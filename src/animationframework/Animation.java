package animationframework;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Animation {
	private List<Frame> frames;
	int count = 0;
	private int currentFrame;
	private long animationTime;

	/**
	 * <b>IMPORTANT:</b> A duration of -1 can only be used for the [0] frame, and
	 * will always play that frame. </n>Both arrays must match and be in order
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
		if (this.frames.get(0).getEndTime() == -1) {
			this.currentFrame = 0;
			return;
		}

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

	public synchronized void paint(int x, int y) {
		this.frames.get(this.getCurrentFrame()).paint(x, y);
	}

	private int getCurrentFrame() {
		if (this.currentFrame >= this.count) {
			this.currentFrame = 0;
			return this.currentFrame;
		}
		return this.currentFrame;
	}
}
