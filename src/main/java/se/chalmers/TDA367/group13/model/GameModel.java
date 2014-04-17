package se.chalmers.TDA367.group13.model;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import se.chalmers.TDA367.group13.entities.Block;
import se.chalmers.TDA367.group13.entities.Enemy;
import se.chalmers.TDA367.group13.entities.Player;
import se.chalmers.TDA367.group13.entities.Player.State;
import se.chalmers.TDA367.group13.entities.Projectile;
import se.chalmers.TDA367.group13.exception.GameOverException;
import se.chalmers.TDA367.group13.util.Controls;
import se.chalmers.TDA367.group13.util.Stats;
import se.chalmers.TDA367.group13.view.Level;
import se.chalmers.TDA367.group13.view.Level_1;

public class GameModel {

	private GameContainer container;
	private Level level;
	private Player player;
	private float collisionY;
	private int Score = 0;
	private long gameStarted, gameEnded;

	public GameModel(GameContainer gc) {
		gameStarted = System.currentTimeMillis();
		Stats.setScore(0);
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

		if (input.isKeyDown(Controls.getLeftKey())) {
			nextXPos.setX(player.nextLeftX());
			if (isLegal(level.getBlocks(), nextXPos)) {
				player.moveLeft();
			} 
		} else if (input.isKeyDown(Controls.getRightKey())) {
			nextXPos.setX(player.nextRightX());
			if (isLegal(level.getBlocks(), nextXPos)) {

				if (nextXPos.getCenterX() > (container.getWidth() / 2) && !(-level.getCamera().getX() > (level.getWidth() - container.getWidth()))) {
					level.moveBlocks(player.getX() - nextXPos.getX());
					level.moveEnemies(player.getX() - nextXPos.getX());
					level.getCamera().move(player.getX() - nextXPos.getX());
				} else {
					player.moveRight();
				}
			}
		}

		if (input.isKeyDown(Controls.getJumpKey())) {
			if (player.getState() != State.JUMPING) {
				player.setState(State.JUMPING);
				player.setJumpStart(System.currentTimeMillis());
				player.setJumpCharge(System.currentTimeMillis());
			} else if (System.currentTimeMillis() - player.getJumpCharge() < 500) {
				player.setJumpStart(System.currentTimeMillis());
			}
		} else {
			player.setJumpCharge(0);
		}

		Rectangle nextYPos = new Rectangle(player.getX(), player.getY(),
				player.getWidth(), player.getHeight());
		player.updateYVelocity();
		nextYPos.setY(player.nextY());
		if (isLegal(level.getBlocks(), nextYPos)) {
			player.moveY();
		} else if(collisionY > player.getY()){
 				player.setState(State.STILL);
		}
		
		if(player.getState() != State.JUMPING){
			if(input.isKeyDown(Controls.getRightKey()) || input.isKeyDown(Controls.getLeftKey())) {
				player.setState(State.WALKING);
			}
		}
		
		level.updateEnemies(player);
		
		player.getWeapon().pointAt(input.getMouseX(),input.getMouseY(), player.getDirection());
		if (input.isMouseButtonDown(Controls.getShootKey()) || input.isKeyDown(Controls.getShootKey())) 
			player.getWeapon().fireWeapon(player.getDirection());
			LinkedList<Projectile> removed = new LinkedList<Projectile>();
		
		for (Projectile projectile : player.getWeapon().getProjectiles()) {
			Enemy victim = getVictim(level.getEnemies(), projectile);
			if (victim != null) {
				victim.loseHealth();
				removed.add(projectile);
			} else if(isLegal(level.getBlocks(), projectile)) {
				projectile.update();
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
			Stats.addTimePlayed(gameEnded - gameStarted);
			Stats.incrementDeaths();
			throw new GameOverException();
		}
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
