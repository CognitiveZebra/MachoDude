package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Image;

import se.chalmers.TDA367.group13.util.Direction;

public class MoveableEntity extends Entity implements IMoveable{
	
	protected AbstractMoveableEntityState state; 
	protected Direction direction;
	
	public MoveableEntity(float x, float y, Image image){
		super(x,y, image);
	}

	@Override
	public float getNextLeftX(){
		return x - state.getVelocity().x;
	}
	@Override
	public float getNextRightX(){
		return x + state.getVelocity().x;
	}
	@Override
	public float getNextY(){
		return y + state.getVelocity().y;
	}
	@Override
	public void moveLeft(){
		direction = Direction.LEFT;
		setX(x - state.getVelocity().x);
	}
	@Override
	public void moveRight(){
		direction = Direction.RIGHT;
		setX(x + state.getVelocity().x);
	}
	
	@Override
	public void moveY(){
		setY(getY() + state.getVelocity().y);
	}
}
