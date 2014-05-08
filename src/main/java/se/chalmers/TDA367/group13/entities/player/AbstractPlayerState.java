package se.chalmers.TDA367.group13.entities.player;

import se.chalmers.TDA367.group13.entities.AbstractEntityState;
import se.chalmers.TDA367.group13.entities.IState;

public abstract class AbstractPlayerState extends AbstractEntityState implements IState {
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
