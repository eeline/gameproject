package characters;

import java.awt.Image;

import main.Background;
import main.MainLoop;

public class PlayerCharacter extends Position {
	private final Attributes attributes;
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
		this.attributes = new Attributes(10, 10, 10);
		this.defaultImage = defaultImage;
		this.jumpingImage = jumpingImage;
		this.duckingImage = duckingImage;
	}

	/**
	 * updates X and Y position. Not sure about the 150 number
	 */
	public void update() {
		super.update(firstBackground, secondBackground);
	}

	/**
	 * @return the centerX
	 */

	public void move(final int moveKey) {
		super.move(moveKey);
	}

	public void stop(final int moveKey) {
		super.stop(moveKey);
	}

	public Image getSprite() {
		final int key = super.positionCheck();
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
		return super.getCenterX();
	}
	public int getCenterY(){
		return super.getCenterY();
	}
}