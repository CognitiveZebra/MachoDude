package se.chalmers.TDA367.group13.entities.enemies.boss1;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;


public class Boss_1Shooting extends  AbstractBoss_1State{

	public Boss_1Shooting(){
		velocity = new Vector2f(0,0);
		coolDown = 500;
	}
	
	public Boss_1Shooting(Animation a){
		super(a);
		velocity = new Vector2f(0,0);
		coolDown = 500;
	}
	
	
}
