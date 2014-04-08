package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;

import se.chalmers.TDA367.group13.entities.Enemy.Direction;
import se.chalmers.TDA367.group13.entities.Enemy.State;

public abstract class Enemy extends Entity {

	protected XMLPackedSheet enemySheet;
	protected Direction direction;
	protected State state;
	private float gravity = 9.81f;

	public Enemy(float x, float y, String sheet, String xml) throws SlickException {
		super(x, y-new Image(sheet).getHeight() - 5, new Image(sheet));
		enemySheet = new XMLPackedSheet(sheet, xml);
		setImage(enemySheet.getSprite("walk1"));
		direction = Direction.LEFT;
		state = State.STILL;
	}
	
	public enum Direction {
		LEFT, RIGHT;
	}
	
	public enum State {
		WALKING, STILL;
	}

	public void setDirection(Direction d) {
		direction = d;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setState(State s) {
		state = s;
	}

	public State getState() {
		return state;
	}

	public abstract float getWalkingSpeed();

	public float getNextRightX() {
		return x + getWalkingSpeed();
	}
	
	public float getNextLeftX() {
		return x - getWalkingSpeed(); 
	}
	
	public float getNextY() {
		return y + gravity;
	}

	public void moveRight() {
		setX(getX() + getWalkingSpeed());
	}
	
	public void moveLeft() {
		setX(getX() - getWalkingSpeed());
	}

	public void moveY() {
		setY(getY() + gravity);		
	}


}
