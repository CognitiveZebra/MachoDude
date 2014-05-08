package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Animation;

import se.chalmers.TDA367.group13.util.Direction;

public interface IMoveableState {

	Animation getAnimation(Direction d);
	void setAnimation(Animation a, Direction d);

}
