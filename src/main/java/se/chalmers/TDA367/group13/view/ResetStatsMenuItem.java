package se.chalmers.TDA367.group13.view;

import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.TDA367.group13.util.Stats;

public class ResetStatsMenuItem extends MenuItem {

	public ResetStatsMenuItem(float x, float y, Image image, String name) {
		super(x, y, image, name);
	}
	
	@Override 
	public void clicked(StateBasedGame game){
		Stats.getInstance().reset();
	}

}
