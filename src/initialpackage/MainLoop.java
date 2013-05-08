package initialpackage;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

public class MainLoop extends Applet implements Runnable {
	// auto generated sUID to satisfy the warning gods
	private static final long serialVersionUID = 1560999524005463670L;

	private static final int DIM_X = 800;
	private static final int DIM_Y = 480;
	private static final String GAME_NAME = "Game Name Here";
	private static final int MAGIC_NUMBER_Y = 61;
	private static final int MAGIC_NUMBER_X = 63;
	private static final int MAGIC_NUMBER_BACKGROUND = 2160;

	public static final int FIRST_BACKGROUND = 0;
	public static final int SECOND_BACKGROUND = 1;

	private Character mainCharacter;
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
		MainLoop.secondBackground = new Background(MAGIC_NUMBER_BACKGROUND, 0);
		this.mainCharacter = new Character(
				getImage(base, "data/character.png"), getImage(base,
						"data/jumping.png"), getImage(base, "data/ducking.png"));
		this.addKeyListener(new KeyboardListener(this.mainCharacter));
		this.playerCharacterSprite = this.mainCharacter
				.getSprite(Character.DEFAULT_SPRITE);
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
			if (this.mainCharacter.isJumped()) {
				this.playerCharacterSprite = this.mainCharacter
						.getSprite(Character.JUMP_SPRITE);
			} else if (this.mainCharacter.isDucked()) {
				this.playerCharacterSprite = this.mainCharacter
						.getSprite(Character.DUCK_SPRITE);
			} else if (!this.mainCharacter.isJumped()
					&& !this.mainCharacter.isDucked()) {
				this.playerCharacterSprite = this.mainCharacter
						.getSprite(Character.DEFAULT_SPRITE);

			}

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
