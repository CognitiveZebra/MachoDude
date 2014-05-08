package se.chalmers.TDA367.group13.entities.enemies;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.util.Constants;

public class Enemy1Still extends AbstractEnemyState {
	public Enemy1Still(){
		super();
		velocity = new Vector2f(0,Constants.GRAVITY);
	}
	
	public Enemy1Still(Animation left, Animation right){
		super(left, right);
		velocity = new Vector2f(0,Constants.GRAVITY);
	}
}
