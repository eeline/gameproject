package animationframework;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Animation {
	private List<Frame> frames;
	private int currentFrame;
	private long animationTime;
	private long totalDuration;

	/**
	 * both arrays must match and be in order
	 * 
	 * @param images
	 * @param duration
	 */
	public Animation(Image[] images, int[] duration) {
		this.totalDuration = 0;
		synchronized (this) {
			this.animationTime = 0;
			this.currentFrame = 0;
		}
		this.frames = Collections.synchronizedList(new ArrayList<Frame>());
		for (int i = 0; i < images.length; i++) {
			frames.add(new Frame(images[i], duration[i]));
			this.totalDuration += duration[i];
		}
	}

	public synchronized void update(long elapsedTime) {
		if (this.frames.size() > 1) {
			this.animationTime += elapsedTime;
			if (this.animationTime >= this.totalDuration) {
				this.animationTime = this.animationTime % this.totalDuration;
				this.currentFrame = 0;
			}

			while (this.animationTime > this.frames.get(this.currentFrame).endTime) {
				this.currentFrame++;
			}
		}
	}

	public synchronized void paint(Graphics g, ImageObserver ob) {
		for (Frame f : this.frames)
			f.paint(g, ob);
	}

	private class Frame {
		private Image image;
		private long endTime;

		public Frame(Image image, long totalDuration) {
			// TODO Auto-generated constructor stub
		}

		public void paint(Graphics g, ImageObserver ob) {
			// TODO Auto-generated method stub

		}

	}
}
