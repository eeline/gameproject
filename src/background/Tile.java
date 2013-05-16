package background;

import animationframework.Animation;
import character.Position;

public class Tile extends Position {
	private Animation animation;
	private int type;
	
	//type constants
	public static final int OCEAN_TILE =0;
	public static final int LAND_TILE =1;
	
	public Tile(int x, int y, int speed, Animation animation, int type){
		super(x, y, speed, 0);
		this.animation = animation;
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void paint(){
		this.animation.paint(super.centerX, super.centerY);
	}
	@Deprecated
	@Override
	public void update(long elapsedTime) {
		
	}
}
