package se.chalmers.TDA367.group13.entities.enemies.boss1;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.entities.AbstractMoveableEntityState;

public class AbstractBoss_1State extends AbstractMoveableEntityState {
	private long stateStartedMillis;
	protected long coolDown;
	
	public AbstractBoss_1State(){
		super();
		stateStartedMillis = System.currentTimeMillis();
	}
	
	public AbstractBoss_1State(Animation left){
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
