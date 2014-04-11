package se.chalmers.TDA367.group13;

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

public class GameModel {

	private GameContainer container;
	private Level level;
	private Player player;
	private float collisionY;

	public GameModel(GameContainer gc) {
		this.container = gc;

		try {
			level = new Level_1();
			player = new Player(300, 300, "res/Sprites/MachoDude/sheet.png",
					"res/Sprites/MachoDude/sheet.xml");
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public void update(Input input, int delta) {
		
		Rectangle nextXPos = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());

		if (input.isKeyDown(Input.KEY_A)) {
			nextXPos.setX(player.nextLeftX());
			if (isLegal(level.getBlocks(), nextXPos)) {
				player.moveLeft();
			} 
		} else if (input.isKeyDown(Input.KEY_D)) {
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

		if (input.isKeyDown(Input.KEY_W)) {
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
			if(input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_A)) {
				player.setState(State.WALKING);
			}
		}
		level.updateEnemies(player);
		
		player.getWeapon().pointAt(input.getMouseX(),input.getMouseY(), player.getDirection());
		if (input.isMouseButtonDown(input.MOUSE_LEFT_BUTTON)) 
			player.getWeapon().fireWeapon(player.getDirection());
		LinkedList<Projectile> removed = new LinkedList<Projectile>();
		for (Projectile projectile : player.getWeapon().getProjectiles()) {
			if(isLegal(level.getBlocks(), projectile)) {
				projectile.update();
			} else {
				removed.add(projectile);
			}
		}
		player.getWeapon().getProjectiles().removeAll(removed);
		if(isEnemyCollision(level.getEnemies(), nextXPos)){
			player.loseHealth();
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
