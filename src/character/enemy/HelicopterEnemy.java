/**
 * 
 */
package character.enemy;

import java.awt.Image;

import animationframework.Animation;
import character.NonPlayerCharacter;
import character.weapon.Projectiles;

/**
 * @author ee
 * 
 */
public class HelicopterEnemy extends NonPlayerCharacter {
	private long durationCount = 0;
	private boolean alternate = true;
	private final int initialCenterY;
	private final int tooHighCenterY;
	private boolean visible;
	private Animation animation;

	// TODO implement visible check for right side X that turns off projectile
	// firing and painting

	/**
	 * 
	 * @param health
	 * @param power
	 * @param speedX
	 * @param speedY
	 * @param centerX
	 * @param centerY
	 * @param images
	 * @param durations
	 */
	public HelicopterEnemy(final int health, final int power, final int speedX,
			final int speedY, final int centerX, final int centerY,
			final Image[] images, final long[] durations) {
		super(health, power);
		super.centerX = centerX;
		super.centerY = centerY;
		this.initialCenterY = centerY;
		this.tooHighCenterY = initialCenterY - 80;
		this.visible = true;
		this.animation = new Animation(images, durations);
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

	@Deprecated
	@Override
	public void update() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see characters.NonPlayerCharacter#attack()
	 */
	@Override
	public void attack() {
		if (this.visible)
			Projectiles.generateProjectile(1, -7, this.centerX,
					this.centerY + 5);
	}

	@Override
	public void paint() {
		if (this.visible)
			this.animation.paint(this.centerX - 48, this.centerY - 48);
	}

	@Override
	public void update(long elapsedTime) {
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
		this.durationCount += elapsedTime;
		this.animation.update(elapsedTime);

		if (this.durationCount >= 1000) {
			this.durationCount = 0;
			this.attack();
		}
	}

	public void setVisibleBoundary(int visibleBoundary) {
		this.visible = (this.centerX + this.speedX) <= visibleBoundary +200;
	}
}
