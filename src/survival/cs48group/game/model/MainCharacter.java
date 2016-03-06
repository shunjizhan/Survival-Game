package survival.cs48group.game.model;

import java.awt.Rectangle;

import survival.cs48group.game.main.GameMain;
import survival.cs48group.game.model.Bullet;
import survival.cs48group.game.state.PlayState;

public class MainCharacter {
	private int x, y, width, height, VelX,VelY;
	private final static int Move_Speed=30;
	private Rectangle rect;
	public boolean powerup = false;
	
	//constructor for the main character
	public MainCharacter(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height= height;
		rect =new Rectangle(x,y,width,height);
		VelX=0;
		VelY=0;
	}
	
	//update the main character (rectangle under it's image)
	public void update() {
		y += VelY;
		x += VelX;
		if (x<0){
			x=0;
		} else if (x+width>GameMain.GAME_WIDTH){
			x = GameMain.GAME_WIDTH-width;
		}
		
		
		if (y<0){
			y=0;
		}
		else if (y+height> GameMain.GAME_HEIGHT){
			y = GameMain.GAME_HEIGHT-height;
		}
		updateRect();
	}
	
	//update the rectangle under the main character's image
	private void updateRect(){
		rect.setBounds(x,y,width,height);
		
	}
	
	//change the direction of the movement; move up
	public void accelUp(){
		VelY= - Move_Speed;
		VelX=0;
	} 
	
	//change the direction of the movement; move down
	public void accelDown(){
		VelY = Move_Speed;
		VelX=0;
	}
	
	//change the direction of the movement; move right
	public void accelRight(){
		VelX= Move_Speed;
		VelY=0;
	}
	
	//change the direction of the movement; move left
	public void accelLeft(){
		VelX= -Move_Speed;
		VelY=0;
	}

	//stop the movement of the character
	public void stop(){
		VelX=0;
		VelY=0;
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
	
	//handle the bullet that main character create; shoot
	public void shoot(){
		PlayState.ArrayB.add(new Bullet(x,y-20,50,50));
		if (powerup){
			PlayState.ArrayB.add(new Bullet(x+50,y-20,50,50));
			PlayState.ArrayB.add(new Bullet(x+100,y-20,50,50));
			PlayState.ArrayB.add(new Bullet(x-50,y-20,50,50));
			PlayState.ArrayB.add(new Bullet(x-100,y-20,50,50));
		}
	}
	
	//get the rectangle
	public Rectangle getRect() {
		return rect;
	}
	
	//not implemented yet
	public void bomb(){
		
	}
}

