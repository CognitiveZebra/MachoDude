package se.chalmers.TDA367.group13.entities.enemies;

import org.newdawn.slick.Animation;

import se.chalmers.TDA367.group13.entities.AbstractMoveableEntityState;


public class AbstractEnemyState extends AbstractMoveableEntityState {
	public AbstractEnemyState(){
		super();
	}
	
	public AbstractEnemyState(Animation left, Animation right){
		super(left,right);
	}
	

}
