/**
 * 
 */
package characters;

import java.awt.Image;
import java.lang.Math;

/**
 * @author ee
 * 
 */
public class HelicopterEnemy extends NonPlayerCharacter {
	private Image image;
	private boolean alternate = true;
	private final int initialCenterY;
	private final int tooHighCenterY;

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
		this.tooHighCenterY = initialCenterY -80;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see characters.NonPlayerCharacter#die()
	 */
	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

	@Override 
	public void update(){
		centerX += this.speedX;
		this.speedX = this.attributes.getAttribute(MOVE_SPEED);

		final int random = (int)Math.random() +1;
		if(this.alternate){
			this.centerY += random;

		} else {
			this.centerY -= random;
		}
		if(this.centerY > this.initialCenterY ){
			this.centerY = this.initialCenterY;
			this.alternate = !this.alternate;
		}else if(this.centerY < this.tooHighCenterY ){
			this.centerY = this.tooHighCenterY;
			this.alternate = !this.alternate;
		}
	}	

	/*
	 * (non-Javadoc)
	 * 
	 * @see characters.NonPlayerCharacter#attack()
	 */
	@Override
	public void attack() {
		// TODO Auto-generated method stub

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
}
