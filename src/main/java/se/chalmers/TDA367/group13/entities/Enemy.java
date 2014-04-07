package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;

import se.chalmers.TDA367.group13.entities.Enemy.Direction;

public abstract class Enemy extends Entity {

	protected XMLPackedSheet enemySheet;
	protected Direction direction;
	protected State state;

	public Enemy(float x, float y, String sheet, String xml) throws SlickException {
		super(x, y-new Image(sheet).getHeight(), new Image(sheet));
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


}
