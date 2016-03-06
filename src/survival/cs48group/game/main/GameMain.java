package survival.cs48group.game.main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import survival.cs48group.game.main.Game;

public class GameMain {
	private static final String GAME_TITLE = "Survival";
	public static final int GAME_WIDTH = 1310;
	public static final int GAME_HEIGHT = 800;
	
	public static Game sGame;
	//"static" : are NOT instance variables but class variables
	//NOT belong to any particular instance
	//they belong to the class, and can be accessed without instantiating GameMain
	
	//"final": prevent us from changing the values of these variables
	
	//actually run the program by main method
	public static void main(String[] args){
		JFrame frame = new JFrame(GAME_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		sGame = new Game(GAME_WIDTH, GAME_HEIGHT);
		frame.add(BorderLayout.CENTER, sGame);//or just frame.add(sGame)
		frame.pack();
		frame.setVisible(true);

    }
}
