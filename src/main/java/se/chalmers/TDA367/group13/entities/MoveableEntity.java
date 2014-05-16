package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Image;

import se.chalmers.TDA367.group13.Game;
import se.chalmers.TDA367.group13.util.Constants;
import se.chalmers.TDA367.group13.util.Direction;

public class MoveableEntity extends Entity implements IMoveable{
	
	protected AbstractMoveableEntityState state; 
	protected Direction direction;
	
	public MoveableEntity(float x, float y, Image image){
		super(x,y, image);
	}

	public boolean isLag(int delta){
		return (delta > 20 + (1000 / Game.FRAME_TARGET));
	}
	
	public float getNextLeftX(int delta){
		return x - state.getVelocity().x*delta;
	}

	public float getNextRightX(int delta){
		return x + state.getVelocity().x*delta;
	}

	public float getNextY(int delta){
		if(!isLag(delta)){
			return y + state.getVelocity().y*delta;
		} else {
			return y;
		}
		
	}
	public void moveLeft(int delta){
		direction = Direction.LEFT;
		setX(x - state.getVelocity().x*delta);
	}

	public void moveRight(int delta){
		direction = Direction.RIGHT;
		setX(x + state.getVelocity().x*delta);
	}
	

	public void moveY(int delta){
		if(!isLag(delta)){
			setY(getY() + state.getVelocity().y*delta);
		}
	}
}
