package se.chalmers.TDA367.group13;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.TDA367.group13.entities.Block;
import se.chalmers.TDA367.group13.entities.Enemy;
import se.chalmers.TDA367.group13.entities.Enemy.Direction;
import se.chalmers.TDA367.group13.entities.Enemy_1;
import se.chalmers.TDA367.group13.entities.Player;

public class Level {
	
	private Music music;
	private TiledMap map;
	private LinkedList<Block> blocks;
	private LinkedList<Enemy> enemies;
	private Image smallBackground;
	private Image background;
	private Camera camera;

	public Level(Camera camera, TiledMap map, Image background, Music music) throws SlickException {
		this.camera = camera;
		this.map = map;
		this.background = background;
		this.music = music;
		smallBackground = background.getSubImage(0, 0, 1216, 768);

		blocks = new LinkedList<Block>();
		enemies = new LinkedList<Enemy>();

		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				int tileID = map.getTileId(x, y, 0);
				String block = map.getTileProperty(tileID, "blocked","false");
				String enemy = map.getTileProperty(tileID, "enemy","false");
				if (block.equals("true")) {
					blocks.add(new Block(x * map.getTileWidth(), y* map.getTileWidth(), map.getTileImage(x, y, 0)));
				}
				switch (enemy) {
				case "1":
					enemies.add(new Enemy_1(x * map.getTileWidth(), y* map.getTileWidth()));
					System.out.println(enemies.size());
					break;
				default:
					break;
				}

			}
		}

	}

	public void render(Graphics g) {
		g.drawImage(smallBackground, 0, 0);

		for (Block b : blocks) {
			b.render(g);
		}
		for (Enemy e : enemies) {
			e.render(g);
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
	
	public LinkedList<Enemy> getEnemies() {
		return enemies;
	}
	
	public void moveBlocks(float f) {
		for (Block r : blocks) {
			r.setX(r.getX() + f);
		}
	}
	
	public void moveEnemies(float f) {
		for (Enemy e : enemies) {
			e.setX(e.getX() + f);
		}
	}

	
	public void setMusic(Music music){
		this.music = music;
	}
	
	public void loopMusic(){
		music.loop();
	}

	public float getWidth() {
		return (map.getWidth() * map.getTileWidth());
	}

	public void updateEnemies(Player player) {
		for (Enemy e : enemies) {
			if (e.getCenterX() < player.getCenterX()) {
				e.setDirection(Direction.RIGHT);
			} else {
				e.setDirection(Direction.LEFT);
			}
		}
		
	}


}
