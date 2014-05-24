package se.chalmers.TDA367.group13.view;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.TDA367.group13.controller.GameStateController;
import se.chalmers.TDA367.group13.util.Stats;


public class LevelMenuItem extends MenuItem {
	private int level;
	private Image lock;
	public LevelMenuItem(float x, float y, Image image, String name, int ID, int level) {
		super(x, y, image, name, ID);
		this.level = level;
		try {
			lock = new Image("res/GUI/lock.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void clicked(StateBasedGame sbg){
		if(Stats.getInstance().getHighestLevel() >= level){
			((GameStateController)sbg).setLevel(level);
			sbg.enterState(ID);
		}
	}
	
	@Override
	public void render(Graphics g, boolean isSelected){
		super.render(g, isSelected);
		if(Stats.getInstance().getHighestLevel() < level){
			g.drawImage(lock, getCenterX() + 80, getCenterY() - lock.getHeight()/2);
		}
	}

}


