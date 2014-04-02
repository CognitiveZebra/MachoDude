package se.chalmers.TDA367.group13;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import se.chalmers.TDA367.group13.entities.Block;
import se.chalmers.TDA367.group13.entities.Player;

public class GameModel {

	private GameContainer container;
	private Level level;
	private Player player;
	
	public GameModel(GameContainer gc) {
		this.container = gc;
		
		try {
			level = new Level_1();
			player = new Player(300, 300, "res/Sprites/MachoDude/sheet",  "res/Sprites/MachoDude/sheet.xml");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update (Input input){
		Player nextPos = player;
		
		if(input.isKeyDown(Input.KEY_A)){
			nextPos.moveLeft();
			if(isLegal(level.getBlocks(), nextPos)){
				player = nextPos;
			}
		}else if(input.isKeyDown(Input.KEY_D)){
			nextPos.moveRight();
			if(isLegal(level.getBlocks(), nextPos)){
				if(nextPos.getCenterX() > container.getWidth()/2){
					level.moveBlocks(player.getX() - nextPos.getX());
					level.getCamera().move(player.getX() - nextPos.getX());
				} else {
					player = nextPos;
				}
			} 
		}
		
	}
	
	public boolean isLegal(LinkedList<Block> blocks, Player player){
		for(Block b : blocks){
			if(b.intersects(player)){
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
