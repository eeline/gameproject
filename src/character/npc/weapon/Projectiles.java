package character.npc.weapon;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Projectiles {
	private static List<Projectile> PROJECTILE_LIST = Collections
			.synchronizedList(new ArrayList<Projectile>());
	private static Queue<Projectile> TO_REMOVE = new LinkedList<Projectile>();

	/**
	 * 
	 * @param centerX
	 * @param centerY
	 * @param speedX
	 */
	public static void generateProjectile(int power, int speedX, int centerX,
			int centerY) {
		synchronized (PROJECTILE_LIST) {
			PROJECTILE_LIST.add(new Projectile(power, speedX, centerX, centerY,
					true));
		}
	}

	public static void update() {
		synchronized (PROJECTILE_LIST) {
			for (Projectile p : PROJECTILE_LIST) {
				if (p.isVisible()) {
					p.update();
				} else
					TO_REMOVE.add(p);
			}
		}

		for (int i = 0; i < TO_REMOVE.size(); i++)
			PROJECTILE_LIST.remove(TO_REMOVE.poll());
	}

	public static void paint(Graphics g, ImageObserver ob) {
		synchronized (PROJECTILE_LIST) {
			for (Projectile p : PROJECTILE_LIST) {
				p.paint(g, ob);
			}
		}
	}
}
