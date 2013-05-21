package background;

import animation.Animation;
import character.Position;

public class Tile extends Position {
	private Animation animation;
	private int type;
	private static final int SIZE_MULT = 40; // creating through indicies, need
												// to mult * 40 to get pixel
												// size

	// type constants
	public static final int OCEAN_TILE = 0;
	public static final int DIRT_TILE = 1;

	// speeds
	public static final int OCEAN_SPEED = -Tile.MOVE_SPEED *2;
	public static final int DIRT_SPEED = -Tile.MOVE_SPEED;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param speed
	 * @param animation
	 * @param type
	 */
	public Tile(int x, int y, int speed, Animation animation, int type) {
		super(x * SIZE_MULT, y * SIZE_MULT, speed, 0);
		this.animation = animation;
		this.type =type;
	}

	@Override
	public void update() {
		if(type == OCEAN_TILE){
			if(isBackgroundPaused)
				this.speedX =OCEAN_SPEED *2;
			else
				this.speedX =OCEAN_SPEED *2;
		}else
			this.speedX = DIRT_SPEED;
			

	}

	public void paint() {
		this.animation.paint(super.centerX, super.centerY);
	}

	@Deprecated
	@Override
	public void update(long elapsedTime) {

	}
}
