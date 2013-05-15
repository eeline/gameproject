package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Painter {
	private static Painter instance;
	private Graphics g;
	private final ImageObserver ob;
	
	private Painter(ImageObserver ob){
		this.ob = ob;
	}
	
	public static void init(ImageObserver ob){
		if(instance == null)
			instance = new Painter(ob);
	}
	
	public static void paint(Image image, int x, int y){
		instance.g.drawImage(image, x, y, instance.ob);
	}
	
	public static void paint(int x, int y, int width, int height, Color c){
		instance.g.setColor(c);
		instance.g.drawRect(x, y, width, height);
	}
	
	public static void setG(Graphics g){
		instance.g = g;
	}
}
