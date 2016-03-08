package survival.cs48group.game.model;

import java.awt.Rectangle;

import survival.cs48group.game.main.GameMain;
import survival.cs48group.game.state.PlayState;

public class Bullet {
	public int x, y, width, height, vel;
	private static int Move_Speed=-32;
	public Rectangle rect;
	public boolean isDead=false;
    public int kind;
	
	//constructor for the bullet (fire ball)
    public Bullet(int x, int y, int width, int height, int kind){
		this.x = x + width  +6;
		this.y = y + height ;
		this.width = width;
		this.height= height;
		this.kind = kind;
		rect =new Rectangle(x,y,width,height);
		vel=Move_Speed;
	}
	
	//update the animation of the bullets(fire balls)
	public void update() {
		y += vel;
		/*x += -100+(int)(Math.random()*200);*/
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
	public void onCollideWith(Enemy a){
		if (this.getRect().intersects(a.getRect()))
		   {PlayState.ArrayB.remove(this);
		   	a.hp--;
		   	if (a.hp<0)
		   		{
				    int i=(int) (Math.random() * 5);
				    if ((0==i) || (i==1) || (i==2))
					{
					    PlayState.ArrayI.add(new Item(a.getX(),a.getY(),80,80,i));
					}
						    
				    PlayState.ArrayE.remove(a);
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
