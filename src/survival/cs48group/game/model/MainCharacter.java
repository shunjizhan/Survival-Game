package survival.cs48group.game.model;

import java.awt.Rectangle;
import java.util.ArrayList;

import survival.cs48group.game.main.GameMain;
import survival.cs48group.game.model.Bullet;
import survival.cs48group.game.model.Item;
import survival.cs48group.game.state.PlayState;
import survival.cs48group.game.state.GameoverState;

public class MainCharacter {
    private int x, y, width, height, VelX,VelY;
    public int hp,bombNum;
	private final static int Move_Speed=20;
	private Rectangle rect;
	public int powerLevel = 1;
    public boolean superPower;
	
	//constructor for the main character
	public MainCharacter(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.hp=3;
		this.width = width;
		this.height= height;
		this.bombNum = 1;
		this.superPower = false;
		rect =new Rectangle(x+50,y,20,20);
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
		rect.setBounds(x+50,y,20,20);
		
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
		if (powerLevel!=2)
	    	PlayState.ArrayB.add(new Bullet(this.x+this.width/2,y,40,80,1,-24));
		if (powerLevel==2){
		    PlayState.ArrayB.add(new Bullet(this.x+25+this.width/2,this.y,40,80,1,-24));
		    PlayState.ArrayB.add(new Bullet(this.x-25+this.width/2,this.y,40,80,1,-24));
		}

		if (powerLevel >2) {
		    PlayState.ArrayB.add(new Bullet(this.x+60+this.width/2,this.y,40,80,1,-24));
		    PlayState.ArrayB.add(new Bullet(this.x-60+this.width/2,this.y,40,80,1,-24));
		}
		if (powerLevel==4)
			{
				
		    	PlayState.ArrayB.add(new Bullet(this.x+60+this.width/2,this.y,30,60,2,33));
		    	PlayState.ArrayB.add(new Bullet(this.x-60+this.width/2,this.y,30,60,2,-33));
			}
	}

    	public void bomb(){
	    if(bombNum > 0) {
		bombNum--;
		if(bombNum < 0) {
		    bombNum = 0;
		}
		PlayState.ArrayB= new ArrayList<Bullet>();
		PlayState.ArrayE = new ArrayList<Enemy>();
		PlayState.ArrayBE = new ArrayList<BulletE>();
		try {
		    Thread.sleep(10);
		} catch(Exception e) {}
	    }
	}

    public void getBomb() {
	bombNum++;
	if(bombNum > 3) {
	    bombNum = 3;
	}
    }

	
	//get the rectangle
	public Rectangle getRect() {
		return rect;
	}


	public void onCollideWith(Enemy a){
		if (this.getRect().intersects(a.getRect()))
		   {this.hp--;
		    PlayState.ArrayE.remove(a);
		    if(this.powerLevel > 1) {
			this.powerLevel--;
		    }
		    if (this.hp==-1){
			PlayState.ArrayE = new ArrayList<Enemy>();
			PlayState.ArrayI = new ArrayList<Item>();
			PlayState.ArrayB = new ArrayList<Bullet>();
			PlayState.ArrayBE = new ArrayList<BulletE>();
		    GameMain.sGame.setCurrentState(new GameoverState());
		    }
		  
		   }
	}	

	public void onCollideWith1(Item a){
		if (this.getRect().intersects(a.getRect()))
		   {
		   	int i=a.getKind();
		    if (i==0)
		    	{if (this.hp<3) 
		    		{this.hp++;}}
		    else if (i==1)
		    	{this.getBomb();}
		    else if (i==2)
		    	{    if (this.powerLevel<4)
		    		 this.powerLevel++;}
		    PlayState.ArrayI.remove(a);

		   }
	}

}

