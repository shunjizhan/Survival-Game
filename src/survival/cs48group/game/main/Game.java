package survival.cs48group.game.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;

import javax.swing.JPanel;

import survival.cs48group.framework.util.InputHelper;
import survival.cs48group.game.state.LoadState;
import survival.cs48group.game.state.State;

@SuppressWarnings("serial")

public class Game extends JPanel implements Runnable{
	private int gameWidth;
	private int gameHeight;
	private Image gameImage;
	
	private Thread gameThread;
	private volatile boolean running;
	
	public State currentState;
	
	private InputHelper inputHelper;
	
	//constructor for Game object, give the dimension of the game
	public Game(int gameWidth, int gameHeight){
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		setPreferredSize(new Dimension(gameWidth, gameHeight));
		setBackground(Color.BLACK);
		//keyboard events and buttons will now be available to our Game object
		setFocusable(true);
		requestFocus();
	}
	
	//initialize a new state and set the current state
	public void setCurrentState(State newState){
		System.gc();
		newState.initializeState();
		currentState = newState;
		inputHelper.setCurrentState(currentState);
	}
	
	//initialize game and input helper
	@Override
	public void addNotify(){
		super.addNotify();
		initInput();
		setCurrentState(new LoadState());
		initGame();
	}
	
	//initialize input helper
	private void initInput(){
		inputHelper = new InputHelper();
		addKeyListener(inputHelper);
		addMouseListener(inputHelper);
	}
	
	//initialize game thread
	private void initGame(){
		running = true;
		gameThread = new Thread(this, "Game Thread");
		gameThread.start();
	}

	//run the game
	@Override
	public void run() {
		while(running){
			currentState.updateState();
			prepareGameImage();
			currentState.renderImages(gameImage.getGraphics());
			repaint();
			try{
				Thread.sleep(14);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		//End game immediately when running becomes false
		System.exit(0);
		
	}
	
	//prepare the space for the whole game image
	private void prepareGameImage(){
		if(gameImage == null){
			gameImage = createImage(gameWidth, gameHeight);
		}
		Graphics g = gameImage.getGraphics();
		g.clearRect(0, 0, gameWidth, gameHeight);
	}
	
	//end the game
	public void exit(){
		running = false;
	}
	
	//draw game image on the screen
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if(gameImage == null){
			return;
		}
		g.drawImage(gameImage, 0, 0, null);
	}

}
