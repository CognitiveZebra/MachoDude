package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;

public class Player extends Entity {
	
	private Direction direction = Direction.LEFT;
	private State state = State.STILL;
	private SpriteSheet playerSheet;
	private Animation still, walkLeft, walkRight, jumpLeft, jumpRight;
	private long jumpStart, jumpTime;
	private float jumpHeight = -32, gravity = 9; 
	
	
	public enum Direction {
		 LEFT,RIGHT;
	}
	public enum State {
		 WALKING,STILL,JUMPING;
	}
	
	
	public Player(float x, float y, Image spriteSheet) {
		super(x, y, spriteSheet);
		initAnimations();
		direction = Direction.RIGHT;
		state = State.STILL;
	}
		
	public float nextX(Input input){
		if(input.isKeyDown(Input.KEY_A)){
			direction = Direction.LEFT;
			return getX()-10;
		} else if(input.isKeyDown(Input.KEY_D)){
			direction = Direction.RIGHT;
			return getX()+10;
		} else {
			return getX();
		}
	}
	
	public float nextY(Input input){
		if(input.isKeyPressed(Input.KEY_W) && state != State.JUMPING){
			jumpStart = System.currentTimeMillis();
		}

		if(state == State.JUMPING){
			float jumpTime = ((System.currentTimeMillis() - jumpStart) % 1000);  
			return getY() + jumpHeight + gravity*jumpTime;
		} else {
			return getY();
		}
	}
	
	public void initAnimations(){
		playerSheet = new SpriteSheet(getImage(),32,32);
		//animation fixing goes here
	}
	
	public void render(Graphics g){
		Animation animation;
		switch(state){
		case JUMPING:
			animation = (direction == Direction.LEFT) ? jumpLeft : jumpRight;
			break;
		case STILL:
			animation = still;
			break;
		case WALKING:
			animation = (direction == Direction.LEFT) ? walkLeft : walkRight;
			break;
		default:
			animation = still;
			break;
		}
		g.drawAnimation(animation, getX(), getY());
	}
	
	public void setState(State s){
		state = s; 
	}
}
