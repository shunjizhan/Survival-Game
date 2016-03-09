package survival.cs48group.game.model;

import java.awt.Rectangle;
import survival.cs48group.game.state.PlayState;
import survival.cs48group.game.main.GameMain;
import survival.cs48group.game.model.MainCharacter;
import survival.cs48group.game.model.BulletE;

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
			
			// niao
			if(kind == 1) {
			    VelX= 0 + (int)(Math.random()*8 - 4);
			    VelY= 2;
			}

			// fast
			if(kind == 2) {
			    VelX= 0;
			    VelY= 3;
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

			// niu1
			if(kind == 5) {
			    VelX= 5;
			    VelY= 2;
			}

			// fire
			if(kind == 6) {
			    VelX= (int)Math.random() * 8 - 4;
			    VelY= 2;
			}

			// gao
			if(kind == 7) {
			    VelX= (int)Math.random() * 8 - 4;;
			    VelY= 4;
			}

			// tu
			if(kind == 8) {
			    VelX= (int)Math.random() * 10 - 5;;
			    VelY= 3;
			}

			// yu
			if(kind == 9) {
			    VelX= (int)Math.random() * 18 - 9;;
			    VelY= 6;
			}
			
			
    }
		
		//update the enemy object
		public void update() {		
			y += VelY;
		
			x += VelX;
			if (kind==1)
				{ if ((y==100) || (y==250) ||(y==350))
					{this.shoot();}
				}
				
			if (kind==8){
				 if ((y==100) || (y==200) || (y==300) || (y==0))
				 	this.shoot2();
			}

			if (kind==6){
				if ((y==0)|| (y==200))
					this.shoot1(PlayState.mc);
			}

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
		

		public void shoot(){
			int speed=5;
			double y1;
			double i= x+width/2;
			double j= y+height/2;
			double r= Math.sqrt(width*width/4+height*height/4);
			
			for (int x1=0;x1<3;x1++){
				y1=-60+x1*60;

				double degree= y1/360*Math.PI;
				double a=r*Math.sin(degree);
				double b=r*Math.cos(degree);
				double aa=speed*Math.sin(degree);
				double bb=speed*Math.cos(degree);
				PlayState.ArrayBE.add(new BulletE((int)(i+a),(int)(j+b),30,30,(int)(aa),(int)(bb)));
			}


		}		    		


		public void shoot1(MainCharacter a){
			int speed=30;
			double x1=x+width/2;
			double y1=y+height;
			double x2=x1-a.getX()-a.getWidth()/2;
			double y2=y1-a.getY()-a.getHeight()/2;
			double z1= Math.sqrt(x2*x2+y2*y2);

			double aa= - 10/z1*x2;
			double bb= - 10/z1*y2;
			PlayState.ArrayBE.add(new BulletE((int)(x1),(int)(y1),30,30,(int)(aa),(int)(bb)));
		}

		public void shoot2(){
			int speed=10;
			double x1=x+width/2;
			double y1=y+height;

			double x2=x1-(int)(Math.random()*GameMain.GAME_WIDTH);
			double y2=y1-GameMain.GAME_HEIGHT;
			double z1= Math.sqrt(x2*x2+y2*y2);

			double aa= - 10/z1*x2;
			double bb= - 10/z1*y2;
			PlayState.ArrayBE.add(new BulletE((int)(x1),(int)(y1),30,30,(int)(aa),(int)(bb)));
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


