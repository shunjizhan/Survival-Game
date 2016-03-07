package survival.cs48group.game.model;

import java.awt.Rectangle;
import survival.cs48group.game.state.PlayState;
import survival.cs48group.game.main.GameMain;

public class Enemy {
		private int x, y, width, height, VelX,VelY;
		private final int Move_Speed=1;
		private Rectangle rect;
		public boolean isDead=false;
		
		//constructor for enemy object
		public Enemy(int x, int y, int width, int height){
			this.x = x;
			this.y = y;
			this.width = width;
			this.height= height;
			rect =new Rectangle(x,y,width,height);
			VelX=0;
			VelY= Move_Speed + +(int)(Math.random()*3);
			
		}
		
		//update the enemy object
		public void update() {		
			y += VelY;
			VelX=0;
			x += VelX;
			if (x<0){
				x=0;
			} else if (x+width>GameMain.GAME_WIDTH){
				PlayState.ArrayE.remove(this);
			}
			
			
			if (y<0){
				y=0;
			}
			else if (y+height> GameMain.GAME_HEIGHT){
				PlayState.ArrayE.remove(this);
			}
			updateRect();
		}
		
		//set isDead to true if collision happens
		public void disapear(){
	    	isDead=true;
	    	int i=(int) (Math.random()*30);
	    	if ((0==i) || (i==1) || (i==2))
	    	 {PlayState.ArrayI.add(new Item(this.getX(),this.getY(),80,80,i));}
		}
		
		//get the rectangle under the image of the enemy
		public Rectangle getRect() {
			return rect;
		}
		
		//update the rectangle
		private void updateRect(){
			rect.setBounds(x,y,width,height);
			
		}
		
	

	
		//get the x coordinate
		public int getX(){
			return x;
		}
		
		//get the y coordinate
		public int getY(){
			return y;
		}
		
		//get width of the rectangle
		public int getWidth(){
			return width;
		}
		
		//get height of the rectangle
		public int getHeight(){
			return height;
		}
		
	}


