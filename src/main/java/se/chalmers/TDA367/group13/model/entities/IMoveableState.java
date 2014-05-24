package se.chalmers.TDA367.group13.model.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.util.Direction;

public interface IMoveableState {

	Animation getAnimation(Direction d);
	void setAnimation(Animation a, Direction d);
	Vector2f getVelocity();

}
