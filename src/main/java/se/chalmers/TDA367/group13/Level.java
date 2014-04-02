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
		smallBackground = background.getSubImage(0, 0, 1216, 768);

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
	
	public Camera getCamera() {
		return camera;
	}

	public void updateBackground() {
		smallBackground = this.background.getSubImage((int) camera.getX(), 0, 1216, 768);
	}
	
	public LinkedList<Block> getBlocks() {
		return blocks;
	}
	
	public void moveBlocks(float f) {
		for (Block r : blocks) {
			r.setX(r.getX() + f);
		}
	}

}
