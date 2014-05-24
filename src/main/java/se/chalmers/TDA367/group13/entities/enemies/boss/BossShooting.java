package se.chalmers.TDA367.group13.entities.enemies.boss;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;


public class BossShooting extends  AbstractBossState{

	public BossShooting(){
		velocity = new Vector2f(0,0);
		coolDown = 500;
	}
	
	public BossShooting(Animation a){
		super(a);
		velocity = new Vector2f(0,0);
		coolDown = 500;
	}
	
	
}
