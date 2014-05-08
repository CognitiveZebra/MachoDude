package se.chalmers.TDA367.group13.entities.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.entities.IState;
import se.chalmers.TDA367.group13.util.Constants;
import se.chalmers.TDA367.group13.util.Direction;

public class PlayerJumping extends AbstractPlayerState implements IState {
	
	private float jumpHeight = -8;
	
	public PlayerJumping(){
		super();
		velocity = new Vector2f(4, Constants.gravity);
	}
	
	@Override
	public Vector2f getVelocity() {
		velocity.y = getYVelocity();
		return velocity;
	}


	public float getYVelocity(){
		float jumpTime =  (((System.currentTimeMillis() - getStateStartedMillis()))*(float)Math.pow(10, -3));
		
		float y = jumpHeight + Constants.gravity * jumpTime;
		
		if(y > Constants.gravity){
			y = Constants.gravity;
		}
		return y;
	}

}
