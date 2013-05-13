/**
 * 
 */
package character.npc.enemy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import character.npc.NonPlayerCharacter;
import character.npc.Projectile;

/**
 * @author ee
 * 
 */
public class HelicopterEnemy extends NonPlayerCharacter {
	private Image image;
	private boolean alternate = true;
	private final int initialCenterY;
	private final int tooHighCenterY;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private boolean visible;
	private static final int OFFSET = 50; // default offset for spawning bullets
											// and other models from this object

	/**
	 * @param health
	 * @param power
	 * @param speed
	 * @param centerX
	 * @param centerY
	 */
	public HelicopterEnemy(final int health, final int power, final int speed,
			final int centerX, final int centerY, Image image) {
		super(health, power, speed);
		super.centerX = centerX;
		super.centerY = centerY;
		this.image = image;
		this.initialCenterY = centerY;
		this.tooHighCenterY = initialCenterY - 80;
		this.visible = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see characters.NonPlayerCharacter#die()
	 */
	@Override
	public void die() {
		this.visible = false;

	}

	@Override
	public void update() {
		centerX += this.speedX;
		this.speedX = this.attributes.getAttribute(MOVE_SPEED);

		final int random = (int) Math.random() + 1;
		if (this.alternate) {
			this.centerY += random;

		} else {
			this.centerY -= random;
		}
		if (this.centerY > this.initialCenterY) {
			this.centerY = this.initialCenterY;
			this.alternate = !this.alternate;
		} else if (this.centerY < this.tooHighCenterY) {
			this.centerY = this.tooHighCenterY;
			this.alternate = !this.alternate;
		}

		for (Projectile projectile : this.projectiles) {
			projectile.update();
			if (!projectile.isVisible()) {
				this.projectiles.remove(projectile);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see characters.NonPlayerCharacter#attack()
	 */
	@Override
	public void attack() {
		this.projectiles.add(new Projectile(1, 1, 7, this.centerX + OFFSET,
				this.centerY - (OFFSET / 2), true));

	}

	public Image getImage() {
		return image;
	}

	public int getY() {
		return this.centerY;
	}

	public int getX() {
		return this.centerX;
	}

	public int getSpeed() {
		return this.speedX;
	}

	@Override
	public void paint(Graphics g, ImageObserver ob) {
		g.drawImage(this.getImage(), this.centerX - 48, this.centerY - 48, ob);

		for (Projectile p : this.projectiles)
			p.paint(g, ob);
	}
}
