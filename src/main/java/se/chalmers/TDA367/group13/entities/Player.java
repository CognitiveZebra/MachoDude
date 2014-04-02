package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;

public class Player extends Entity {

	private Direction direction;
	private State state;
	private SpriteSheet playerSheet;
	private Animation still, walkLeft, walkRight, jumpLeft, jumpRight;
	private long jumpStart;
	private float jumpHeight = -32, xVelocity = 10,gravity = 9.81f, yVelocity = gravity;

	public enum Direction {
		LEFT, RIGHT;
	}

	public enum State {
		WALKING, STILL, JUMPING;
	}

	public Player(float x, float y, Image spriteSheet) {
		super(x, y, spriteSheet);
		initAnimations();
		direction = Direction.RIGHT;
		state = State.STILL;
	}
	
	public void moveLeft(){
		direction = Direction.LEFT;
		setX(getX() - xVelocity);
	}
	
	public void moveRight(){
		direction = Direction.RIGHT;
		setX(getX() + xVelocity);
	}
	
	public void moveY(){
		setY(getY() + yVelocity);
	}
	
	public void updateYVelocity(){
		if(state == State.JUMPING){
			float jumpTime = ((System.currentTimeMillis() - jumpStart) % 1000);
			yVelocity = jumpHeight + gravity * jumpTime;
		} else {
			yVelocity = gravity;
		}
	}
	
	public void initAnimations() {
		playerSheet = new SpriteSheet(getImage(), 32, 32);
		Image[] oneFrameAnimation = new Image[]{getImage()};
		still = new Animation(oneFrameAnimation,100);
		walkLeft = new Animation(oneFrameAnimation,100);
		walkRight = new Animation(oneFrameAnimation,100);
		jumpLeft = new Animation(oneFrameAnimation,100);
		jumpRight = new Animation(oneFrameAnimation,100);	
	}

	public void render(Graphics g) {
		Animation animation;
		switch (state) {
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

	public void setState(State s) {
		state = s;
	}
	
	public void setJumpStart(long l){
		jumpStart = l; 
	}
	
}
