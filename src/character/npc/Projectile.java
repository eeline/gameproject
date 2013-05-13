package character.npc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import main.MainLoop;

public class Projectile extends NonPlayerCharacter {
	private boolean visible;

	/**
	 * 
	 * @param health
	 *            irrelevant for this item and can be set to an arbitrary number
	 * @param power
	 *            irrelevant for this item and can be set to an arbitrary number
	 * @param speed
	 *            sets speed of motion, recommend 7 to 10
	 * @param startX
	 *            sets starting position (originate near the attacking enemy)
	 * @param startY
	 *            sets starting position (originate near the attacking enemy)
	 * @param visible
	 *            sets whether the bullet is visible or not
	 */
	public Projectile(int health, int power, int speed, int startX, int startY,
			boolean visible) {
		super(health, power, speed);
		this.centerX = startX;
		this.centerY = startY;
		this.visible = visible;
	}

	/**
	 * sets to invisible, can also be used to assign a new active sprite (eg:
	 * explosion)
	 */

	@Override
	public void die() {
		this.visible = false;
		// TODO remove from the container

	}

	/**
	 * this isn't ever going to function, do not use it
	 */
	@Deprecated
	@Override
	public void attack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		this.centerX += this.speedX;
		if (this.centerX > MainLoop.DIM_X)
			this.visible = false;
	}

	public boolean isVisible() {
		return this.visible;
	}

	@Override
	public void paint(Graphics g, ImageObserver ob) {
		g.setColor(Color.BLACK);
		g.fillRect(this.centerX, this.centerY, 10, 5);
	}
}
