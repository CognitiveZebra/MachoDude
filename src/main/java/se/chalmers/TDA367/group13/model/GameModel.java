package se.chalmers.TDA367.group13.model;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;

import se.chalmers.TDA367.group13.entities.Block;
import se.chalmers.TDA367.group13.entities.Projectile;
import se.chalmers.TDA367.group13.entities.enemies.Enemy;
import se.chalmers.TDA367.group13.entities.player.Player;
import se.chalmers.TDA367.group13.exception.GameOverException;
import se.chalmers.TDA367.group13.factory.ParticleFactory;
import se.chalmers.TDA367.group13.util.Controls;
import se.chalmers.TDA367.group13.util.Stats;
import se.chalmers.TDA367.group13.view.Level;
import se.chalmers.TDA367.group13.view.Level_1;

public class GameModel {

	private GameContainer container;
	private Level level;
	private Player player;
	private float collisionY;
	private long gameStarted, gameEnded;


	public GameModel(GameContainer gc) {
		gameStarted = System.currentTimeMillis();
		Stats.getInstance().setScore(0);
		this.container = gc;
		try {
			level = new Level_1();
			player = new Player(300, 300, "res/Sprites/MachoDude/sheet.png",
					"res/Sprites/MachoDude/sheet.xml");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		


	}

	public void update(Input input, int delta) throws GameOverException {
		Rectangle nextXPos = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());

		if (input.isKeyDown(Controls.getInstance().getLeftKey())) {
			nextXPos.setX(player.getNextLeftX());
			if (isLegal(level.getBlocks(), nextXPos)) {
				player.moveLeft();
			} 
		} else if (input.isKeyDown(Controls.getInstance().getRightKey())) {
			nextXPos.setX(player.getNextRightX());
			if (isLegal(level.getBlocks(), nextXPos)) {

				if (nextXPos.getCenterX() > (container.getWidth() / 2) && !(-level.getCamera().getX() > (level.getWidth() - container.getWidth()))) {
					level.moveBlocks(player.getX() - nextXPos.getX());
					level.moveEnemies(player.getX() - nextXPos.getX());
					level.moveBoss(player.getX() - nextXPos.getX());
					level.moveProjectiles(player.getX() - nextXPos.getX());
					level.getCamera().move(player.getX() - nextXPos.getX());
				} else {
					player.moveRight();
				}
			}
		}


		if (input.isKeyDown(Controls.getInstance().getJumpKey()) && player.getState() != player.getPlayerJumping()) {
				player.setPlayerJumping();
		}

		Rectangle nextYPos = new Rectangle(player.getX(), player.getY(),
				player.getWidth(), player.getHeight());
		nextYPos.setY(player.getNextY());
		if (isLegal(level.getBlocks(), nextYPos)) {
			player.moveY();
		} else if(collisionY > player.getY()){
 				player.setPlayerStill();
		}
		

		if(player.getState() != player.getPlayerJumping()){
			if(input.isKeyDown(Controls.getInstance().getRightKey()) || input.isKeyDown(Controls.getInstance().getLeftKey())) {
				player.setPlayerWalking();
			}
		}
		
		player.moveWeapon();
		
		level.updateEnemies(player);
		level.updateBoss(player);
		
		player.getWeapon().pointAt(input.getMouseX(),input.getMouseY(), player.getDirection());
		
		if (input.isMouseButtonDown(Controls.getInstance().getShootKey()) || input.isKeyDown(Controls.getInstance().getShootKey())){
			player.fireWeapon();
		}

		
		
		LinkedList<Projectile> removed = new LinkedList<Projectile>();
		
		for (Projectile projectile : player.getWeapon().getProjectiles()) {
			Enemy victim = getVictim(level.getEnemies(), projectile);
			if (victim != null) {
				victim.loseHealth();
				removed.add(projectile);
			} else if(isLegal(level.getBlocks(), projectile)) {
				projectile.update(delta);
			} else {
				removed.add(projectile);
			}
		}
		
		player.getWeapon().getProjectiles().removeAll(removed);
		
		if(isEnemyCollision(level.getEnemies(), nextXPos)){
			player.loseHealth();
		}
		
		if(player.isDead()){
			gameEnded = System.currentTimeMillis();
			Stats.getInstance().addTimePlayed(gameEnded - gameStarted);
			Stats.getInstance().incrementDeaths();
			throw new GameOverException();
		}
		
		level.updateWeather(input, delta);
		

	}
		
	public boolean isLegal(LinkedList<Block> blocks, Rectangle hitbox) {
		for (Block b : blocks) {
			if (hitbox.intersects(b)) {
				collisionY = b.getY();
				return false;
			}
		}
		return (hitbox.getX() > 0 && hitbox.getMaxX() < container.getWidth());
	}

	public boolean isEnemyCollision(LinkedList<Enemy> enemies, Rectangle hitbox){
		for(Enemy e : enemies){
			if(hitbox.intersects(e)){
				return true;
			}
		}
		return false;
	}
	
	public Enemy getVictim(LinkedList<Enemy> enemies, Rectangle hitbox) {
		for(Enemy e : enemies){
			if(hitbox.intersects(e)){
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
	
	public void startMusic(){
		level.loopMusic();
	}
	
}
