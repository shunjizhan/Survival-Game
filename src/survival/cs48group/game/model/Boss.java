package survival.cs48group.game.model;

import java.awt.Rectangle;
import survival.cs48group.game.state.PlayState;
import survival.cs48group.game.main.GameMain;
import survival.cs48group.game.model.BulletE;

public class Boss {
		private int x, y, width, height, VelX,VelY;
		private final int Move_Speed=1;
		private Rectangle rect;
        public int hp;
    
		//constructor for enemy object
    	public Boss(int x, int y, int width, int height, int hp){
			this.x = x;
			this.y = y;
			this.hp=hp;
			this.width = width;
			this.height= height;
		
			this.VelX=0;
			this.VelY=0;
		 }
		
		//update the enemy object
		public void update() {		
			y += VelY;
			x += VelX;
			if (x<0){
				VelX=-VelX;
			} else if (x+width>GameMain.GAME_WIDTH){
				VelX=-VelX;
			}
			
			updateRect();
		}


		public void shoot(){
			int speed=10;
			double y1;
			double i= x+width/2;
			double j= y+height/2;
			double r= Math.sqrt(width*width/4+height*height/4);
			for (int x1=0;x1<180;x1++)
			  { y1=x1*30;
			   double degree= y1/360*Math.PI;
			   double a=r*Math.sin(degree);
			   double b=r*Math.cos(degree);
			   double aa=speed*Math.sin(degree);
			   double bb=speed*Math.cos(degree);
			   PlayState.ArrayBE.add(new BulletE((int)(i+a),(int)(j+b),30,30,(int)(aa),(int)(bb)));
			  }

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
