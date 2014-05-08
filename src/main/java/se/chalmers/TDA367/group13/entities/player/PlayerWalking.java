package se.chalmers.TDA367.group13.entities.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.entities.IState;
import se.chalmers.TDA367.group13.util.Constants;
import se.chalmers.TDA367.group13.util.Direction;

public class PlayerWalking extends AbstractPlayerState implements IState {
	
	public PlayerWalking(){
		super();
		velocity = new Vector2f(4,Constants.gravity);
	}

}
