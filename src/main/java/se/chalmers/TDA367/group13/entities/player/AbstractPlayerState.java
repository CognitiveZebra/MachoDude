package se.chalmers.TDA367.group13.entities.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.entities.AbstractMoveableEntityState;
import se.chalmers.TDA367.group13.entities.IMoveableState;

public abstract class AbstractPlayerState extends AbstractMoveableEntityState implements IMoveableState {
	private long stateStartedMillis;

	
	public AbstractPlayerState(){
		super();
		stateStartedMillis = System.currentTimeMillis();
	}
	
	
	public void setStateStartedMillis(){
		stateStartedMillis = System.currentTimeMillis(); 
	}
	
	public long getStateStartedMillis(){
		return stateStartedMillis;
	}
}
