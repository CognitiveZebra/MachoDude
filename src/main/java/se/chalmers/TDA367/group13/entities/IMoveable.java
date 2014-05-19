package se.chalmers.TDA367.group13.entities;

import se.chalmers.TDA367.group13.util.Direction;

public interface IMoveable {

	public float getNextRightX(int delta);
	
	public float getNextLeftX(int delta);
	
	public float getNextY(int delta);

	public void moveRight(int delta);
	
	public void moveLeft(int delta);

	public void moveY(int delta);
}
