package se.chalmers.TDA367.group13.entities.enemies.boss1;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.util.Constants;

public class Boss_1LoadState extends AbstractBoss_1State {

	public Boss_1LoadState(){
		super();
		setCanMove(false);
		setCanFire(false);
	}

	public Boss_1LoadState(Animation left, Animation right){
		super(left, right);
	}

}
