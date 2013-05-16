package main;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

import animationframework.Animation;
import character.Position;
import character.enemy.HelicopterEnemy;
import character.player.PlayerCharacter;
import character.weapon.Projectiles;

public class MainLoop extends Applet implements Runnable {
	// auto generated sUID to satisfy the warning gods
	private static final long serialVersionUID = 1560999524005463670L;

	// important constants that are only needed here
	public static final int DIM_X = 800; // frame size info
	public static final int DIM_Y = 480; // frame size info
	private static final String GAME_NAME = "Game Name Here"; // title
	public static final int ELAPSED_TIME = 17; // 17ms gives 60fps, this is a
												// tick

	// which background is it?
	public static final int FIRST_BACKGROUND = 0;
	public static final int SECOND_BACKGROUND = 1;

	// component objects that need to be updated and maintained
	private PlayerCharacter mainCharacter;
	private HelicopterEnemy heliBadGuy;
	private Image image;
	private Graphics second;

	// imageloader and background
	private ImageLoader loader;
	private Background firstBackground, secondBackground;

	/** 
	 * 
	 */
	@Override
	public void init() {
		this.loader = new ImageLoader();
		// initial frame generation
		this.setSize(DIM_X, DIM_Y);
		this.setBackground(Color.BLACK);
		this.setFocusable(true);

		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle(GAME_NAME);

		// resource initializations
		/*
		 * animation times (in ms) are set in main loop b/c images are set here
		 * might be smarter ways of doing this
		 */

		this.firstBackground = new Background(0, 0, this.loader.get(this,
				ImageLoader.BACKGROUND)[0]);

		this.secondBackground = new Background(Background.BACKGROUND_LENGTH_X,
				0, this.loader.get(this, ImageLoader.BACKGROUND)[0]);

		final long[] blinkDuration = { 1250, 50, 50, 50 };
		final long[] jumpDuration = { -1 };
		final long[] duckDuration = { -1 };

		this.mainCharacter = new PlayerCharacter(new Animation(this.loader.get(
				this, ImageLoader.PLAYER_BLINK_ANIMATION_KEY), blinkDuration),
				new Animation(this.loader
						.get(this, ImageLoader.PLAYER_JUMP_KEY), jumpDuration),
				new Animation(this.loader
						.get(this, ImageLoader.PLAYER_DUCK_KEY), duckDuration));
		/*
		 * location of heliBadguy on x axis is randomized with magic numbers for
		 * now. later probably going to have a more involved method of placing
		 * badguys based on score.
		 */

		final long[] helidurations = { 50, 50, 50, 50, 50 };
		this.heliBadGuy = new HelicopterEnemy(Position.MOVE_SPEED, 0,
				500 + (int) (1000 * Math.random()), 360,
				new Animation(this.loader.get(this,
						ImageLoader.HELICOPTER_ROTATE_KEY), helidurations));

		this.addKeyListener(new KeyboardListener(this.mainCharacter));
		Painter.init(this);
	}

	/** 
	 * 
	 */
	@Override
	public void start() {
		super.start();

		Thread thread = new Thread(this);
		thread.start();
	}

	/** 
	 * 
	 */
	@Override
	public void stop() {
		super.stop();
	}

	/** 
	 * 
	 */
	@Override
	public void destroy() {
		super.destroy();
	}

	/**
	 * main loop of game handled here. 17ms of sleep gives 60FPS assuming
	 * perfect execution. Lazy programming.
	 */
	@Override
	public void run() {
		while (true) {
			this.mainCharacter.update(ELAPSED_TIME);
			this.heliBadGuy.setVisibleBoundary(this.mainCharacter
					.getVisibileBoundary());
			this.heliBadGuy.update(ELAPSED_TIME);
			Projectiles.update();
			this.firstBackground.update();
			this.secondBackground.update();
			repaint();
			try {
				Thread.sleep(ELAPSED_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * manages double buffering
	 */
	@Override
	public void update(Graphics g) {
		Painter.setG(g);
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(this.getBackground());
		second.fillRect(0, 0, this.getWidth(), this.getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	/**
	 * 
	 */
	@Override
	public void paint(Graphics g) {
		Painter.setG(g);
		this.firstBackground.paint();
		this.secondBackground.paint();
		Projectiles.paint();
		this.mainCharacter.paint();
		this.heliBadGuy.paint();

	}

	/**
	 * handles movement and space bar key presses
	 */

	public Background getBackground(int key) {
		switch (key) {
		case MainLoop.FIRST_BACKGROUND:
			return this.firstBackground;
		case MainLoop.SECOND_BACKGROUND:
			return this.secondBackground;
		default:
			return null;
		}
	}

}
