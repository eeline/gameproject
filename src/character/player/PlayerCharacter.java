package character.player;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import main.Background;
import main.MainLoop;
import animationframework.Animation;
import character.Attributes;
import character.Position;
import character.weapon.Projectiles;

public class PlayerCharacter extends Position {
	private final Attributes attributes;
	private Image duckingImage;
	private Image jumpingImage;
	private Animation animation;
	public static final int Y_OFFSET = 61; // related to character
	public static final int X_OFFSET = 63; // related to character

	public static final int DEFAULT_SPRITE = 0;
	public static final int JUMP_SPRITE = 1;
	public static final int DUCK_SPRITE = 2;
	/**
	 * background
	 */
	private final Background first;
	private final Background second;
	/**
	 * projectile constant
	 */
	private static final int OFFSET = 50;

	public PlayerCharacter(Image jumpingImage, Image duckingImage,
			Image[] images, long[] durations, Background b1, Background b2) {
		this.attributes = new Attributes(10, 10);
		this.jumpingImage = jumpingImage;
		this.duckingImage = duckingImage;
		this.animation = new Animation(images, durations);
		this.first = b1;
		this.second = b2;
	}

	/**
	 * updates X and Y position. Not sure about the 150 number
	 */
	public void update(long elapsedTime) {
		if (this.speedX < 0)
			this.centerX += this.speedX;
		if (this.speedX == 0 || this.speedX < 0) {
			first.stop();
			second.stop();
		}

		if (this.centerX <= SCROLL_BORDER && this.speedX > 0)
			this.centerX += this.speedX;

		if (this.speedX > 0 && this.centerX > 200) {
			first.go();
			second.go();
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

		this.animation.update(elapsedTime);
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

	private void handlePaint(Graphics g, ImageObserver ob) {
		final int key = super.positionCheck();
		switch (key) {
		case DEFAULT_SPRITE:
			this.animation.paint(g, ob, this.centerX - X_OFFSET,
					this.centerY - Y_OFFSET);
			break;
		case JUMP_SPRITE:
			g.drawImage(this.jumpingImage, this.centerX
					- X_OFFSET, this.centerY
					- Y_OFFSET, ob);
			break;
		case DUCK_SPRITE:
			g.drawImage(duckingImage, centerX - X_OFFSET,
					centerY - Y_OFFSET, ob);
			break;
		default:
			return;
		}
	}

	public void paint(Graphics g, ImageObserver ob) {
		this.handlePaint(g, ob);
	}

	public void attack() {
		Projectiles.generateProjectile(1, 70, this.centerX + OFFSET,
				this.centerY - (OFFSET / 2));
	}

	@Deprecated
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}