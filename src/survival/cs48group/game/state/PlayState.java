package survival.cs48group.game.state;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import survival.cs48group.game.main.Game;
import survival.cs48group.game.main.GameMain;
import survival.cs48group.game.main.Resources;
import survival.cs48group.game.model.MainCharacter;
import survival.cs48group.game.model.Bullet;
import survival.cs48group.game.model.Enemy;

public class PlayState extends State{
	private MainCharacter  mc;
	//private int score = 0;
	//private Font scoreFont;
    private static int count=0;
	public static ArrayList<Bullet> ArrayB = new ArrayList<Bullet>(100);
	public static ArrayList<Enemy> ArrayE = new ArrayList<Enemy>(100); 
	private static int bgp=-1024+GameMain.GAME_HEIGHT;
	
	//initialize the play state and create new main character object
	@Override
	public void initializeState() {
		mc = new MainCharacter(710,620,192,312);
		
		//scoreFont = new Font("SansSerif", Font.BOLD, 25);
	}
	
	//update the play state to handle removing character and bullet
	@Override
	public void updateState() {
		mc.update();
		for(int i=0; i<ArrayB.size(); i++) {
			if (ArrayB.get(i).isDead)
				ArrayB.remove(ArrayB.get(i));
		}
	    for (int i=0; i<ArrayE.size();i++){
	    	if (ArrayE.get(i).isDead)
				ArrayE.remove(ArrayE.get(i));
	    }
			for(int i=0; i<ArrayB.size(); i++) {
				ArrayB.get(i).update();
			}
		    for (int i=0; i<ArrayE.size();i++){
		    	ArrayE.get(i).update();
		    }
		if (bgp>=0)
			bgp=-1200+GameMain.GAME_HEIGHT;
		bgp++;
	}

	//public void render(Graphics g) {
		//g.setFont(scoreFont);
		//g.drawString("ss" + score, 350, 40);
	//}
	
	//draw all the images needed in the play state
	@Override
	public void renderImages(Graphics g) {	
		
		g.drawImage(Resources.background,-300,bgp,null);
		
			for (int i=0;i<ArrayB.size();i++){
				for (int j=0;j<ArrayE.size();j++)	
					ArrayB.get(i).onCollideWith(ArrayE.get(j));
			}
			g.drawImage(Resources.flight, mc.getX(), mc.getY(), null);
			
		//if(ArrayB.size() > 0){
			for(int i1=0; i1<ArrayB.size(); i1++) {
				g.drawImage(Resources.bullet, ArrayB.get(i1).getX(), ArrayB.get(i1).getY(), null);
			}
		//}
		
			if (count>10000) { count=0;}
			count++;
			if (count % 25==0)
			{   int x=(int) (Math.random()*GameMain.GAME_WIDTH);
				ArrayE.add(new Enemy(x,0,200,100));
			}
			for (int i1=0; i1<ArrayE.size();i1++){
				g.drawImage(Resources.enemies,ArrayE.get(i1).getX(),ArrayE.get(i1).getY(),null);
			}
	}
	
	//not implemented yet
	@Override
	public void onClick(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}
	
	//handle key press event
	@Override
	public void onPress(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_UP
				|| k.getKeyCode() == KeyEvent.VK_W) {
			mc.accelUp();
		}
		else if (k.getKeyCode() == KeyEvent.VK_DOWN
				|| k.getKeyCode() == KeyEvent.VK_S) {
			mc.accelDown();
		}
		else if (k.getKeyCode() == KeyEvent.VK_LEFT
				|| k.getKeyCode() == KeyEvent.VK_A) {
			mc.accelLeft();
		}
		else if (k.getKeyCode() == KeyEvent.VK_RIGHT
				|| k.getKeyCode() == KeyEvent.VK_D) {
			mc.accelRight();
		}
		else if (k.getKeyCode() == KeyEvent.VK_Z
				|| k.getKeyCode() == KeyEvent.VK_H) {
			mc.shoot();
		}
		else if (k.getKeyCode() == KeyEvent.VK_X
				|| k.getKeyCode() == KeyEvent.VK_J) {
			mc.bomb();
		}	
		else if (k.getKeyCode() == KeyEvent.VK_F1) {
			setCurrentState(new MenuState());
		}
		else if (k.getKeyCode() == KeyEvent.VK_F2) {
			if(mc.powerup == true){
				mc.powerup = false;
			}
			else {		
				mc.powerup = true;
			}
		}
	}
	
	//handle key release event
	@Override
	public void onRelease(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_UP 
				|| k.getKeyCode() == KeyEvent.VK_DOWN
				|| k.getKeyCode() == KeyEvent.VK_LEFT
				|| k.getKeyCode() == KeyEvent.VK_RIGHT
				|| k.getKeyCode() == KeyEvent.VK_W
				|| k.getKeyCode() == KeyEvent.VK_X
				|| k.getKeyCode() == KeyEvent.VK_A
				|| k.getKeyCode() == KeyEvent.VK_D) {
			mc.stop();
		}
		
	}
	
	
}
