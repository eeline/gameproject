package characters;

import main.Background;
import main.MainLoop;

public class NonPlayerCharacter extends Position {
	final Attributes attributes;

	private final Background background = MainLoop
			.getBackground(MainLoop.FIRST_BACKGROUND);

	public NonPlayerCharacter(int health, int power, int speed) {
		attributes = new Attributes(health, power, speed);
	}

}
