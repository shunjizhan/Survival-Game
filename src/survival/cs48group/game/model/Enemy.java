package survival.cs48group.game.model;

import java.awt.Rectangle;
import survival.cs48group.game.state.PlayState;
import survival.cs48group.game.main.GameMain;

public class Enemy {
		private int x, y, width, height, VelX,VelY;
		private final int Move_Speed=1;
		private Rectangle rect;
		public boolean isDead=false;
    public int hp, kind;
    
		//constructor for enemy object
    public Enemy(int x, int y, int width, int height, int hp, int kind){
			this.x = x;
			this.y = y;
			this.hp=hp;
			this.width = width;
			this.height= height;
			this.kind = kind;		    
			rect =new Rectangle(x+width/10*2,y+height/10*2,width/10*6,height/10*6);
			
			// bat
			if(kind == 1) {
			    VelX= 0 + (int)(Math.random()*8 - 4);
			    VelY= 2;
			}

			// fast
			if(kind == 2) {
			    VelX= 0;
			    VelY= 5;
			}

			// frog
			if(kind == 3) {
			    VelX= 0;
			    VelY= 1;
			}

			// big frog
			if(kind == 4) {
			    VelX= 0;
			    VelY= 5;
			}
			
			
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
			
			
			if (y > GameMain.GAME_HEIGHT){
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
			rect.setBounds(x+width/10*2,y+height/10*2,width/10*6,height/10*6);			
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


