package survival.cs48group.game.model;

import java.awt.Rectangle;
import survival.cs48group.game.state.PlayState;
import survival.cs48group.game.main.GameMain;

public class Enemy {
		private int x, y, width, height, VelX,VelY;
		private final int Move_Speed=1;
		private Rectangle rect;
		public boolean isDead=false;
		public int hp;
		//constructor for enemy object
		public Enemy(int x, int y, int width, int height, int hp){
			this.x = x;
			this.y = y;
			this.hp=hp;
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


