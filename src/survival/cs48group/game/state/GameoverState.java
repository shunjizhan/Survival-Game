package survival.cs48group.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import survival.cs48group.game.main.Resources;

public class GameoverState extends State{
	
	//initialize load state to load resources
	@Override
	public void initializeState(){
		System.out.println("Game Over!");
	}
	
	//set current state
	@Override
	public void updateState(){
	        
	}
	
	//Not implemented yet
	@Override
	public void renderImages(Graphics g){
	    g.drawImage(Resources.welcome, 0, 0, null);
	}
	
	@Override
	public void onClick(MouseEvent e){
	    setCurrentState(new PlayState());
	}
	
	@Override
	public void onPress(KeyEvent e){
		//TODO Auto-generated method stub
	}
	
	@Override
	public void onRelease(KeyEvent e){
		//TODO Auto-generated method stub
	}

} 
