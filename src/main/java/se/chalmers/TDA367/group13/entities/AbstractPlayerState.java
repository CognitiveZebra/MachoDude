package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.util.Direction;

public abstract class AbstractPlayerState implements IState {
	private long stateStartedMillis;
	private Animation animationLeft, animationRight;
	
	public AbstractPlayerState(){
		stateStartedMillis = System.currentTimeMillis();
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

	public abstract Vector2f getVelocity();
	
	public void setStateStartedMillis(){
		stateStartedMillis = System.currentTimeMillis(); 
	}
	
	public long getStateStartedMillis(){
		return stateStartedMillis;
	}



}
