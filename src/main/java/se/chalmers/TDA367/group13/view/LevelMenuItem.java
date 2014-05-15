package se.chalmers.TDA367.group13.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.TDA367.group13.controller.GameStateController;
import se.chalmers.TDA367.group13.entities.Entity;
import se.chalmers.TDA367.group13.util.Util;


public class LevelMenuItem extends MenuItem {
	private int level;
	public LevelMenuItem(float x, float y, Image image, String name, int ID, int level) {
		super(x, y, image, name, ID);
		this.level = level;
	}
	
	
	@Override
	public void clicked(StateBasedGame sbg){
		GameStateController.getGameState().setLevel(level);
		sbg.enterState(ID);
	}

}


