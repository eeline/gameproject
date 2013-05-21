package character.player;

import main.MainLoop;
import animation.Animation;
import character.Attributes;
import character.Position;
import character.weapon.Projectiles;

public class PlayerCharacter extends Position {
	@SuppressWarnings("unused")
	private final Attributes attributes;
	private Animation blink;
	private Animation jump;
	private Animation duck;
	public static final int Y_OFFSET = 61; // related to character
	public static final int X_OFFSET = 63; // related to character

	public static final int DEFAULT_SPRITE = 0;
	public static final int JUMP_SPRITE = 1;
	public static final int DUCK_SPRITE = 2;

	/**
	 * projectile constant
	 */
	private static final int OFFSET = 50;

	/**
	 * 
	 * @param blink
	 * @param jump
	 * @param duck
	 */
	public PlayerCharacter(Animation blink, Animation jump, Animation duck) {
		this.attributes = new Attributes(10, 10);
		this.duck = duck;
		this.jump = jump;
		this.blink = blink;
	}

	/**
	 * updates X and Y position. Not sure about the 150 number
	 */
	public void update(long elapsedTime) {
		if (this.speedX < 0)
			this.centerX += this.speedX;
		if (this.speedX == 0 || this.speedX < 0) {
			super.isBackgroundPaused = true;
		}

		if (this.centerX <= SCROLL_BORDER && this.speedX > 0)
			this.centerX += this.speedX;

		if (this.speedX > 0 && this.centerX > 200) {
			super.isBackgroundPaused = false;
		}

		// manage Y transition
		this.centerY += speedY;
		if (this.centerY + this.speedY >= GROUND)
			this.centerY = GROUND;
		// manage "jumping"
		if (isJumped) {
			this.speedY += 1;
			if (this.centerY + this.speedY >= GROUND) {
				this.centerY = GROUND;
				isJumped = !isJumped;
			}
		}

		// enforces zero bound
		if (this.centerX + this.speedX <= ZERO_BOUND_X)
			this.centerX = ZERO_BOUND_X + 1;

		this.blink.update(elapsedTime);
		this.jump.update(elapsedTime);
		this.duck.update(elapsedTime);
	}

	/**
	 * @return the centerX
	 */

	public void move(final int moveKey) {
		if (moveKey == Position.MOVE_ATTACK) {
			if (!this.isDucking)
				attack();
			return;
		}

		else
			super.move(moveKey);
	}

	public void stop(final int moveKey) {
		super.stop(moveKey);
	}

	public void paint() {
		final int key = super.positionCheck();
		switch (key) {
		case DEFAULT_SPRITE:
			this.blink.paint(this.centerX - X_OFFSET, this.centerY - Y_OFFSET);
			break;
		case JUMP_SPRITE:
			this.jump.paint(this.centerX - X_OFFSET, this.centerY - Y_OFFSET);
			break;
		case DUCK_SPRITE:
			this.duck.paint(centerX - X_OFFSET, centerY - Y_OFFSET);
			break;
		default:
			return;
		}
	}

	public void attack() {
		Projectiles.generateProjectile(1, 70, this.centerX + OFFSET,
				this.centerY - (OFFSET / 2));
	}

	public int getVisibileBoundary() {
		return this.centerX + MainLoop.DIM_X;
	}

	@Deprecated
	@Override
	public void update() {

	}
}