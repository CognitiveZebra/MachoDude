package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;


public abstract class Enemy extends Entity implements IMoveable, IDestructable {

	protected XMLPackedSheet enemySheet;
	protected Direction direction;
	protected Weapon weapon;
	protected State state;
	private float gravity = 9.81f;
	protected int health, maxHealth;
	private HealthBarEnemy healthbar = new HealthBarEnemy();
	
	
	public Enemy(float x, float y, String sheet, String xml, int scale) throws SlickException {
		super(x, y - new Image(sheet).getWidth()*scale - 1, new Image(sheet));
		enemySheet = new XMLPackedSheet(sheet, xml);
		setImage(enemySheet.getSprite("walk1"));
		direction = Direction.LEFT;
		state = State.STILL;
	}
	
	@Override 
	public void render(Graphics g){
		healthbar.render(this,g);
	}
	
	public enum State {
		WALKING, STILL;
	}
	
	public void setDirection(Direction d) {
		direction = d;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setState(State s) {
		state = s;
	}

	public State getState() {
		return state;
	}

	public abstract float getWalkingSpeed();
	
	public abstract Weapon getWeapon();

	public float getNextRightX() {
		return x + getWalkingSpeed();
	}
	
	public float getNextLeftX() {
		return x - getWalkingSpeed(); 
	}
	
	public float getNextY() {
		return y + gravity;
	}

	public void moveRight() {
		setX(getX() + getWalkingSpeed());
	}
	
	public void moveLeft() {
		setX(getX() - getWalkingSpeed());
	}

	public void moveY() {
		setY(getY() + gravity);		
	}

	public void loseHealth(){	
		health = health -1;
	}
	
	public boolean isDead() {
		return health <= 0;
	}
	
	public boolean isHurt(){
		return health < maxHealth;
	}



}
