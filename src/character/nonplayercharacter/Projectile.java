package character.nonplayercharacter;

import main.MainLoop;

public class Projectile extends NonPlayerCharacter {
	private boolean visible;

	public Projectile(int health, int power, int speed, int startX, int startY, boolean visible) {
		super(health, power, speed);
		this.centerX = startX;
		this.centerY = startY;
		this.visible = visible;
	}
	/**
	 * this isn't going to ever function, do not use it
	 */
	@Deprecated
	@Override
	public void die() {
		// TODO Auto-generated method stub
		
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
	public void update(){
		this.centerX += this.speedX;
		if(this.centerX > MainLoop.DIM_X)
			this.visible = false;
	}
}
