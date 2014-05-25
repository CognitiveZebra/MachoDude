package se.chalmers.TDA367.group13.model.entities.boss;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.model.entities.AbstractMoveableEntityState;

public class AbstractBossState extends AbstractMoveableEntityState {
	private long stateStartedMillis;
	protected long coolDown;
	
	public AbstractBossState(){
		super();
		stateStartedMillis = System.currentTimeMillis();
	}
	
	public AbstractBossState(Animation left){
		super();
		stateStartedMillis = System.currentTimeMillis();
		animationLeft = left;
	}
	
	public void setStateStartedMillis(){
		stateStartedMillis = System.currentTimeMillis(); 
	}
	
	public long getStateStartedMillis(){
		return stateStartedMillis;
	}
	
	public boolean isNextState(){
		return System.currentTimeMillis() - stateStartedMillis > coolDown;
	}

	public void setVelocity(Vector2f v) {
		velocity = v;
		
	}

	
}
