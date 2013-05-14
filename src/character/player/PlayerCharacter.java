package character.player;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import main.Background;
import main.MainLoop;
import character.Attributes;
import character.Position;
import character.weapon.Projectiles;

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
	private static final Background first;
	private static final Background second;
	/**
	 * projectile constant
	 */
	private static final int OFFSET = 50;

	static {
		first = MainLoop.getBackground(MainLoop.FIRST_BACKGROUND);
		second = MainLoop.getBackground(MainLoop.SECOND_BACKGROUND);
	}

	public PlayerCharacter(Image defaultImage, Image jumpingImage,
			Image duckingImage) {
		this.attributes = new Attributes(10, 10);
		this.defaultImage = defaultImage;
		this.jumpingImage = jumpingImage;
		this.duckingImage = duckingImage;
	}

	/**
	 * updates X and Y position. Not sure about the 150 number
	 */
	public void update() {
		if (this.speedX < 0)
			this.centerX += this.speedX;
		if (this.speedX == 0 || this.speedX < 0) {
			first.setBackgroundSpeedX(0);
			second.setBackgroundSpeedX(0);
		}

		if (this.centerX <= SCROLL_BORDER && this.speedX > 0)
			this.centerX += this.speedX;

		if (this.speedX > 0 && this.centerX > 200) {
			first.setBackgroundSpeedX(-MOVE_SPEED);
			second.setBackgroundSpeedX(-MOVE_SPEED);
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

	}

	/**
	 * @return the centerX
	 */

	public void move(final int moveKey) {
		if (moveKey == Position.MOVE_ATTACK) {
			if (!this.isDucking && !this.isJumped)
				attack();
			return;
		}

		else
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

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void paint(Graphics g, ImageObserver ob) {
		g.drawImage(this.getSprite(), this.getCenterX()
				- MainLoop.MAGIC_NUMBER_X, this.getCenterY()
				- MainLoop.MAGIC_NUMBER_Y, ob);
	}

	public void attack() {
		Projectiles.generateProjectile(1, 70, this.centerX + OFFSET,
				this.centerY - (OFFSET / 2));
	}
}