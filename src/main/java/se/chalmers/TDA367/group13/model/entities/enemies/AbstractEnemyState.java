package se.chalmers.TDA367.group13.model.entities.enemies;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.model.entities.AbstractMoveableEntityState;


public class AbstractEnemyState extends AbstractMoveableEntityState {
	public AbstractEnemyState(){
		super();
	}
	
	public AbstractEnemyState(Animation left, Animation right){
		super(left,right);
	}

	

}
