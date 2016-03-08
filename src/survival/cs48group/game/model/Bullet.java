package survival.cs48group.game.model;

import java.awt.Rectangle;

import survival.cs48group.game.main.GameMain;
import survival.cs48group.game.state.PlayState;

public class Bullet {
	public int x, y, width, height, vel,velx;

	public Rectangle rect;
	public boolean isDead=false;
    public int kind;
	
	//constructor for the bullet (fire ball)
    public Bullet(int x, int y, int width, int height, int kind,int speed){
		this.x = x - width/2;
		this.y = y - height ;
		this.width = width;
		this.height= height;
		this.kind = kind;
		rect =new Rectangle(x,y,width,height);
		
		vel=speed;
		if (speed>0)
		vel=-speed; 
		velx=0;
		if (kind==2){
			velx=speed/3;
			}
	}
	
	//update the animation of the bullets(fire balls)
	public void update() {
		y += vel;
	    x += velx;
		if (y<-height){
			PlayState.ArrayB.remove(this);
		}
		updateRect();
	}
	
	//update the rectangle which is invisible under the bullet image
	private void updateRect(){
		rect.setBounds(x,y,width,height);
		
	}
	
	//handle with the collision between enemies and bullets
	public void onCollideWith(Enemy a) {
		if (this.getRect().intersects(a.getRect()))
		   {
		       PlayState.ArrayB.remove(this);
		       a.hp--;
		       if (a.hp <= 0)
			   {
			       int i=(int) (Math.random() * 30);
			       if ((0==i) || (i==1) || (i==2))
				   {
				       PlayState.ArrayI.add(new Item(a.getX(),a.getY(),80,80,i));
				   }
			       
			       PlayState.ArrayE.remove(a);
			       PlayState.score++;
			       if(PlayState.score == 30) {
				   PlayState.stage = 2;
				   System.out.println("stage" +  PlayState.stage);
			       }
			       if(PlayState.score == 100) {
				   PlayState.stage = 3;
				   System.out.println("stage" +  PlayState.stage);
			       }
			       if(PlayState.score == 150) {
				   PlayState.stage = 4;
				   System.out.println("stage" +  PlayState.stage);
			       }
			       if(PlayState.score == 200) {
				   PlayState.stage = 5;
				   System.out.println("stage" +  PlayState.stage);
			       }
			       if(PlayState.score == 250) {
				   PlayState.stage = 6;
				   System.out.println("stage" +  PlayState.stage);
			       }
			       if(PlayState.score == 350) {
				   PlayState.stage = 7;
				   System.out.println("stage" +  PlayState.stage);
			       }
			       
			   }
		   }		
	}
	

	
	
	//set the boolean isDead to true if collision happens and character needs to be removed
	public void disapear(){
	    	isDead=true;
	}

	//stop the movement of main character
	public void stop(){
		vel=0;
	}
	
	//get the value of the x coordinate
	public int getX(){
		return x;
	}
	
	//get the value of the y coordinate
	public int getY(){
		return y;
	}
	
	//get the value of width
	public int getWidth(){
		return width;
	}
	
	//get the value of height
	public int getHeight(){
		return height;
	}
	
	//get the rectangle
	public Rectangle getRect() {
		return rect;
	}

    public int getKind() {
	return this.kind;
    }

}
