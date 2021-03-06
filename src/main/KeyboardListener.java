package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import character.Position;
import character.player.PlayerCharacter;

public class KeyboardListener implements KeyListener {
	private PlayerCharacter character;

	KeyboardListener(PlayerCharacter character) {
		this.character = character;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			character.move(Position.MOVE_DUCK);
			break;
		case KeyEvent.VK_LEFT:
			character.move(Position.MOVE_LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			character.move(Position.MOVE_RIGHT);
			break;
		case KeyEvent.VK_UP:
			character.move(Position.MOVE_JUMP);
			break;
		case KeyEvent.VK_SPACE:
			character.move(Position.MOVE_ATTACK);
			break;
		case KeyEvent.VK_CONTROL:
			character.move(Position.MOVE_FLY);
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
			character.stop(Position.MOVE_DUCK);
			break;
		case KeyEvent.VK_LEFT:
			character.stop(Position.MOVE_LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			character.stop(Position.MOVE_RIGHT);
			break;
		}
	}

	@Deprecated
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO not sure what to do with this yet, instinct is to leave it sit. 

	}

}
