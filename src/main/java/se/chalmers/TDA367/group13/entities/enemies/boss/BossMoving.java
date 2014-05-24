package se.chalmers.TDA367.group13.entities.enemies.boss;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

public class BossMoving extends AbstractBossState {
	
	public BossMoving(){
		velocity = new Vector2f(0,0);
		coolDown = 1000;
	}
	
	public BossMoving(Animation a){
		super(a);
		velocity = new Vector2f(0,0);
		coolDown = 1000;
	}

}
