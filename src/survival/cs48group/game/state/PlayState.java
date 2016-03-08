package survival.cs48group.game.state;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import survival.cs48group.game.main.Game;
import survival.cs48group.game.main.GameMain;
import survival.cs48group.game.main.Resources;
import survival.cs48group.game.model.MainCharacter;
import survival.cs48group.game.model.Bullet;
import survival.cs48group.game.model.BulletE;
import survival.cs48group.game.model.Enemy;
import survival.cs48group.game.model.Item;
import survival.cs48group.game.model.Boss;
import survival.cs48group.game.state.GameoverState;
public class PlayState extends State{
	private MainCharacter  mc;
	private Boss bs;
	//private int score = 0;
	//private Font scoreFont;
    private static int count=0;
	public static ArrayList<Bullet> ArrayB = new ArrayList<Bullet>();
	public static ArrayList<Enemy> ArrayE = new ArrayList<Enemy>(); 
	public static ArrayList<Item> ArrayI = new ArrayList<Item>();  
	public static ArrayList<BulletE> ArrayBE = new ArrayList<BulletE>();
	private static int bgp=-1024+GameMain.GAME_HEIGHT;
    public static int score, stage;
    public boolean frogCreated, bigFrogCreated,BossCreated;

    public PlayState() {
	super();
	score = 0;
	stage = 1;
	frogCreated = false;
    }
	
	//initialize the play state and create new main character object
	@Override
	public void initializeState() {
		mc = new MainCharacter(GameMain.GAME_WIDTH/2,GameMain.GAME_HEIGHT,120,120);
		
		//scoreFont = new Font("SansSerif", Font.BOLD, 25);
	}
	
	//update the play state to handle removing character and bullet
	@Override
	public void updateState() {
		mc.update();
			for(int i=0; i<ArrayBE.size(); i++) {
				ArrayBE.get(i).update();
			}
			for(int i=0; i<ArrayB.size(); i++) {
				ArrayB.get(i).update();
			}
		    for (int i=0; i<ArrayE.size();i++){
		    	ArrayE.get(i).update();
		    }
		    for (int i=0; i<ArrayI.size();i++){
		    	ArrayI.get(i).update();
		    }
		    
		if (bgp>=0)
			bgp=-2950+GameMain.GAME_HEIGHT;
		bgp++;
	}

	//public void render(Graphics g) {
		//g.setFont(scoreFont);
		//g.drawString("ss" + score, 350, 40);
	//}
	
	//draw all the images needed in the play state
	@Override
	public void renderImages(Graphics g) {
	    if(mc.superPower == true) {
		mc.shoot();
	    }
	    if(count % 5 == 0) {
		mc.shoot();
	    }
		g.drawImage(Resources.background,0,bgp,null);
		if (BossCreated){
		g.drawImage(Resources.boss,bs.getX(),bs.getY(),null);
		}
		

			for (int i=0;i<ArrayE.size();i++){
				mc.onCollideWith(ArrayE.get(i));
			}
			for (int i=0;i<ArrayI.size();i++){
				mc.onCollideWith1(ArrayI.get(i));
			}

			// draw lives number
		if (mc.hp==3){
			g.drawImage(Resources.hp,0,GameMain.GAME_HEIGHT-110,null);
			g.drawImage(Resources.hp,110,GameMain.GAME_HEIGHT-110,null);
			g.drawImage(Resources.hp,220,GameMain.GAME_HEIGHT-110,null);
		}
		if (mc.hp==2){
			g.drawImage(Resources.hp,0,GameMain.GAME_HEIGHT-110,null);
			g.drawImage(Resources.hp,110,GameMain.GAME_HEIGHT-110,null);
		}
		if (mc.hp==1){
			g.drawImage(Resources.hp,0,GameMain.GAME_HEIGHT-110,null);	
		}

		// draw bombs number
		if (mc.bombNum==3){
			g.drawImage(Resources.bomb,0+650,GameMain.GAME_HEIGHT-110,null);
			g.drawImage(Resources.bomb,110+650,GameMain.GAME_HEIGHT-110,null);
			g.drawImage(Resources.bomb,220+650,GameMain.GAME_HEIGHT-110,null);
		}
		if (mc.bombNum==2){
			g.drawImage(Resources.bomb,220+650,GameMain.GAME_HEIGHT-110,null);
			g.drawImage(Resources.bomb,110+650,GameMain.GAME_HEIGHT-110,null);
		}
		if (mc.bombNum==1){
			g.drawImage(Resources.bomb,220+650,GameMain.GAME_HEIGHT-110,null);	
		}

			for (int i=0;i<ArrayB.size();i++){
				for (int j=0;j<ArrayE.size();j++)
					if ((i<ArrayB.size()) && (j<ArrayE.size()))
					ArrayB.get(i).onCollideWith(ArrayE.get(j));
			}

			g.drawImage(Resources.flight, mc.getX(), mc.getY(), null);
			
			// draw bullet
			for(int i1=0; i1<ArrayB.size(); i1++) {
			    if(ArrayB.get(i1).getKind() == 1) {
				g.drawImage(Resources.bullet, ArrayB.get(i1).getX(), ArrayB.get(i1).getY(), null);
			    }
			    if(ArrayB.get(i1).getKind() == 2){
				g.drawImage(Resources.bullet2, ArrayB.get(i1).getX(), ArrayB.get(i1).getY(), null);
			    }
			}

			for (int i3=0;i3<ArrayBE.size();i3++)
				g.drawImage(Resources.bulletE,ArrayBE.get(i3).getX(),ArrayBE.get(i3).getY(),null);

			// draw item
			for (int i2=0;i2<ArrayI.size();i2++) {
			    if (ArrayI.get(i2).getKind()==0){
				g.drawImage(Resources.hpitem,ArrayI.get(i2).getX(),ArrayI.get(i2).getY(),null);
			    }
			    if (ArrayI.get(i2).getKind()==1) {
				g.drawImage(Resources.bombitem,ArrayI.get(i2).getX(),ArrayI.get(i2).getY(),null);
			    }
			    if (ArrayI.get(i2).getKind()==2) {
				g.drawImage(Resources.bulletitem,ArrayI.get(i2).getX(),ArrayI.get(i2).getY(),null);
			    }
       
			}

			// create enemy
			if(PlayState.stage == 1) {
			    createEnemy1();	
			}

			if(PlayState.stage == 2) {
			    createEnemy2();	
			}

			if(PlayState.stage == 3) {
			    createEnemy3();	
			}

			if(PlayState.stage == 4) {
			    createEnemy4();	
			}

			if(PlayState.stage == 5) {
			    createEnemy5();	
			}

			if(PlayState.stage == 6) {
			    createEnemy6();	
			}

			if(PlayState.stage == 7) {
			    createEnemy7();	
			}

			if(PlayState.stage == 8) {
			    createEnemy8();	
			}
			
			if(PlayState.stage == 9) {
			    createEnemy9();	
			}

			if(PlayState.stage == 10) {
			    createEnemy10();	
			}

			

			// draw enemy
			for (int i1=0; i1<ArrayE.size();i1++){
			    if(ArrayE.get(i1).kind == 1) {
				g.drawImage(Resources.enemies,ArrayE.get(i1).getX(),ArrayE.get(i1).getY(),null);
			    }
			    if(ArrayE.get(i1).kind == 2) {
				g.drawImage(Resources.enemy2,ArrayE.get(i1).getX(),ArrayE.get(i1).getY(),null);
			    }
			    if(ArrayE.get(i1).kind == 3) {
				g.drawImage(Resources.frog,ArrayE.get(i1).getX(),ArrayE.get(i1).getY(),null);
			    } 
			    if(ArrayE.get(i1).kind == 4) {
				g.drawImage(Resources.frog600,ArrayE.get(i1).getX(),ArrayE.get(i1).getY(),null);
			    }
			    if(ArrayE.get(i1).kind == 5) {
				g.drawImage(Resources.niu1,ArrayE.get(i1).getX(),ArrayE.get(i1).getY(),null);
			    }
			    if(ArrayE.get(i1).kind == 6) {
				g.drawImage(Resources.fire,ArrayE.get(i1).getX(),ArrayE.get(i1).getY(),null);
			    }
			    if(ArrayE.get(i1).kind == 7) {
				g.drawImage(Resources.gao,ArrayE.get(i1).getX(),ArrayE.get(i1).getY(),null);
			    }
			    if(ArrayE.get(i1).kind == 8) {
				g.drawImage(Resources.tu,ArrayE.get(i1).getX(),ArrayE.get(i1).getY(),null);
			    }
			    if(ArrayE.get(i1).kind == 9) {
				g.drawImage(Resources.yu,ArrayE.get(i1).getX(),ArrayE.get(i1).getY(),null);
			    }
			}

        
	}
	
	//not implemented yet
	@Override
	public void onClick(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}
	
	//handle key press event
	@Override
	public void onPress(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_UP
				|| k.getKeyCode() == KeyEvent.VK_W) {
			mc.accelUp();
		}
		else if (k.getKeyCode() == KeyEvent.VK_DOWN
				|| k.getKeyCode() == KeyEvent.VK_S) {
			mc.accelDown();
		}
		else if (k.getKeyCode() == KeyEvent.VK_LEFT
				|| k.getKeyCode() == KeyEvent.VK_A) {
			mc.accelLeft();
		}
		else if (k.getKeyCode() == KeyEvent.VK_RIGHT
				|| k.getKeyCode() == KeyEvent.VK_D) {
			mc.accelRight();
		}
		else if (k.getKeyCode() == KeyEvent.VK_Z
				|| k.getKeyCode() == KeyEvent.VK_H) {
			mc.shoot();
		}
		else if (k.getKeyCode() == KeyEvent.VK_X
				|| k.getKeyCode() == KeyEvent.VK_J) {
			mc.bomb();
		}	
		else if (k.getKeyCode() == KeyEvent.VK_F1) {
			setCurrentState(new MenuState());
		}
		else if (k.getKeyCode() == KeyEvent.VK_F2) {
		    if(mc.superPower == true) {
			mc.superPower = false;
		    }
		    else {
			mc.superPower = true;
		    }
		}
	}
	
	//handle key release event
	@Override
	public void onRelease(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_UP 
				|| k.getKeyCode() == KeyEvent.VK_DOWN
				|| k.getKeyCode() == KeyEvent.VK_LEFT
				|| k.getKeyCode() == KeyEvent.VK_RIGHT
				|| k.getKeyCode() == KeyEvent.VK_W
				|| k.getKeyCode() == KeyEvent.VK_X
				|| k.getKeyCode() == KeyEvent.VK_A
				|| k.getKeyCode() == KeyEvent.VK_D) {
			mc.stop();
		}
		
	}

    public void createEnemy1() {

	if (count>100) { count=0;}
	count++;
	if (count % 50 == 0)
	    {   int x=(int) (Math.random()*(GameMain.GAME_WIDTH-180));
		createNiao(x);
	    }
	
	if (count % 10 == 0 )
	    {   int x=(int) (Math.random()*(GameMain.GAME_WIDTH-180));
		createNiu2(x);
	    }
    }

    public void createEnemy2() {
	if (count>100) { count=0;}
	count++;
	if (count % 100==0)
	    {   int x=(int) (Math.random()*(GameMain.GAME_WIDTH-180));
	        createNiao(x);
	    }
	
	if (count % 2 == 0)
	    {   int x=(int) (Math.random()*(GameMain.GAME_WIDTH-180));
	        createNiu2(x);
	    }
    }

    public void createEnemy3() {
	if (count>1000) { count=0;}
	count++;
	
	if (count % 20 == 0)
	    {   int x=(int) (Math.random()*(GameMain.GAME_WIDTH-180));
	        createNiao(x);
	    }
	
	if (count % 5 == 0)
	    {   int x=(int) (Math.random()*(GameMain.GAME_WIDTH-180));
		createNiu2(x);
	    }

    }

    public void createEnemy4() {
	if (count>1000) { count=0;}
	count++;
	
	if(frogCreated == false) {
	    createFrog(100);
	    createFrog(400);
	    createFrog(700);
	    frogCreated = true;
	}
	if (count % 10 == 0)
	    {   
		int x=(int) (Math.random()*(GameMain.GAME_WIDTH-180));
	        createNiu2(x);
	    }

    }

    public void createEnemy5() {
	if (count>100) { count=0;}
	count++;

	if(bigFrogCreated == false) {
	    createBigFrog(200);
	    bigFrogCreated = true;
	}

	if (count % 10 == 0)
	    {   
		int x=(int) (Math.random()*(GameMain.GAME_WIDTH-180));
		ArrayE.add(new Enemy(x,0,130,130,1,2));
	    }

    }

    public void createEnemy6() {
	if (count>1000) { count=0;}
	count++;
	int x=(int) (Math.random()*(GameMain.GAME_WIDTH-180));
	
	if(count % 20 == 0) {
	    createNiu1(x);
	}

	if(count % 30 == 0) {
	    createGao(x);
	}

	if(count % 50 == 0) {
	    createFire(x);
	}

	if(count % 40 == 0) {
	    createTu(x);
	}
	
	if(count % 10 == 0) {
	    createYu(x);
	}
    }

    public void createEnemy7() {
	    	if(BossCreated == false) {
	   		bs=new Boss(200,50,300,150,100);

	    BossCreated = true;
	}
	
	if (count>500) { count=0;}
		count++;
		if (count < 150) {
		      if(count % 10 == 0) {
			bs.shoot3(mc);	        
		    }
		}

		else if(count < 300) {
		    if(count % 4 == 0) {
			bs.shoot2();	        
		    }
		}
		
		else {
		    for(int i=0; i<20; i++) {
			bs.shoot();
	        
		    }
		}

    }

    public void createEnemy8() {
	if (count>100) { count=0;}
	count++;

    }

    public void createEnemy9() {
	if (count>100) { count=0;}
	count++;

    }

    public void createEnemy10() {
	if (count>100) { count=0;}
	count++;

    }

    public void createNiao(int x) {
	ArrayE.add(new Enemy(x,-100,200,100,3,1));
    }

    public void createNiu2(int x) {
	ArrayE.add(new Enemy(x,-60,60,60,1,2));
    }
    
    public void createFrog(int x) {
	ArrayE.add(new Enemy(x,-250,250,250,150,3));
    }
    
    public void createBigFrog(int x) {
	ArrayE.add(new Enemy(x,-600,600,600,1000,4));
    }
    
    public void createNiu1(int x) {
	ArrayE.add(new Enemy(x,-80,100,80,3,5));
    }
    
    public void createFire(int x) {
	ArrayE.add(new Enemy(x,-150,100,150,5,6));
    }
    
    public void createGao(int x) {	
	ArrayE.add(new Enemy(x,-160,80,160,4,7));
    }
    
    public void createTu(int x) {
	ArrayE.add(new Enemy(x,-80,80,80,2,8));
    }
    
    public void createYu(int x) {
	ArrayE.add(new Enemy(x,-40,40,40,1,9));
    }
	
}
