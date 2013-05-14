package animationframework;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Frame {
	private Image image;
	private long endTime;

	/**
	 * 
	 * @param image
	 *            image for a particular frame of a sprite
	 * @param endTime
	 *            time in ms from last frame that this will run
	 */
	Frame(Image image, long endTime) {
		this.image = image;
		this.endTime = endTime;
	}
	void paint(Graphics g, ImageObserver ob, int x, int y) {
		g.drawImage(image, x, y, ob);
	}

	long getEndTime() {
		return this.endTime;
	}
}
