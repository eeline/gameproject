package initialpackage;

import java.awt.Image;

public class Character extends Attributes{
	/**
	 * character information
	 */
	private int centerX = 100;
	private int centerY = GROUND;
	private boolean jumped = false;
	private boolean isMovingLeft = false;
	private boolean isMovingRight = false;
	private boolean isDucking = false;
	private int speedX = 0;
	private int speedY = 1;

	private Image defaultImage;
	private Image duckingImage;
	private Image jumpingImage;
	private boolean justDucked;
	/**
	 * private movement constants
	 */
	private static final int JUMP_SPEED = -15;
	private static final int MOVE_SPEED = 5;

	/**
	 * border constants
	 */
	private static final int GROUND = 382;
	private static final int SCROLL_BORDER = 200;
	private static final int ZERO_BOUND_X = 60;
	/**
	 * public directional indicator constants
	 * 
	 */
	public static final int MOVE_RIGHT = 1;
	public static final int MOVE_LEFT = 2;
	public static final int MOVE_DUCK = 3;
	public static final int MOVE_JUMP = 4;
	public static final int MOVE_FLY = 5;
	/**
	 * sprite keys
	 */
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

	public Character(Image defaultImage, Image jumpingImage, Image duckingImage) {
		super(10, 10, 10);
		this.defaultImage = defaultImage;
		this.jumpingImage = jumpingImage;
		this.duckingImage = duckingImage;
	}

	/**
	 * updates X and Y position. Not sure about the 150 number
	 */
	public void update() {
		// manage X transition
		if (this.speedX < 0)
			this.centerX += this.speedX;
		if (this.speedX == 0 || this.speedX < 0) {
			Character.firstBackground.setBackgroundSpeedX(0);
			Character.secondBackground.setBackgroundSpeedX(0);
		}

		if (this.centerX <= SCROLL_BORDER && this.speedX > 0)
			this.centerX += this.speedX;

		if (this.speedX > 0 && this.centerX > 200) {
			Character.firstBackground.setBackgroundSpeedX(-MOVE_SPEED);
			Character.secondBackground.setBackgroundSpeedX(-MOVE_SPEED);
		}

		// manage Y transition
		this.centerY += speedY;
		if (this.centerY + this.speedY >= GROUND)
			this.centerY = GROUND;
		// manage "jumping"
		if (this.jumped) {
			this.speedY += 1;
			if (this.centerY + this.speedY >= GROUND) {
				this.centerY = GROUND;
				this.jumped = !jumped;
			}
		}

		// enforces zero bound
		if (this.centerX + this.speedX <= ZERO_BOUND_X)
			this.centerX = ZERO_BOUND_X + 1;
	}

	/**
	 * @return the centerX
	 */
	public int getCenterX() {
		return centerX;
	}

	/**
	 * @return the centerY
	 */
	public int getCenterY() {
		return centerY;
	}

	/**
	 * @return the jumped
	 */
	public boolean isJumped() {
		return jumped;
	}

	/**
	 * @return the speedX
	 */
	public int getSpeedX() {
		return speedX;
	}

	/**
	 * @return the speedY
	 */
	public int getSpeedY() {
		return speedY;
	}

	public void move(final int moveKey) {
		switch (moveKey) {
		case MOVE_RIGHT:
			this.speedX = MOVE_SPEED;
			this.isMovingRight = true;
			this.justDucked = false;
			break;
		case MOVE_LEFT:
			this.speedX = -MOVE_SPEED;
			this.isMovingLeft = true;
			this.justDucked = false;
			break;
		case MOVE_JUMP:
			if (!this.jumped) {
				this.speedY = JUMP_SPEED;
				this.jumped = !jumped;
			}
			break;
		case MOVE_FLY:
			this.speedY = JUMP_SPEED;
			this.jumped = true;
			break;
		case MOVE_DUCK:
			this.isDucking = true;
			stop();
			break;
		}
	}

	public void stop(final int moveKey) {
		switch (moveKey) {
		case MOVE_RIGHT:
			this.isMovingRight = false;
			stop();
			break;
		case MOVE_LEFT:
			this.isMovingLeft = false;
			stop();
			break;
		case MOVE_DUCK:
			this.isDucking = false;
			break;
		}
	}

	private void stop() {
		if (this.isDucking) {
			this.speedX = 0;
			this.justDucked = true;
			return;
		} else {
			if (!this.isMovingLeft && !this.isMovingRight)
				this.speedX = 0;
			if (!this.isMovingLeft && this.isMovingRight)
				this.move(MOVE_RIGHT);
			if (this.isMovingLeft && !this.isMovingRight)
				this.move(MOVE_LEFT);
		}
	}

	public Image getSprite(int key) {
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
	public boolean isMovingOnX(){
		if((this.isMovingLeft || this.isMovingRight) && !this.justDucked)
			return true;
		else return false;
	}
	public boolean isDucked() {
		return this.isDucking;
	}
}