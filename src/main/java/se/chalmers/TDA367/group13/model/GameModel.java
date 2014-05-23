package se.chalmers.TDA367.group13.model;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import se.chalmers.TDA367.group13.Game;
import se.chalmers.TDA367.group13.entities.block.Block;
import se.chalmers.TDA367.group13.entities.enemies.Enemy;
import se.chalmers.TDA367.group13.entities.player.Player;
import se.chalmers.TDA367.group13.entities.projectile.Projectile;
import se.chalmers.TDA367.group13.exception.GameOverException;
import se.chalmers.TDA367.group13.exception.WinException;
import se.chalmers.TDA367.group13.level.Level;
import se.chalmers.TDA367.group13.level.LevelFactory;
import se.chalmers.TDA367.group13.util.Controls;
import se.chalmers.TDA367.group13.util.Stats;

public class GameModel {

	private GameContainer container;
	private Level level;
	private float collisionY;
	private long gameStarted, gameEnded;

	public GameModel(GameContainer gc, int level) {
		gameStarted = System.currentTimeMillis();
		Stats.getInstance().setScore(0);
		this.container = gc;
		try {
			this.level = LevelFactory.createLevel(level);
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
	
	public void update(Input input, int delta) throws GameOverException, WinException {
		movePlayerX(input, delta);
		movePlayerY(input, delta);
		determinePlayerState(input);
		updatePlayerWeapon(input);
		playerShoot(input);
		updateEnemies(delta);
		updateBoss(delta);
		updateScore();
		updateProjectiles(input, delta);
		checkGameOver();
	}
	

	public void movePlayerX(Input input, int delta) {
		Rectangle nextXPos = new Rectangle(level.getPlayer().getX(), level
				.getPlayer().getY(), level.getPlayer().getWidth(), level
				.getPlayer().getHeight());
		if (input.isKeyDown(Controls.getInstance().getLeftKey())) {
			nextXPos.setX(level.getPlayer().getNextLeftX(delta));
			if (isBlockCollision(level.getBlocks(), nextXPos)) {
				level.getPlayer().moveLeft(delta);
			}
		} else if (input.isKeyDown(Controls.getInstance().getRightKey())) {
			nextXPos.setX(level.getPlayer().getNextRightX(delta));
			if (isBlockCollision(level.getBlocks(), nextXPos)) {

				if (nextXPos.getCenterX() > (container.getWidth() / 2)
						&& !(-level.getCamera().getX() > (level.getWidth() - container
								.getWidth()))) {
					level.moveBlocks(level.getPlayer().getX() - nextXPos.getX());
					level.moveEnemies(level.getPlayer().getX()
							- nextXPos.getX());
					level.moveBoss(level.getPlayer().getX() - nextXPos.getX());
					level.moveProjectiles(level.getPlayer().getX()
							- nextXPos.getX());
					level.getCamera().move(
							level.getPlayer().getX() - nextXPos.getX());
				} else {
					level.getPlayer().moveRight(delta);
				}
			}
		}
	}

	public void movePlayerY(Input input, int delta) {
		if (input.isKeyDown(Controls.getInstance().getJumpKey())
				&& level.getPlayer().getState() != level.getPlayer()
						.getPlayerJumping()) {
			level.getPlayer().setPlayerJumping();
		}

		Rectangle nextYPos = new Rectangle(level.getPlayer().getX(), level
				.getPlayer().getNextY(delta), level.getPlayer().getWidth(),
				level.getPlayer().getHeight());
		if (isBlockCollision(level.getBlocks(), nextYPos)) {
			level.getPlayer().moveY(delta);
		} else if (collisionY > level.getPlayer().getY()) {
			level.getPlayer().setPlayerStill();
		}
	}
	
	public void determinePlayerState(Input input){
		if (level.getPlayer().getState() != level.getPlayer()
				.getPlayerJumping()) {
			if (input.isKeyDown(Controls.getInstance().getRightKey())
					|| input.isKeyDown(Controls.getInstance().getLeftKey())) {
				level.getPlayer().setPlayerWalking();
			}
		}
	}
	
	public void updatePlayerWeapon(Input input){
		level.getPlayer().moveWeapon();
		level.getPlayer()
				.getWeapon()
				.pointAt(input.getMouseX(), input.getMouseY(),
						level.getPlayer().getDirection());
	}
	
	public void playerShoot(Input input){
		if (input.isMouseButtonDown(Controls.getInstance().getShootKey())
				|| input.isKeyDown(Controls.getInstance().getShootKey())) {
			if (level.getPlayer().getWeapon().isReady()) {
				level.getPlayer().getProjectiles()
						.add(level.getPlayer().fireWeapon());
			}
		}
	}
	
	public void updateEnemies(int delta){
		level.updateEnemies(level.getPlayer(), delta);
		Rectangle nextXPos = new Rectangle(level.getPlayer().getX(), level
				.getPlayer().getY(), level.getPlayer().getWidth(), level
				.getPlayer().getHeight());
		if (isEnemyCollision(level.getEnemies(), nextXPos)) {
			level.getPlayer().loseHealth();
		}
	}
	
	public void updateBoss(int delta) throws WinException{
		level.updateBoss(level.getPlayer(), delta);
	}
	
	public void updateScore(){
		level.updateScore();
	}
	
	public void updateProjectiles(Input input, int delta){
		LinkedList<Projectile> removed = new LinkedList<Projectile>();

		for (Projectile projectile : level.getPlayer().getProjectiles()) {
			Enemy victim = getVictim(level.getEnemies(), projectile);
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

		level.getPlayer().getProjectiles().removeAll(removed);
	}

	
	public void updateWeather(Input input, int delta){
		level.updateWeather(input, delta);
	} 
	
	public void checkGameOver() throws GameOverException{
		if(level.getPlayer().isDead()){
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
		return level.getPlayer();
	}

	public void startMusic() {
		level.loopMusic();
	}

}
