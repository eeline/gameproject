package initialpackage;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class StartingClass extends Applet implements Runnable, KeyListener {
	// auto generated sUID to satisfy the warning gods
	private static final long serialVersionUID = 1560999524005463670L;

	private static final int DIM_X = 800;
	private static final int DIM_Y = 480;
	private static final String GAME_NAME = "Game Name Here";
	private static final int MAGIC_NUMBER_Y = 61;
	private static final int MAGIC_NUMBER_X = 63;
	private static final int MAGIC_NUMBER_BACKGROUND = 2160;
	
	private Character mainCharacter;
	private Image image;
	private Image mainCharacterImage;
	private Image background;
	private Graphics second;
	private URL base;
	private static Background firstBackground, secondBackground;

	

	/** 
	 * 
	 */
	@Override
	public void init() {
		//initial frame generation
		this.setSize(DIM_X, DIM_Y);	
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(this);
		Frame frame = (Frame)this.getParent().getParent();
		frame.setTitle(GAME_NAME);
		
		//resource management
		base = getDocumentBase();
		this.mainCharacterImage = getImage(base, "data/character.png");
		this.background = getImage(base, "data/background.png");
	}

	/** 
	 * 
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
		this.firstBackground= new Background(0,0);
		this.secondBackground = new Background(MAGIC_NUMBER_BACKGROUND,0);
		this.mainCharacter = new Character();
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
			this.firstBackground.update();
			this.secondBackground.update();
			this.mainCharacter.update();
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
	public void update(Graphics g){
		if(image == null){
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}
		
		second.setColor(this.getBackground());
		second.fillRect(0, 0, this.getWidth(), this.getHeight());
		second.setColor(getForeground());
		paint(second);
		
		g.drawImage(image,0,0,this);
	}
	
	/**
	 * 
	 */
	public void paint(Graphics g){
		g.drawImage(this.background, this.firstBackground.getBackgroundX(), this.firstBackground.getBackgroundY(), this);
		g.drawImage(this.background, this.secondBackground.getBackgroundX(), this.secondBackground.getBackgroundY(), this);

		g.drawImage(mainCharacterImage, this.mainCharacter.getCenterX() - MAGIC_NUMBER_X, this.mainCharacter.getCenterY() - MAGIC_NUMBER_Y, this);
		
	}
	
	
	//KEY EVENT HANDLER:
	/** 
	 * 
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * handles movement and space bar key presses
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int moveType = Character.STOP_MOVEMENT;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_DOWN:
			moveType = Character.STOP_MOVEMENT;

			break;
		case KeyEvent.VK_LEFT:
			moveType = Character.MOVE_LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			moveType = Character.MOVE_RIGHT;
			break;
		case KeyEvent.VK_SPACE:
			moveType = Character.JUMP;
			break;
		}
		this.mainCharacter.movement(moveType);
	}

	/** 
	 * 
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_SPACE:
			this.mainCharacter.movement(Character.STOP_MOVEMENT);
			break;
		}
	}

}
