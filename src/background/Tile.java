package background;

import animationframework.Animation;
import character.Position;

public class Tile extends Position {
	private Animation animation;
	private int type;
	private static final int SIZE_MULT = 40; //creating through indicies, need to mult * 40 to get pixel size

	// type constants
	public static final int OCEAN_TILE = 0;
	public static final int LAND_TILE = 1;

	public Tile(int x, int y, int speed, Animation animation, int type) {
		super(x * SIZE_MULT, y * SIZE_MULT, speed, 0);
		this.animation = animation;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public void paint() {
		this.animation.paint(super.centerX, super.centerY);
	}

	@Deprecated
	@Override
	public void update(long elapsedTime) {

	}
}
