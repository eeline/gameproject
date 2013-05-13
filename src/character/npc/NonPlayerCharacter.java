package character.npc;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import character.Attributes;
import character.Position;



public abstract class NonPlayerCharacter extends Position {
	protected final Attributes attributes;
	
	public NonPlayerCharacter(int health, int power, int speed) {
		attributes = new Attributes(health, power, speed);
	}

	public void update() {
		centerX += speedX;
		speedX=this.attributes.getAttribute(MOVE_SPEED);
	}

	public abstract void die();

	public abstract void attack();
	
	public abstract void paint(Graphics g, ImageObserver ob);
}
