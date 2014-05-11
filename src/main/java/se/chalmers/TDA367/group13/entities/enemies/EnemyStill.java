package se.chalmers.TDA367.group13.entities.enemies;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.util.Constants;

public class EnemyStill extends AbstractEnemyState {
	public EnemyStill(){
		super();
		velocity = new Vector2f(0,Constants.GRAVITY);
	}
	
	public EnemyStill(Animation left, Animation right){
		super(left, right);
		velocity = new Vector2f(0,Constants.GRAVITY);
	}
}
