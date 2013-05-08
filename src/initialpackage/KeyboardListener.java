package initialpackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
	private Character character;
	KeyboardListener(Character character){
		this.character = character;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			character.move(Character.MOVE_DUCK);
			break;
		case KeyEvent.VK_LEFT:
			character.move(Character.MOVE_LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			character.move(Character.MOVE_RIGHT);
			break;
		case KeyEvent.VK_UP:
			character.move(Character.MOVE_FLY);
			break;
		case KeyEvent.VK_SPACE:
			character.move(Character.MOVE_JUMP);
			break;
		}

	}

	/** 
	 * 
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
		case KeyEvent.VK_UP:
			break;
		case KeyEvent.VK_DOWN:
			character.stop(Character.MOVE_DUCK);
			break;
		case KeyEvent.VK_LEFT:
			character.stop(Character.MOVE_LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			character.stop(Character.MOVE_RIGHT);
			break;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
