package se.chalmers.TDA367.group13;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import se.chalmers.TDA367.group13.entities.Block;
import se.chalmers.TDA367.group13.entities.Player;
import se.chalmers.TDA367.group13.entities.Player.State;

public class GameModel {

	private GameContainer container;
	private Level level;
	private Player player;
	
	public GameModel(GameContainer gc) {
		this.container = gc;
		
		try {
			level = new Level_1();
			player = new Player(300, 300, "res/Sprites/MachoDude/sheet.png",  "res/Sprites/MachoDude/sheet.xml");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update (Input input){
		Rectangle nextPos = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());

		
		if(input.isKeyDown(Input.KEY_A)){
			nextPos.setX(player.nextLeftX());
			if(isLegal(level.getBlocks(), nextPos)){
				player.moveLeft();
			}
		} else if(input.isKeyDown(Input.KEY_D)){
			nextPos.setX(player.nextRightX());
			if(isLegal(level.getBlocks(), nextPos)){
				
				if(nextPos.getCenterX() > (container.getWidth()/2)){
					player.setState(State.WALKING);
					level.moveBlocks(player.getX() - nextPos.getX());
					level.getCamera().move(player.getX() - nextPos.getX());
				} else {
					player.moveRight();
				}
			} 
		} else if(input.isKeyDown(Input.KEY_W)){
				
		} else {
			if(player.getState() != State.JUMPING){
				player.setState(State.STILL);
			}
		}
		
		
	}
	
	public boolean isLegal(LinkedList<Block> blocks, Rectangle hitbox){
		for(Block b : blocks){
			if(hitbox.intersects(b)){
				return false;
			}
		}
		
		return true;
	}
	
	public Level getLevel(){
		return level;
	}
	
	public Player getPlayer(){
		return player;
	}
}
