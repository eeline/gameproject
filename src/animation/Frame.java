package animation;

import java.awt.Image;

import main.Painter;

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
	void paint(int x, int y) {
		Painter.paint(image, x, y);
	}

	long getEndTime() {
		return this.endTime;
	}
}
