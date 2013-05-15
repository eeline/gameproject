package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import character.Position;

public class Background {
	private int backgroundX;
	private final int backgroundY;
	private int backgroundSpeedX;
	private Image image;

	public static final int BACKGROUND_LENGTH_X = -2160;

	public Background(int x, int y, Image image) {
		this.backgroundX = x;
		this.backgroundY = y;
		this.backgroundSpeedX = 0;
		this.image = image;
	}

	public void update() {
		this.backgroundX += this.backgroundSpeedX;

		if (this.backgroundX <= BACKGROUND_LENGTH_X) {
			this.backgroundX += (BACKGROUND_LENGTH_X * -2);
		}
	}

	public void paint(Graphics g, ImageObserver ob) {
		g.drawImage(image, this.backgroundX, this.backgroundY, ob);
	}

	public void stop() {
		this.backgroundSpeedX = 0;
	}

	public void go() {
		this.backgroundSpeedX = -Position.MOVE_SPEED;
	}
}
