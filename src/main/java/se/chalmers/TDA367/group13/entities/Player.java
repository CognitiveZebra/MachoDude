package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.XMLPackedSheet;

public class Player extends Entity {

	private Direction direction;
	private State state;
	private XMLPackedSheet playerSheet;
	private Animation still, walkLeft, walkRight, jumpLeft, jumpRight;
	private long jumpStart;
	private float jumpHeight = -32, xVelocity = 10,gravity = 9.81f, yVelocity = gravity;

	public enum Direction {
		LEFT, RIGHT;
	}

	public enum State {
		WALKING, STILL, JUMPING;
	}

	public Player(float x, float y, String sheet, String xml) throws SlickException {
		super(x, y, new Image(sheet));
		playerSheet = new XMLPackedSheet(sheet, xml);
		setImage(playerSheet.getSprite("Still.png"));
		initAnimations();
		direction = Direction.RIGHT;
		state = State.STILL;
	}
	
	public float nextLeftX(){
		return x - xVelocity;
	}
	
	public float nextRightX(){
		return x + xVelocity;
	}
	
	public void moveLeft(){
		setState(State.WALKING);
		direction = Direction.LEFT;
		setX(getX() - xVelocity);
	}
	
	public void moveRight(){
		setState(State.WALKING);
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
		Image[]  left = {playerSheet.getSprite("walk1.png"),playerSheet.getSprite("walk2.png"),
				playerSheet.getSprite("walk3.png"),playerSheet.getSprite("walk4.png")};
		
		Image[]  right = {playerSheet.getSprite("walk1.png").getFlippedCopy(true, false),
				playerSheet.getSprite("walk2.png").getFlippedCopy(true, false),
				playerSheet.getSprite("walk3.png").getFlippedCopy(true, false),
				playerSheet.getSprite("walk4.png").getFlippedCopy(true, false)};
		
		
		
		still = new Animation(new Image[]{playerSheet.getSprite("Still.png")},100);
		walkLeft = new Animation(left,100);
		walkRight = new Animation(right,100);
		jumpLeft = new Animation(new Image[]{playerSheet.getSprite("jump.png")},100);
		jumpRight = new Animation(new Image[]{playerSheet.getSprite("jump.png").getFlippedCopy(true, false)},100);	
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
	
	public State getState(){
		return state;
	}
	
	public void setJumpStart(long l){
		jumpStart = l; 
	}
	
}
