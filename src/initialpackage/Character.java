package initialpackage;

public class Character {
	private int centerX = 100;
	private int centerY = 382;
	private boolean jumped = false;

	private int speedX = 0;
	private int speedY = 1;

	private static final int ZERO = 0;
	private static final int INITIAL_CENTER_Y = 382;
	private static final int MAGIC_NUMBER_X = 150;// the fuck is this constant?
	private static final int ZERO_BOUND_X = 60; //further left than this puts the character's hand outside screen
	@SuppressWarnings("unused")
	private static final int GROUND_LEVEL_Y = 440; //ergo feet level is apparently INITIAL_CENTER_Y

	public static final int MOVE_RIGHT = 0;
	public static final int MOVE_LEFT = 1;
	public static final int STOP_MOVEMENT = 2;
	public static final int JUMP = 3;

	/**
	 * updates X and Y position. Not sure about the 150 number
	 */
	public void update() {
		// manage X transition
		if (this.speedX < ZERO)
			this.centerX += this.speedX;
		else if (this.speedX == ZERO){
			
		}
			
		else {
			if (this.centerX <= MAGIC_NUMBER_X) // the fuck is this digit?
				this.centerX += this.speedX;
			else
				System.out.println("scroll background");
		}

		// manage Y transition
		if (this.centerY + this.speedY >= INITIAL_CENTER_Y)
			this.centerY = INITIAL_CENTER_Y;
		else
			this.centerY += this.speedY;

		// manage "jumping"
		if (this.jumped) {
			this.speedY += 1;
			if (this.centerY + this.speedY >= INITIAL_CENTER_Y) {
				this.centerY = INITIAL_CENTER_Y;
				this.speedY = ZERO;
				this.jumped = false;
			}
		}

		// enforces zero bound
		if (this.centerX + this.speedX <= ZERO_BOUND_X)
			this.centerX = ZERO_BOUND_X + 1;
	}
	/**
	 * 
	 * @param MOVEMENT_TYPE use one of the static movement types available to indicate
	 * direction of movement, cessation of movement or jumping
	 */
	public void movement(final int MOVEMENT_TYPE) {
		switch (MOVEMENT_TYPE) {
		case MOVE_LEFT:
			this.speedX = -6;
			break;
		case MOVE_RIGHT:
			this.speedX = 6;
			break;
		case STOP_MOVEMENT:
			this.speedX = 0;
			break;
		case JUMP:
			if(!jumped){
				this.speedY = -15;
				this.jumped = !jumped;
			}
		}

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
}