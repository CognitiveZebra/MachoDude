package se.chalmers.TDA367.group13.entities.enemies;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.geom.Point;

import se.chalmers.TDA367.group13.entities.AbstractMoveableEntityState;
import se.chalmers.TDA367.group13.entities.IDestructable;
import se.chalmers.TDA367.group13.entities.IMoveable;
import se.chalmers.TDA367.group13.entities.MoveableEntity;
import se.chalmers.TDA367.group13.entities.weapon.Weapon;
import se.chalmers.TDA367.group13.util.Constants;
import se.chalmers.TDA367.group13.util.Direction;


public abstract class Enemy extends MoveableEntity implements IDestructable {

	protected XMLPackedSheet enemySheet;
	protected Weapon weapon;
	protected int health, maxHealth;
	private HealthBarEnemy healthbar = new HealthBarEnemy();
	protected int value = 1;
	protected Animation stillLeft, stillRight, walkLeft, walkRight;
	protected Point rightShoulder, leftShoulder;
	protected Sound hurtSound, deathSound;
	protected AbstractEnemyState walking, still; 
	
	public Enemy(float x, float y, String sheet, String xml, int scale) throws SlickException {
		super(x, y - new Image(sheet).getWidth()*scale - 1, new Image(sheet));
		enemySheet = new XMLPackedSheet(sheet, xml);
		setImage(enemySheet.getSprite("walk1"));
		direction = Direction.LEFT;
	}
	
	@Override 
	public void render(Graphics g){
		healthbar.render(this,g);
		g.drawAnimation(state.getAnimation(direction), x, y);
	}
		
	public void setDirection(Direction d) {
		direction = d;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setState(AbstractMoveableEntityState s) {
		state = s;
	}

	public AbstractMoveableEntityState getStillState() {
		return still;
	}
	
	public AbstractMoveableEntityState getWalkingState() {
		return walking;
	}
	
	public AbstractMoveableEntityState getState() {
		return state;
	}

	
	public abstract Weapon getWeapon();


	public void loseHealth(){	
		health = health -1;
		hurtSound.play();
	}
	
	public boolean isDestroyed() {
		if(health <= 0){
		deathSound.play();
		return true;
		}
		return false;
	}
	
	public boolean isHurt(){
		return health < maxHealth;
	}
	
	public int getValue(){
		return value;
	}



}
