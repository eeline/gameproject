package characters;

import java.awt.Image;

import main.Background;
import main.MainLoop;

public class PlayerCharacter extends Attributes {
	Position position = new Position();
	private Image defaultImage;
	private Image duckingImage;
	private Image jumpingImage;

	public static final int DEFAULT_SPRITE = 0;
	public static final int JUMP_SPRITE = 1;
	public static final int DUCK_SPRITE = 2;
	/**
	 * background
	 */
	private static final Background firstBackground;
	private static final Background secondBackground;

	static {
		firstBackground = MainLoop.getBackground(MainLoop.FIRST_BACKGROUND);
		secondBackground = MainLoop.getBackground(MainLoop.SECOND_BACKGROUND);
	}

	public PlayerCharacter(Image defaultImage, Image jumpingImage,
			Image duckingImage) {
		super(10, 10, 10);
		this.defaultImage = defaultImage;
		this.jumpingImage = jumpingImage;
		this.duckingImage = duckingImage;
	}

	/**
	 * updates X and Y position. Not sure about the 150 number
	 */
	public void update() {
		this.position.update(firstBackground, secondBackground);
	}

	/**
	 * @return the centerX
	 */

	public void move(final int moveKey) {
		this.position.move(moveKey);
	}

	public void stop(final int moveKey) {
		this.position.stop(moveKey);
	}

	public Image getSprite() {
		final int key = this.position.positionCheck();
		switch (key) {
		case DEFAULT_SPRITE:
			return this.defaultImage;
		case JUMP_SPRITE:
			return this.jumpingImage;
		case DUCK_SPRITE:
			return this.duckingImage;
		default:
			return null;
		}
	}
	
	public int getCenterX(){
		return this.position.getCenterX();
	}
	public int getCenterY(){
		return this.position.getCenterY();
	}
}