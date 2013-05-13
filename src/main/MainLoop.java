package main;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import character.npc.enemy.HelicopterEnemy;
import character.player.PlayerCharacter;

public class MainLoop extends Applet implements Runnable {
	// auto generated sUID to satisfy the warning gods
	private static final long serialVersionUID = 1560999524005463670L;

	// important constants that are only needed here
	public static final int DIM_X = 800; // frame size info
	public static final int DIM_Y = 480; // frame size info
	private static final String GAME_NAME = "Game Name Here"; // title
	private static final int MAGIC_NUMBER_Y = 61; // related to character
	private static final int MAGIC_NUMBER_X = 63; // related to character

	// which background is it?
	public static final int FIRST_BACKGROUND = 0;
	public static final int SECOND_BACKGROUND = 1;

	// component objects that need to be updated and maintained
	private PlayerCharacter mainCharacter;
	private HelicopterEnemy heliBadGuy;
	private Image image;
	private Image playerCharacterSprite;
	private Image background;
	private Graphics second;
	private URL base;
	private static Background firstBackground, secondBackground;

	/** 
	 * 
	 */
	@Override
	public void init() {
		// initial frame generation
		this.setSize(DIM_X, DIM_Y);
		this.setBackground(Color.BLACK);
		this.setFocusable(true);

		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle(GAME_NAME);

		// resource management
		base = getDocumentBase();
		this.background = getImage(base, "data/background.png");
	}

	/** 
	 * 
	 */
	@Override
	public void start() {
		super.start();
		MainLoop.firstBackground = new Background(0, 0);
		MainLoop.secondBackground = new Background(
				Background.BACKGROUND_LENGTH_X, 0);
		this.mainCharacter = new PlayerCharacter(getImage(base,
				"data/character.png"), getImage(base, "data/jumping.png"),
				getImage(base, "data/ducking.png"));
		this.heliBadGuy = new HelicopterEnemy(1, 1,
				MainLoop.firstBackground.getBackgroundSpeedX(), 500, 360,
				getImage(base, "data/heliboy.png"));
		this.addKeyListener(new KeyboardListener(this.mainCharacter));
		this.playerCharacterSprite = this.mainCharacter.getSprite();
		Thread thread = new Thread(this);
		thread.start();
	}

	/** 
	 * 
	 */
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	/** 
	 * 
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	/**
	 * main loop of game handled here. 17ms of sleep gives 60FPS assuming
	 * perfect execution. Lazy programming.
	 */
	@Override
	public void run() {
		while (true) {
			this.mainCharacter.update();
			this.playerCharacterSprite = this.mainCharacter.getSprite();
			this.heliBadGuy.update();

			MainLoop.firstBackground.update();
			MainLoop.secondBackground.update();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 */
	public void update(Graphics g) {
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
	public void paint(Graphics g) {
		g.drawImage(this.background, MainLoop.firstBackground.getBackgroundX(),
				MainLoop.firstBackground.getBackgroundY(), this);
		g.drawImage(this.background,
				MainLoop.secondBackground.getBackgroundX(),
				MainLoop.secondBackground.getBackgroundY(), this);
		g.drawImage(playerCharacterSprite, this.mainCharacter.getCenterX()
				- MAGIC_NUMBER_X, this.mainCharacter.getCenterY()
				- MAGIC_NUMBER_Y, this);
		g.drawImage(this.heliBadGuy.getImage(), this.heliBadGuy.getX() - 48,
				this.heliBadGuy.getY() - 48, this);

	}

	/**
	 * handles movement and space bar key presses
	 */

	public static Background getBackground(int key) {
		switch (key) {
		case MainLoop.FIRST_BACKGROUND:
			return MainLoop.firstBackground;
		case MainLoop.SECOND_BACKGROUND:
			return MainLoop.secondBackground;
		default:
			return null;
		}
	}

}
