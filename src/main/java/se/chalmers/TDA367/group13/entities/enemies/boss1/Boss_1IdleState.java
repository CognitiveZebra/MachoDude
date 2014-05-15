package se.chalmers.TDA367.group13.entities.enemies.boss1;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.util.Constants;

public class Boss_1IdleState extends AbstractBoss_1State {

	public Boss_1IdleState(){
		super();
		setCanMove(true);
		setCanFire(false);
	}

	public Boss_1IdleState(Animation left, Animation right){
		super(left, right);
	}

}
