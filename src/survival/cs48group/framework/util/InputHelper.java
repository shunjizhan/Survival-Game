package survival.cs48group.framework.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import survival.cs48group.game.state.State;

public class InputHelper implements KeyListener, MouseListener{
	
	private State currentState;
	
	//set current state
	public void setCurrentState(State currentState){
		this.currentState = currentState;
	}
	
	//listen the mouse click event
	@Override
	public void mouseClicked(MouseEvent e) {
		currentState.onClick(e);
		
	}
	
	//not implemented yet
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	//not implemented yet
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	//not implemented yet
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//not implemented yet
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//listen the key press event
	@Override
	public void keyPressed(KeyEvent e) {
		currentState.onPress(e);
		
	}
	
	//listen the key release event
	@Override
	public void keyReleased(KeyEvent e) {
		currentState.onRelease(e);
		
	}

	//not implemented yet
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
