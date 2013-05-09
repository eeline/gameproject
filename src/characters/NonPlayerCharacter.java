package characters;

import main.Background;
import main.MainLoop;

public class NonPlayerCharacter extends Character {
	private final Background background = MainLoop
			.getBackground(MainLoop.FIRST_BACKGROUND);

	public NonPlayerCharacter(int health, int power, int speed) {
		super(health, power, speed);
	}

}
