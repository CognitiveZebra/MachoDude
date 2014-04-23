package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.util.Direction;

public interface IState {

	Animation getAnimation(Direction d);
	void setAnimation(Animation a, Direction d);

}
