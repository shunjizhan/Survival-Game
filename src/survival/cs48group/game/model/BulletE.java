package survival.cs48group.game.model;
import java.util.ArrayList;
import java.awt.Rectangle;
import survival.cs48group.game.state.GameoverState;
import survival.cs48group.game.model.Bullet;
import survival.cs48group.game.main.GameMain;
import survival.cs48group.game.state.PlayState;
import survival.cs48group.game.model.BulletE;
import survival.cs48group.game.model.Item;
import survival.cs48group.game.model.MainCharacter;
public class BulletE {
	public int x, y, width, height, vely,velx;

	public Rectangle rect;

	
	//constructor for the bullet (fire ball)
    public BulletE(int x, int y, int width, int height, int velx,int vely){
		this.x = x - width/2;
		this.y = y - height;
	    rect =new Rectangle(x+width/10*3,y+height/10*3,width/10*4,height/10*4);
		this.width = width;
		this.height= height;
		this.velx = velx;
		this.vely = vely;
	}
	
	//update the animation of the bullets(fire balls)
	public void update() {
		y += vely;
	    x += velx;

		if ((y<-height) || (y>GameMain.GAME_HEIGHT)){
			PlayState.ArrayBE.remove(this);
		}
		if ((x>GameMain.GAME_WIDTH) || (x< - width))
			{PlayState.ArrayBE.remove(this);}
		updateRect();
	}
	
	//update the rectangle which is invisible under the bullet image
	private void updateRect(){
		rect.setBounds(x+width/10*2,y+height/10*2,width/10*6,height/10*6);
		
	}
	
	//handle with the collision between enemies and bullets
	public void onCollideWith(MainCharacter a){
		if (this.getRect().intersects(a.getRect()))
		   {PlayState.ArrayBE.remove(this);
		   	a.hp--;
		   	a.powerLevel--;
		   	if(a.powerLevel < 1) {
		   		a.powerLevel = 1;
		   	}
		   	
		    if (a.hp==-1){
		    	PlayState.ArrayE = new ArrayList<Enemy>();
				PlayState.ArrayI = new ArrayList<Item>();
				PlayState.ArrayB = new ArrayList<Bullet>();
				PlayState.ArrayBE = new ArrayList<BulletE>();
		    	GameMain.sGame.setCurrentState(new GameoverState());
		   }
		}
	}	
	
	
	
	//set the boolean isDead to true if collision happens and character needs to be removed


	
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


}
