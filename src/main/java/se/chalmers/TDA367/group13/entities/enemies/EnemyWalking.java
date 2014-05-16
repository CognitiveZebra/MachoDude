package se.chalmers.TDA367.group13.entities.enemies;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.util.Constants;

public class EnemyWalking extends AbstractEnemyState{
	
	public EnemyWalking(){
		super();
		velocity = new Vector2f(0.1f,Constants.GRAVITY);
	}
	
	public EnemyWalking(Animation left, Animation right){
		super(left, right);
		velocity = new Vector2f(0.1f,Constants.GRAVITY);
	}
}
