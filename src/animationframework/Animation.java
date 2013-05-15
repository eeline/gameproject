package animationframework;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Animation {
	private List<Frame> frames;
	int count = 0;
	private int currentFrame;
	private long animationTime;

	/**
	 * both arrays must match and be in order
	 * 
	 * @param images
	 * @param duration
	 */
	public Animation(Image[] images, long[] duration) {
		synchronized (this) {
			this.animationTime = 0;
			this.currentFrame = 0;
		}
		this.frames = Collections.synchronizedList(new ArrayList<Frame>());
		for (int i = 0; i < images.length; i++) {
			this.frames.add(new Frame(images[i], duration[i]));
			this.count++;
		}
	}

	public synchronized void update(long elapsedTime) {
		if (this.currentFrame >= count)
			this.currentFrame = 0;
		if (this.frames.size() > 1)
			this.animationTime += elapsedTime;
		if (this.animationTime > this.frames.get(this.currentFrame)
				.getEndTime()) {
			this.animationTime = 0;
			this.currentFrame++;
		}
	}

	public synchronized void paint(Graphics g, ImageObserver ob, int x, int y) {
		this.frames.get(this.getCurrentFrame()).paint(g, ob, x, y);
	}

	private int getCurrentFrame() {
		if (this.currentFrame >= this.count) {
			this.currentFrame = 0;
			return this.currentFrame;
		}
		return this.currentFrame;

	}
}
