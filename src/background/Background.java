package background;

import animation.Animation;
import character.Position;

public class Background extends Position{
	private Animation animation;

	public static final int BACKGROUND_LENGTH_X = -2160;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param image
	 */
	
	/* 
	 * TODO make background compatible with the animation framework
	 */
	public Background(int x, int y, Animation animation) {
		super(x, y, 0, 0);
		this.animation = animation;
	}
	@Override
	public void update() {
		super.centerX += super.speedX;

		if (super.centerX <= BACKGROUND_LENGTH_X) {
			super.centerX+= (BACKGROUND_LENGTH_X * -2);
		}
	}

	public void paint() {
		this.animation.paint(super.centerX, super.centerY);
	}

	public void stop() {
		super.speedX = 0;
	}

	public void go() {
		super.speedX = -Position.MOVE_SPEED/5;
	}
	
	public boolean isStopped(){
		return this.speedX ==0;
	}
	@Override
	@Deprecated
	public void update(long elapsedTime) {
		
	}
}
