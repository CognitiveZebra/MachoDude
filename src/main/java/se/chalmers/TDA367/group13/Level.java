package se.chalmers.TDA367.group13;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

public class Level {

	private TiledMap map;
	protected static final float LEVEL_FLOOR = 640;
	public static final float LEVEL_WIDTH = 1440;
	private List<Rectangle> collisions;
	private Image smallBackground;
	private Image background;
	private float cameraMovement;
	private float oldCamX;
	private Camera camera;

	public Level(Camera camera, TiledMap map, Image background) throws SlickException {
		this.camera = camera;
		oldCamX = camera.getX();
		this.map = map;
		this.background = background;
		smallBackground = background.getSubImage(0, 0, 1440, 896);

		collisions = new ArrayList<Rectangle>();

		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				int tileID = map.getTileId(x, y, 0);
				String block = map.getTileProperty(tileID, "blocked","notblocking");
				if (block != "notblocking") {
					collisions.add(new Rectangle(x * map.getTileWidth(), y* map.getTileWidth(), map.getTileHeight(), map.getTileWidth()));
				}

			}
		}

	}

	public void render(Graphics g) {
		g.drawImage(smallBackground, 0, 0);
		map.render((int) - oldCamX, 0);

		for (Rectangle r : collisions) {
			g.draw(r);
		}

	}

	public void update() {
		cameraMovement = camera.getX() - oldCamX;
		oldCamX = camera.getX();
		smallBackground = this.background.getSubImage((int) camera.getX(), 0, 1440, 736);
		for (Rectangle r : collisions) {
			r.setX(r.getX() - cameraMovement);
		}

	}
	
	public List<Rectangle> getCollisionBoxes() {
		return collisions;
	}

}
