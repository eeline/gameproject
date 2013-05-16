package character;

import character.player.PlayerCharacter;

public abstract class Position {
	/**
	 * character information
	 */
	protected int centerX = 100;
	protected int centerY = GROUND;
	protected boolean isJumped = false;
	protected boolean isMovingLeft = false;
	protected boolean isMovingRight = false;
	protected boolean isDucking = false;
	protected boolean isBackgroundPaused = false;
	protected int speedX = 0;
	protected int speedY = 1;

	private boolean justDucked;
	/**
	 * private movement constants
	 */
	protected static final int JUMP_SPEED = -15;
	public static final int MOVE_SPEED = 5;

	/**
	 * border constants
	 */
	protected static final int GROUND = 382;
	protected static final int SCROLL_BORDER = 300;
	protected static final int ZERO_BOUND_X = 60;
	/**
	 * public directional indicator constants
	 * 
	 */
	public static final int MOVE_RIGHT = 1;
	public static final int MOVE_LEFT = 2;
	public static final int MOVE_DUCK = 3;
	public static final int MOVE_JUMP = 4;
	public static final int MOVE_FLY = 5;
	public static final int MOVE_ATTACK = 0;

	/**
	 * sprite keys
	 */
	protected Position() {

	}

	/**
	 * 
	 * <b>IMPORTANT</b> when adding a new class that implements position, pick
	 * one of the two update methods and deprecate the other
	 */
	public abstract void update();

	public abstract void update(long elapsedTime);

	protected void move(final int moveKey) {
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
			if (!this.isJumped) {
				this.speedY = JUMP_SPEED;
				this.isJumped = !isJumped;
			}
			break;
		case MOVE_FLY:
			this.speedY = JUMP_SPEED;
			this.isJumped = true;
			break;
		case MOVE_DUCK:
			this.isDucking = true;
			stop();
			break;
		}
	}

	protected void stop(final int moveKey) {
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

	protected boolean isMovingOnX() {
		if ((this.isMovingLeft || this.isMovingRight) && !this.justDucked)
			return true;
		else
			return false;
	}

	protected int positionCheck() {

		if (this.isJumped) {
			return PlayerCharacter.JUMP_SPRITE;
		} else if (this.isDucking && isMovingOnX()) {
			return PlayerCharacter.DEFAULT_SPRITE;
		} else if (this.isDucking) {
			return PlayerCharacter.DUCK_SPRITE;
		} else if (!this.isJumped && !this.isDucking) {
			return PlayerCharacter.DEFAULT_SPRITE;
		} else
			return -1;
	}
	
	public boolean isBackgroundPaused(){
		return this.isBackgroundPaused;
	}
}
