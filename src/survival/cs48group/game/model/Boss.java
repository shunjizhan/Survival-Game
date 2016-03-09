package survival.cs48group.game.model;

import java.awt.Rectangle;
import survival.cs48group.game.state.PlayState;
import survival.cs48group.game.main.GameMain;
import survival.cs48group.game.model.BulletE;
import survival.cs48group.game.model.Bullet;
import survival.cs48group.game.model.MainCharacter;
import survival.cs48group.game.state.GameoverState;

public class Boss {
    private int x, y, width, height, VelX,VelY;
    public int check=0;
		private final int Move_Speed=1;
		private Rectangle rect;

		private int b;
        public int hp;
    	public boolean bossgetposition=false;
    	private boolean a=false;
    	private boolean c=false;
    	private int x1,x2;
		//constructor for enemy object
    	public Boss(int x, int y, int width, int height, int hp){
			this.x = x;
			this.y = y;
			this.hp=hp;
			this.width = width;
			this.height= height;
			this.rect = new Rectangle(x+width/10*2,y+height/10*2,width/10*6,height/10*6);
			this.VelX=10;
			this.VelY=10;
		 }
		
		//update the enemy object
		public void update() {		
			if (!bossgetposition){
				if (y<100)
					VelY=2;
				if (y>=100){
					bossgetposition=true;
					c=true;}
				VelX=0;
				
			}
			
			else{
				if (c) 
					{VelX=3;
				     c=false;
				    } 

				if ((y>200) || (y<0)){
					VelY=-1*(VelY);
				}
				if (!a){
					b=0;
					x1= (int) (Math.random()*GameMain.GAME_WIDTH)-400;
					x2= (int) (Math.random()*GameMain.GAME_WIDTH)-400;
					if (x1<0)
						x1=0;
					if (x2<0)
					 x2=0;
					int temp;
					if (x2<x1){
						temp=x1;
						x1=x2;
						x2=temp;
					}
					a=true;
				}
				if ((x<x1) || (x>x2)){
					VelX=VelX*-1;
					b++;
					if (b>=2){
					a=false;
					}
				}
	
			}

			y+=VelY;
			x+=VelX;
			updateRect();
		}


		public void shoot(){
			int speed=5;
			double y1;
			double i= x+width/2;
			double j= y+height/2;
			double r= Math.sqrt(width*width/4+height*height/4);
			
			y1=check*1;
			double degree= y1/360*Math.PI;
			double a=r*Math.sin(degree);
			double b=r*Math.cos(degree);
			double aa=speed*Math.sin(degree);
			double bb=speed*Math.cos(degree);
			PlayState.ArrayBE.add(new BulletE((int)(i+a),(int)(j+b),30,30,(int)(aa),(int)(bb)));
			check+=2;

		}

    		public void shoot2(){
			int speed=5;
			double y1;
			double i= x+width/2;
			double j= y+height/2;
			double r= Math.sqrt(width*width/4+height*height/4);			
	        
			for (int x1=0;x1<180;x1++)
			  {y1=x1*30;
			   double degree= y1/360*Math.PI;
			   double a=r*Math.sin(degree);
			   double b=r*Math.cos(degree);
			   double aa=speed*Math.sin(degree);
			   double bb=speed*Math.cos(degree);
			   PlayState.ArrayBE.add(new BulletE((int)(i+a),(int)(j+b),30,30,(int)(aa),(int)(bb)));
			  }

		}

    public void shoot3(MainCharacter a){
			int speed=10;
			double x1=x+width/2;
			double y1=y+height;
			double x2=x1-a.getX()-a.getWidth()/2;
			double y2=y1-a.getY()-a.getHeight()/2;
			double z1= Math.sqrt(x2*x2+y2*y2);

			double aa= - 10/z1*x2;
			double bb= - 10/z1*y2;
			PlayState.ArrayBE.add(new BulletE((int)(x1),(int)(y1),30,30,(int)(aa),(int)(bb)));


		}

    public void getdamage(Bullet a){
    	if (this.getRect().intersects(a.getRect()))
		   {PlayState.ArrayB.remove(a);
		   	this.hp--;
		   	System.out.println("boss hp is " + this.hp);
		    if (this.hp==-1)
		   	 {
		   		GameMain.sGame.setCurrentState(new GameoverState());

		   		System.out.print("Boss is dead!");
		   		}

	    	}
	}
	        
			    	
	public void stop(){
		VelX=0;
		VelY=0;
	}
	public void start(){
		VelX=5;
		VelY=5;
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
