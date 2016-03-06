package survival.cs48group.game.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class Resources {
	
	public static BufferedImage welcome, flight, bullet,background,enemies,hp1,hp2,hp3,hp4,hp5,hp6;
	public static AudioClip hit;
	
	//load all the images and sound for the game
	public static void load(){
		welcome = loadImage("Menu.png");
		flight = loadImage("character11.png");
		bullet = loadImage("fireNew.png");
		background= loadImage("sky123.JPG");
		enemies = loadImage("monsterNew.png");
		hp6=loadImage("green1.png");
		hp5=loadImage("green2.png");
		hp4=loadImage("yellow1.png");
		hp3=loadImage("yellow2.png");
		hp2=loadImage("red1.png");
		hp1=loadImage("red2.png");
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