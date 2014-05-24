package se.chalmers.TDA367.group13.model;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import se.chalmers.TDA367.group13.Game;
import se.chalmers.TDA367.group13.exception.GameOverException;
import se.chalmers.TDA367.group13.exception.WinException;
import se.chalmers.TDA367.group13.model.entities.block.Block;
import se.chalmers.TDA367.group13.model.entities.enemies.Enemy;
import se.chalmers.TDA367.group13.model.entities.player.Player;
import se.chalmers.TDA367.group13.model.entities.projectile.Projectile;
import se.chalmers.TDA367.group13.model.level.Level;
import se.chalmers.TDA367.group13.model.level.LevelFactory;
import se.chalmers.TDA367.group13.util.Controls;
import se.chalmers.TDA367.group13.util.Stats;

public class GameModel {

	private GameContainer container;
	private Level level;
	private float collisionY;
	private long gameStarted, gameEnded;
	private Player player;
	private LinkedList<Enemy> enemies;
	private LinkedList<Projectile> projectiles; 
	//private Boss boss;

	public GameModel(GameContainer gc, int levelNumber) {
		gameStarted = System.currentTimeMillis();
		Stats.getInstance().setScore(0);
		this.container = gc;
		try {
			this.level = LevelFactory.createLevel(levelNumber);
			player = level.getPlayer();
			enemies = level.getEnemies();
			projectiles = level.getProjectiles();
			//boss = level.getBoss();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
	
	public void update(Input input, int delta) throws GameOverException, WinException {
		updatePlayer(input,delta);
		updateEnemies(delta);
		updateBoss(delta);
		updateProjectiles(input, delta);
	}
	
	public void updatePlayer(Input input, int delta) throws GameOverException{
		checkGameOver();
		movePlayerX(input, delta);
		movePlayerY(input, delta);
		determinePlayerState(input);
		updatePlayerWeapon(input);
		playerShoot(input);
	}

	public void movePlayerX(Input input, int delta) {
		Rectangle nextXPos = new Rectangle(player.getX(), level
				.getPlayer().getY(), player.getWidth(), level
				.getPlayer().getHeight());
		if (input.isKeyDown(Controls.getInstance().getLeftKey())) {
			nextXPos.setX(player.getNextLeftX(delta));
			if (isBlockCollision(level.getBlocks(), nextXPos)) {
				player.moveLeft(delta);
			}
		} else if (input.isKeyDown(Controls.getInstance().getRightKey())) {
			nextXPos.setX(player.getNextRightX(delta));
			if (isBlockCollision(level.getBlocks(), nextXPos)) {

				if (nextXPos.getCenterX() > (container.getWidth() / 2)
						&& !(-level.getCamera().getX() > (level.getWidth() - container
								.getWidth()))) {
					level.moveBlocks(player.getX() - nextXPos.getX());
					level.moveEnemies(player.getX()
							- nextXPos.getX());
					level.moveBoss(player.getX() - nextXPos.getX());
					level.moveProjectiles(player.getX()
							- nextXPos.getX());
					level.getCamera().move(
							player.getX() - nextXPos.getX());
				} else {
					player.moveRight(delta);
				}
			}
		}
	}

	public void movePlayerY(Input input, int delta) {
		if (input.isKeyDown(Controls.getInstance().getJumpKey())
				&& player.getState() != player
						.getPlayerJumping()) {
			player.setPlayerJumping();
		}

		Rectangle nextYPos = new Rectangle(player.getX(), level
				.getPlayer().getNextY(delta), player.getWidth(),
				player.getHeight());
		if (isBlockCollision(level.getBlocks(), nextYPos)) {
			player.moveY(delta);
		} else if (collisionY > player.getY()) {
			player.setPlayerStill();
		}
	}
	
	public void determinePlayerState(Input input){
		if (player.getState() != player
				.getPlayerJumping()) {
			if (input.isKeyDown(Controls.getInstance().getRightKey())
					|| input.isKeyDown(Controls.getInstance().getLeftKey())) {
				player.setPlayerWalking();
			}
		}
	}
	
	public void updatePlayerWeapon(Input input){
		player.moveWeapon();
		player
				.getWeapon()
				.pointAt(input.getMouseX(), input.getMouseY(),
						player.getDirection());
	}
	
	public void playerShoot(Input input){
		if (input.isMouseButtonDown(Controls.getInstance().getShootKey())
				|| input.isKeyDown(Controls.getInstance().getShootKey())) {
			if (player.getWeapon().isReady()) {
				player.getProjectiles()
						.add(player.fireWeapon());
			}
		}
	}
	
	public void updateEnemies(int delta){
		level.updateEnemies(player, delta);
		Rectangle nextXPos = new Rectangle(player.getX(), level
				.getPlayer().getY(), player.getWidth(), level
				.getPlayer().getHeight());
		if (isEnemyCollision(enemies, nextXPos)) {
			player.loseHealth();
		}
	}
	
	public void updateBoss(int delta) throws WinException{
		level.updateBoss(player, delta);
	}
	
	public void updateProjectiles(Input input, int delta){
		LinkedList<Projectile> removed = new LinkedList<Projectile>();

		for (Projectile projectile : player.getProjectiles()) {
			Enemy victim = getVictim(enemies, projectile);
			if (victim != null) {
				victim.loseHealth();
				removed.add(projectile);
			} else if (projectile.intersects(level.getBoss())
					&& !level.getBoss().isDestroyed()) {
				level.getBoss().loseHealth();
				removed.add(projectile);
			} else if (isBlockCollision(level.getBlocks(), projectile)) {
				projectile.update(delta);
			} else {
				removed.add(projectile);
			}
		}

		player.getProjectiles().removeAll(removed);
	}

	
	public void updateWeather(Input input, int delta){
		level.updateWeather(input, delta);
	} 
	
	public void checkGameOver() throws GameOverException{
		if(player.isDead()){
			System.out.println();
			gameEnded = System.currentTimeMillis();
			Stats.getInstance().addTimePlayed(gameEnded - gameStarted);
			Stats.getInstance().incrementDeaths();
			throw new GameOverException();
		}
	}
	
	


	public boolean isBlockCollision(LinkedList<Block> blocks, Rectangle hitbox) {
		for (Block b : blocks) {
			if (hitbox.intersects(b)) {
				collisionY = b.getY();
				return false;
			}
		}
		return (hitbox.getX() > 0 && hitbox.getMaxX() < container.getWidth());
	}

	public boolean isEnemyCollision(LinkedList<Enemy> enemies, Rectangle hitbox) {
		for (Enemy e : enemies) {
			if (hitbox.intersects(e)) {
				return true;
			}
		}
		return false;
	}

	public Enemy getVictim(LinkedList<Enemy> enemies, Rectangle hitbox) {
		for (Enemy e : enemies) {
			if (hitbox.intersects(e)) {
				return e;
			}
		}
		return null;
	}

	public Level getLevel() {
		return level;
	}

	public Player getPlayer() {
		return player;
	}

	public void startMusic() {
		level.loopMusic();
	}

}
