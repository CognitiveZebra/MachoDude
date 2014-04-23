package se.chalmers.TDA367.group13.entities;

import se.chalmers.TDA367.group13.util.Direction;

public interface IMoveable {

	public float getNextRightX();
	
	public float getNextLeftX();
	
	public float getNextY();

	public void moveRight();
	
	public void moveLeft();

	public void moveY();
}
