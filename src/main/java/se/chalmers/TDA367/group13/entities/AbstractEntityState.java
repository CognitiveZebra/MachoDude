package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.entities.IMoveableState;
import se.chalmers.TDA367.group13.util.Direction;

public abstract class AbstractEntityState implements IState{
	private Animation animation;

	
	public AbstractEntityState(Animation a){
		animation = a; 
	}
	
	@Override
	public Animation getAnimation(){
		return animation;
	}

	@Override
	public void setAnimation(Animation a) {
		this.animation = a;
	}


}
