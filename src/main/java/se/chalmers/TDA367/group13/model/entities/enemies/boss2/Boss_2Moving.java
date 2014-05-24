package se.chalmers.TDA367.group13.model.entities.enemies.boss2;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

public class Boss_2Moving extends AbstractBoss_2State {
	
	public Boss_2Moving(){
		velocity = new Vector2f(0,0);
		coolDown = 1000;
	}
	
	public Boss_2Moving(Animation a){
		super(a);
		velocity = new Vector2f(0,0);
		coolDown = 1000;
	}

}
