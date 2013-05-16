package main;

import java.applet.Applet;
import java.awt.Image;
import java.net.URL;

class ImageLoader {

	private static final String PLAYER_BASE = "data/playercharacter/";
	private static final String HELICOPTER_BASE = "data/enemy/helicopter/";
	private static final String BACKGROUND_BASE = "data/background/";
	private static final String TILE_BASE = "data/tile/";

	static final int PLAYER_BLINK_ANIMATION_KEY = 0;
	static final int PLAYER_JUMP_KEY = 1;
	static final int PLAYER_DUCK_KEY = 2;
	static final int HELICOPTER_ROTATE_KEY = 3;
	static final int BACKGROUND = 4;
	static final int TILE_BACKGROUND = 5;
	static final int TILE_DIRT = 6;
	static final int TILE_OCEAN = 7;

	Image[] get(final Applet applet, final int key) {
		final URL base = applet.getDocumentBase();
		switch (key) {
		case PLAYER_BLINK_ANIMATION_KEY:
			Image[] blink = {
					applet.getImage(base, ImageLoader.PLAYER_BASE
							+ "character.png"),
					applet.getImage(base, ImageLoader.PLAYER_BASE
							+ "character2.png"),
					applet.getImage(base, ImageLoader.PLAYER_BASE
							+ "character3.png"),
					applet.getImage(base, ImageLoader.PLAYER_BASE
							+ "character2.png") };
			return blink;
		case PLAYER_JUMP_KEY:
			Image[] jump = { applet.getImage(base, ImageLoader.PLAYER_BASE
					+ "jumping.png") };
			return jump;
		case PLAYER_DUCK_KEY:
			Image[] duck = { applet.getImage(base, ImageLoader.PLAYER_BASE
					+ "ducking.png") };
			return duck;
		case HELICOPTER_ROTATE_KEY:
			Image[] rotate = {
					applet.getImage(base, ImageLoader.HELICOPTER_BASE
							+ "heliboy.png"),
					applet.getImage(base, ImageLoader.HELICOPTER_BASE
							+ "heliboy2.png"),
					applet.getImage(base, ImageLoader.HELICOPTER_BASE
							+ "heliboy3.png"),
					applet.getImage(base, ImageLoader.HELICOPTER_BASE
							+ "heliboy4.png"),
					applet.getImage(base, ImageLoader.HELICOPTER_BASE
							+ "heliboy5.png"), };
			return rotate;
		case BACKGROUND:
			Image[] background = { applet.getImage(base,
					ImageLoader.BACKGROUND_BASE + "background.png") };
			return background;
		case TILE_BACKGROUND:
			Image[] tileBackground = {applet.getImage(base, ImageLoader.TILE_BASE +"background.png")};
			return tileBackground;
		case TILE_OCEAN:
			Image[] tileOcean = {applet.getImage(base, ImageLoader.TILE_BASE + "tileocean.png")};
			return tileOcean;
		case TILE_DIRT: 
			Image[] tileDirt = {applet.getImage(base, TILE_BASE + "tiledirt.png")};
			return tileDirt;
		default:
			return null;

		}
	}
}
