package character;

import java.awt.Graphics;
import java.awt.image.ImageObserver;




public abstract class NonPlayerCharacter extends Position {
	protected final Attributes attributes;
	
	public NonPlayerCharacter(int health, int power) {
		attributes = new Attributes(health, power);
	}

	public abstract void update();

	public abstract void die();

	public abstract void attack();
	
	public abstract void paint(Graphics g, ImageObserver ob);
}
