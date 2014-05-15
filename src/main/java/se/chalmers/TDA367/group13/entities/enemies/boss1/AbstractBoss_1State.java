package se.chalmers.TDA367.group13.entities.enemies.boss1;

import org.newdawn.slick.Animation;

import se.chalmers.TDA367.group13.entities.AbstractMoveableEntityState;

public class AbstractBoss_1State extends AbstractMoveableEntityState {
	private boolean canMove;
	private boolean canFire;

	public AbstractBoss_1State(){
		super();
	}
	
	public AbstractBoss_1State(Animation left, Animation right){
		super(left,right);
	}
	
	public boolean getCanMove(){
		return canMove;
	}
	
	public void setCanMove(boolean b){
		canMove = b;
	}
	
	public boolean getCanFire() {
		return canFire;
	}

	public void setCanFire(boolean b) {
		canFire = b;
	}
}
