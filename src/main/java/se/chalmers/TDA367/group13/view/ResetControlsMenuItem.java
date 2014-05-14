package se.chalmers.TDA367.group13.view;

import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.TDA367.group13.util.Controls;

public class ResetControlsMenuItem extends MenuItem {

	public ResetControlsMenuItem(float x, float y, Image image, String name) {
		super(x, y, image, name);
	}
	
	@Override
	public void clicked(StateBasedGame game){
		Controls.getInstance().reset();
	}

}
