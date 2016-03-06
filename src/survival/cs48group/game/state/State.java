package survival.cs48group.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import survival.cs48group.game.main.GameMain;

public abstract class State {
    //abstract methods
	
	public abstract void initializeState();
    
    public abstract void updateState();
    
    public abstract void renderImages(Graphics g);
    
    public abstract void onClick(MouseEvent m);
    
    public abstract void onPress(KeyEvent k);
    
    public abstract void onRelease(KeyEvent k);
    
    //set the current state
    public void setCurrentState(State newState){
		GameMain.sGame.setCurrentState(newState);
	}

}