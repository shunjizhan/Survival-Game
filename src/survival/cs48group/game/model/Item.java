package survival.cs48group.game.model;

import java.awt.Rectangle;
import survival.cs48group.game.state.PlayState;
import survival.cs48group.game.main.GameMain;

public class Item {
		private int x, y, width, height, VelX,VelY;

		private int kind;
		private Rectangle rect;
	
	
		//constructor for enemy object
		public Item(int x, int y, int width, int height,int kind){
			this.x = x;
			this.y = y;
			this.width = width;
			this.height= height;
			this.kind=kind;
			rect =new Rectangle(x,y,width,height);
			VelX=4;
			VelY=5;
		}
		
		//update the enemy object
		public void update() {		
			y += VelY;
			x += VelX;
			if (x<0){
				x=0;
				this.VelX=-VelX;
			} else if (x+width>GameMain.GAME_WIDTH){
				this.VelX=-Velx;
			}
			
			
			if (y<0){
				y=0;
			}
			else if (y+height> GameMain.GAME_HEIGHT){
				PlayState.ArrayI.remove(this);
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