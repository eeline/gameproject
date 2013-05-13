package character.weapon;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Projectiles {
	private static ArrayList<Projectile> PROJECTILE_LIST = new ArrayList<Projectile>();

	public static void update() {
		for (Projectile p : PROJECTILE_LIST)
			p.update();
	}

	/**
	 * 
	 * @param centerX
	 * @param centerY
	 * @param speedX
	 */
	public static void generateProjectile(int power, int speedX, int centerX,
			int centerY) {
		PROJECTILE_LIST.add(new Projectile(power, speedX, centerX, centerY,
				true));
	}

	public static void paint(Graphics g, ImageObserver ob) {
		for (Projectile p : PROJECTILE_LIST)
			p.paint(g, ob);
	}
}
