package survival.cs48group.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import survival.cs48group.game.main.Resources;

public class LoadState extends State{
	
	//initialize load state to load resources
	@Override
	public void initializeState(){
		Resources.load();
		System.out.println("Loaded Successfully");
	}
	
	//set current state
	@Override
	public void updateState(){
		setCurrentState(new MenuState());
	}
	
	//Not implemented yet
	@Override
	public void renderImages(Graphics g){
		//TODO Auto-generated method stub
	}
	
	@Override
	public void onClick(MouseEvent e){
		//TODO Auto-generated method stub
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