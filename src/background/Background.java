package background;

import animationframework.Animation;
import character.Position;

public class Background {
	private int backgroundX;
	private final int backgroundY;
	private int backgroundSpeedX;
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
		this.backgroundX = x;
		this.backgroundY = y;
		this.backgroundSpeedX = 0;
		this.animation = animation;
	}

	public void update() {
		this.backgroundX += this.backgroundSpeedX;

		if (this.backgroundX <= BACKGROUND_LENGTH_X) {
			this.backgroundX += (BACKGROUND_LENGTH_X * -2);
		}
	}

	public void paint() {
		this.animation.paint(this.backgroundX, this.backgroundY);
	}

	public void stop() {
		this.backgroundSpeedX = 0;
	}

	public void go() {
		this.backgroundSpeedX = -Position.MOVE_SPEED;
	}
}
