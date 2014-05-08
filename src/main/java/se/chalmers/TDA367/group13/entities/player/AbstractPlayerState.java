package se.chalmers.TDA367.group13.entities.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.entities.IMoveableState;
import se.chalmers.TDA367.group13.util.Direction;

public abstract class AbstractPlayerState implements IMoveableState {
	private Animation animationLeft, animationRight;
	private long stateStartedMillis;
	protected Vector2f velocity;

	
	public AbstractPlayerState(){
		super();
		stateStartedMillis = System.currentTimeMillis();
	}
	
	@Override
	public Animation getAnimation(Direction d){
		return (d == Direction.LEFT) ? animationLeft : animationRight;
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
	
	public void setStateStartedMillis(){
		stateStartedMillis = System.currentTimeMillis(); 
	}
	
	public long getStateStartedMillis(){
		return stateStartedMillis;
	}
}
