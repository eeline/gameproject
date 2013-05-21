package main;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

import animation.Animation;
import background.Background;
import background.Tiles;
import character.Position;
import character.enemy.HelicopterEnemy;
import character.player.PlayerCharacter;
import character.weapon.Projectiles;

public class MainLoop extends Applet implements Runnable {
	// auto generated sUID to satisfy the warning gods
	private static final long serialVersionUID = 1560999524005463670L;

	private static final String GAME_NAME = "Project Bot"; // title

	// constants
	public static final int DIM_X = 800; // frame size info
	public static final int DIM_Y = 480; // frame size info
	public static final int ELAPSED_TIME = 17; // 17ms gives 60fps, this is a
												// tick

	// component objects that need to be updated and maintained
	private PlayerCharacter mainCharacter;
	private HelicopterEnemy heliBadGuy;
	private Image image;
	private Graphics second;
	private Background firstBackground, secondBackground;
	private Tiles tiles;

	// imageloader
	private ImageLoader loader;

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
		 * TODO set animation times and image locations in XML files, load
		 * through ImageLoader framework
		 */
		long[] backgroundDuration = { -1 };
		this.firstBackground = new Background(0, 0, new Animation(
				this.loader.get(this, ImageLoader.TILE_BACKGROUND),
				backgroundDuration));

		this.secondBackground = new Background(Background.BACKGROUND_LENGTH_X,
				0, new Animation(this.loader.get(this,
						ImageLoader.TILE_BACKGROUND), backgroundDuration));

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

		long[] dirtDuration = { -1 };
		long[] oceanDuration = { -1 };
		this.tiles = Tiles.get(
				new Animation(this.loader.get(this, ImageLoader.TILE_DIRT),
						dirtDuration),
				new Animation(this.loader.get(this, ImageLoader.TILE_OCEAN),
						oceanDuration));
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
			if (this.mainCharacter.isBackgroundPaused()) {
				this.firstBackground.stop();
				this.secondBackground.stop();
			} else {
				this.firstBackground.go();
				this.secondBackground.go();
			}
			this.heliBadGuy.setVisibleBoundary(this.mainCharacter
					.getVisibileBoundary());
			this.heliBadGuy.update(ELAPSED_TIME);
			Projectiles.update();
			this.firstBackground.update();
			this.secondBackground.update();
			tiles.update();
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
	 * handles paint calls
	 */
	@Override
	public void paint(Graphics g) {
		Painter.setG(g);
		this.firstBackground.paint();
		this.secondBackground.paint();
		Projectiles.paint();
		this.heliBadGuy.paint();
		tiles.paint();
		this.mainCharacter.paint();

	}
}
