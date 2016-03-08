package survival.cs48group.game.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class Resources {
	
    public static BufferedImage boss,bulletE,welcome, flight, bullet,bullet2,background,enemies,hpitem,bulletitem,bombitem,hp,bomb,enemy2,frog,frog600;
	public static AudioClip hit;
	
	//load all the images and sound for the game
	public static void load(){
		welcome = loadImage("latestbackground2.png");
		flight = loadImage("character.png");
		bullet = loadImage("charbigbullet.png");
		bullet2 = loadImage("charsmallbullet.png");
		bulletE = loadImage("enemybullet.png");
		boss = loadImage("boss.png");
	
		background= loadImage("ingamebackground2.png");
		enemies = loadImage("birdboss.png");
		hpitem=loadImage("hpitem.png");
		bulletitem=loadImage("item2.png");
		bombitem=loadImage("item3.png");
		hp=loadImage("lifepart.png");
		bomb=loadImage("killpart.png");
		enemy2 = loadImage("niu2.png");
		frog = loadImage("frog.png");
		frog600 = loadImage("frog600.png");
	}
	
	
	
	//method for loading image
	private static BufferedImage loadImage(String filename){
		BufferedImage img = null;
		try {
			img = ImageIO.read(Resources.class.getResourceAsStream("/resources/" +filename));
		}catch (Exception e){
			System.out.println("Error while reading: " + filename);
			e.printStackTrace();
		}
		return img;
	}

}
