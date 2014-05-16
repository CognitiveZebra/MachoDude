package se.chalmers.TDA367.group13.level;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.TDA367.group13.Game;
import se.chalmers.TDA367.group13.entities.block.Block;
import se.chalmers.TDA367.group13.entities.enemies.Enemy;
import se.chalmers.TDA367.group13.entities.enemies.boss1.Boss_1;
import se.chalmers.TDA367.group13.entities.enemies.enemy1.Enemy_1;
import se.chalmers.TDA367.group13.entities.enemies.enemy2.Enemy_2;
import se.chalmers.TDA367.group13.entities.player.Player;
import se.chalmers.TDA367.group13.entities.projectile.Projectile;
import se.chalmers.TDA367.group13.exception.WinException;
import se.chalmers.TDA367.group13.particles.ParticleFactory;
import se.chalmers.TDA367.group13.util.Camera;
import se.chalmers.TDA367.group13.util.Controls;
import se.chalmers.TDA367.group13.util.Direction;
import se.chalmers.TDA367.group13.util.Stats;
import se.chalmers.TDA367.group13.util.Util;
import se.chalmers.TDA367.group13.view.TextItem;

public class Level {

	private Music music;
	private TiledMap map;
	private TextItem score;
	private LinkedList<Block> blocks;
	private LinkedList<Enemy> enemies;
	private Image smallBackground;
	private Image background;
	private Camera camera;
	private Boss_1 boss;
	private LinkedList<Projectile> projectiles;
	private ParticleSystem weather; 
	private ConfigurableEmitter rain;
	private int delta;
	protected int level_number;

	public Level(Camera camera, TiledMap map, Image background, Music music)
			throws SlickException {
		this.camera = camera;
		this.map = map;
		this.background = background;
		this.music = music;
		smallBackground = background.getSubImage(0, 0, 1216, 768);
		score = new TextItem("Score: " + Stats.getInstance().getScore(), Game.WIDTH - 200, 10);

		projectiles = new LinkedList<Projectile>();
		blocks = new LinkedList<Block>();
		enemies = new LinkedList<Enemy>();


		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				String block = map.getTileProperty(map.getTileId(x, y, 0), "blocked", "false");
				String enemy = map.getTileProperty(map.getTileId(x, y, 1), "enemy", "false");
				if (block.equals("true")) {
					blocks.add(new Block(x * map.getTileWidth(), y
							* map.getTileWidth(), map.getTileImage(x, y, 0)));
				}
				switch (enemy) {
				case "1":
					enemies.add(new Enemy_1(x * map.getTileWidth(), y
							* map.getTileWidth()));
					break;
				case "2":
					enemies.add(new Enemy_2(x * map.getTileWidth(), y
							* map.getTileWidth()));
					break;
				case "boss":
					boss = new Boss_1((x-2) * map.getTileWidth(), 
							y * map.getTileWidth());
					break;
				default:
					break;
				}

			}
		}

		weather = new ParticleSystem(new Image("res/Particles/particle_rain.png"),1500);
		rain = ParticleFactory.createEmitter("rain");
		weather.addEmitter(rain);

	}

	public void render(Graphics g) {
		g.drawImage(smallBackground, 0, 0);
		weather.render();

		for (Block b : blocks) {
			b.render(g);
		}
		for (Enemy e : enemies) {
			e.render(g);
		}

		boss.render(g);
		for (Projectile projectile : projectiles) {
			projectile.render(g);
		}

		g.setColor(Color.white);
		g.setFont(Util.getFont32());
		score.render(g);
	}

	public Camera getCamera() {
		return camera;
	}

	public void updateBackground() {
		smallBackground = this.background.getSubImage((int) camera.getX(), 0,
				1216, 768);
	}

	public LinkedList<Block> getBlocks() {
		return blocks;
	}

	public LinkedList<Enemy> getEnemies() {
		return enemies;
	}

	public Boss_1 getBoss(){
		return boss;
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

	public void moveProjectiles (float f){
		for (Projectile p : projectiles)
			p.setX(p.getX() + f);
	}

	public void moveBoss(float f){
		boss.setX(boss.getX()+f);
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public void loopMusic() {
		music.loop();
	}

	public float getWidth() {
		return (map.getWidth() * map.getTileWidth());
	}

	public void updateBoss(Player player) throws WinException{
		if (boss.getHealth() <= 0){
			Stats.getInstance().updateHighestLevel(level_number);
			throw new WinException();
		} else if (boss.getX() < Game.WIDTH){
			
			boss.showHealthBar();
			if(boss.getState() == boss.getShootingState()){
				projectiles.add(boss.fireLaser());
			} 
			
			boss.update(player);
			
			Rectangle hitbox = new Rectangle(boss.getX(), boss.getNextY(), boss.getWidth(), boss.getHeight());
			
			if(isLegal(hitbox)){
				boss.moveY();
			}
			


		} 
	}

	public void updateEnemies(Player player, int delta) {
		this.delta = delta;
		LinkedList<Enemy> dead = new LinkedList<Enemy>();
		for (Enemy e : enemies) {

			if (e.isDestroyed()) {
				dead.add(e);
				Stats.getInstance().addScore(e.getValue());
				Stats.getInstance().incrementEnemiesKilled();
			}

			Rectangle nextYPos;
			if (e.getDirection() == Direction.LEFT) {
				nextYPos = new Rectangle(e.getX()-e.getWidth(), e.getY(), e.getWidth(), e.getHeight());
			} else {
				nextYPos = new Rectangle(e.getMaxX(), e.getY(), e.getWidth(), e.getHeight());
			}

			nextYPos.setY(e.getNextY());
			if (isLegal(nextYPos)) {
				e.setDirection(((e.getDirection() == Direction.LEFT) ?  Direction.RIGHT: Direction.LEFT));
			}

			if (Math.abs((e.getCenterX() - player.getCenterX())) < 20 || isLegal(nextYPos)) {
				e.setState(e.getStillState());
			} else {
				e.setState(e.getWalkingState());
			}

			if (e.getState() == e.getWalkingState()) {
				Rectangle nextXPos = new Rectangle(e.getX(), e.getY(), e.getWidth(), e.getHeight());
				if (e.getDirection() == Direction.LEFT) {
					nextXPos.setX(e.getNextLeftX());
					if (isLegal(nextXPos)) {
						e.moveLeft();
					} else {
						e.setDirection(Direction.RIGHT);
					}
				} else if (e.getDirection() == Direction.RIGHT) {
					nextXPos.setX(e.getNextRightX());
					if (isLegal(nextXPos)) {
						e.moveRight();
					} else {
						e.setDirection(Direction.LEFT);
					} 
				}
				nextXPos.setX(e.getNextLeftX());
				if (!isLegal(nextXPos)) {
					e.setState(e.getStillState());
				}
			}
			e.updateAggroRange();
			if (e.getAggroRange().intersects(player)) {
				if (e.getCenterX() < player.getCenterX()) {
					e.setDirection(Direction.RIGHT);
				} else {
					e.setDirection(Direction.LEFT);
				}
				e.getWeapon().pointAt(player.getCenterX(), player.getCenterY(), e.getDirection());
				if (e.getWeapon().isReady()) {	
					projectiles.add(e.getWeapon().fireWeapon(e.getDirection()));
				}
			} else {
				if (e.getDirection() == Direction.RIGHT) {
					e.getWeapon().setImage(e.getWeapon().getRightImage());
				} else if (e.getDirection() == Direction.LEFT) {
					e.getWeapon().setImage(e.getWeapon().getLeftImage());
				}
				e.getWeapon().getImage().setRotation(0);
			}

		}

		LinkedList<Projectile> removed = new LinkedList<Projectile>();
		if (!projectiles.isEmpty()){
			for (Projectile projectile : projectiles) {
				if (projectile.intersects(player)) {
					player.loseHealth(projectile.getDamage());
					removed.add(projectile);
				} else if (isLegal(projectile)) {
					projectile.update(delta);
				} else {
					removed.add(projectile);
				}
			}
		}
		getProjectiles().removeAll(removed);
		enemies.removeAll(dead);
	}

	public void updateWeather(Input input, int delta){
		updateWind(input, rain);
		weather.update(delta);
	}

	public void updateWind(Input input, ConfigurableEmitter emitter){
		if(input.isKeyDown(Controls.getInstance().getLeftKey())){
			emitter.windFactor.setValue(25);
		} else if(input.isKeyDown(Controls.getInstance().getRightKey())){
			emitter.windFactor.setValue(-25);
		} else {
			emitter.windFactor.setValue(0);
		}
	}

	public void updateScore() {
		score.updateText("Score: " + Stats.getInstance().getScore());
	}
	public ParticleSystem getWeather(){
		return weather;
	}

	public boolean isLegal(Rectangle hitbox) {
		for (Block b : blocks) {
			if (hitbox.intersects(b)) {
				return false;
			}
		}
		return true;
	}

	public LinkedList<Projectile> getProjectiles() {
		return projectiles;
	}
}
