package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.entities.IState;
import se.chalmers.TDA367.group13.util.Direction;

public abstract class AbstractEntityState implements IState {
	private Animation animationLeft, animationRight;
	protected Vector2f velocity;
	
	public AbstractEntityState(){
	}
	
	@Override
	public Animation getAnimation(Direction d){
		Animation animation = (d == Direction.LEFT) ? animationLeft : animationRight;
		return animation;
	}

	@Override
	public void setAnimation(Animation a, Direction d) {
		if(d == Direction.LEFT){
			animationLeft = a;
		} else if(d == Direction.RIGHT){
			animationRight = a;
		}
	}

	public Vector2f getVelocity(){
		return velocity;
	}
}
