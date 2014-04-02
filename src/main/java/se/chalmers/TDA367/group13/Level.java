package se.chalmers.TDA367.group13;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.TDA367.group13.entities.Block;

public class Level {

	private TiledMap map;
	protected static final float LEVEL_FLOOR = 640;
	public static final float LEVEL_WIDTH = 1440;
	private LinkedList<Block> blocks;
	private Image smallBackground;
	private Image background;
	private float cameraMovement;
	private float oldCamX;
	private Camera camera;

	public Level(Camera camera, TiledMap map, Image background) throws SlickException {
		this.camera = camera;
		this.map = map;
		this.background = background;
		smallBackground = background.getSubImage(0, 0, 1440, 896);

		blocks = new LinkedList<Block>();

		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				int tileID = map.getTileId(x, y, 0);
				String block = map.getTileProperty(tileID, "blocked","notblocking");
				if (block != "notblocking") {
					blocks.add(new Block(x * map.getTileWidth(), y* map.getTileWidth(), map.getTileImage(x, y, 0)));
				}

			}
		}

	}

	public void render(Graphics g) {
		g.drawImage(smallBackground, 0, 0);

		for (Block b : blocks) {
			b.render(g);
		}

	}

	public void updateBackground() {
		smallBackground = this.background.getSubImage((int) camera.getX(), 0, 1440, 736);
	}
	
	public List<Block> getBlocks() {
		return blocks;
	}
	
	public void moveBlocks(float f) {
		for (Block r : blocks) {
			r.setX(r.getX() + f);
		}
	}

}
