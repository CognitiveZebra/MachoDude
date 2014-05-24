package se.chalmers.TDA367.group13.model.entities.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.model.entities.IMoveableState;
import se.chalmers.TDA367.group13.util.Constants;
import se.chalmers.TDA367.group13.util.Direction;

public class PlayerJumping extends AbstractPlayerState implements IMoveableState {
	
	private float jumpHeight = -0.5f;
	
	public PlayerJumping(){
		super();
		velocity = new Vector2f(0.25f, Constants.GRAVITY);
	}
	
	@Override
	public Vector2f getVelocity() {
		velocity.y = getYVelocity();
		return velocity;
	}


	public float getYVelocity(){
		float jumpTime =  (((System.currentTimeMillis() - getStateStartedMillis()))*(float)Math.pow(10, -3));
		
		float y = jumpHeight + Constants.GRAVITY * jumpTime;
		
		if(y > Constants.GRAVITY){
			y = Constants.GRAVITY;
		}
		return y;
	}

}
