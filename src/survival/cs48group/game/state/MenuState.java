package survival.cs48group.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import survival.cs48group.game.main.Resources;

public class MenuState extends State{
	
	//initialize the menu state
	@Override
	public void initializeState(){
		System.out.println("Entered instructionState");
	}
	
	//not implemented yet
	@Override
	public void updateState(){
		//Do Nothing
	}
	
	//draw the welcome image for the menu
	@Override
	public void renderImages(Graphics g){
		//Draws Resources.welcome to the screen at x = 0
		g.drawImage(Resources.welcome, 0, 0, null);

	}

    	public void onClick(MouseEvent e){
	    Resources.menu.stop();
		setCurrentState(new instructionState());
	}
    
	
	//not implemented yet
	@Override
	public void onPress(KeyEvent e){
		
	}
	
	@Override
	public void onRelease(KeyEvent e){
		
	}

}
