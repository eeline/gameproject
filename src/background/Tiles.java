package background;

import java.util.ArrayList;
import java.util.List;

import animation.Animation;

public class Tiles {
	private static Tiles instance;
	List<Tile> tiles = new ArrayList<Tile>();
	private static final int xCount = 200;
	private static final int yCount = 12;

	public static Tiles get(Animation dirt, Animation ocean) {
		if (instance == null)
			instance = new Tiles(dirt, ocean);
		return instance;
	}

	public void update() {
		for (Tile t : tiles)
			t.update();
	}

	public void paint() {
		for (Tile t : tiles)
			t.paint();
	}

	private Tiles(Animation dirt, Animation ocean) {
		MapLoader loader = new MapLoader("");

		for (int i = 0; i < xCount; i++) {
			for (int j = 0; j < yCount; j++) {
				switch (j) {
				case 10:
					tiles.add(new Tile(i, j, Tile.OCEAN_SPEED, ocean,
							Tile.OCEAN_TILE));
					break;
				case 11:
					tiles.add(new Tile(i, j, Tile.DIRT_SPEED, dirt,
							Tile.DIRT_TILE));
					break;
				}
			}
		}
	}

	private void parseData(String string) {
		while(!string.isEmpty())
			new Tile()
		
	}

}
