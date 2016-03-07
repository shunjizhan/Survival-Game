package survival.cs48group.game.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class Resources {
	
    public static BufferedImage welcome, flight, bullet,bullet2,background,enemies,hpitem,bulletitem,bombitem,hp,bomb;
	public static AudioClip hit;
	
	//load all the images and sound for the game
	public static void load(){
		welcome = loadImage("background1.png");
		flight = loadImage("character.png");
		bullet = loadImage("bigbullet.png");
		bullet2 = loadImage("bullet.png");
		background= loadImage("ingamebackground.png");
		enemies = loadImage("monsterNew.png");
		hpitem=loadImage("hpitem.png");
		bulletitem=loadImage("item2.png");
		bombitem=loadImage("item3.png");
		hp=loadImage("hppart2.png");
		bomb=loadImage("bomb.png");
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
