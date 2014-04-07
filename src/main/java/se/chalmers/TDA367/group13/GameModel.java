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

	public void update(Input input) {
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
