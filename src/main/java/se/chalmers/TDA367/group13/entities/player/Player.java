package se.chalmers.TDA367.group13.entities.player;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.geom.Point;

import se.chalmers.TDA367.group13.entities.Entity;
import se.chalmers.TDA367.group13.entities.IMoveable;
import se.chalmers.TDA367.group13.entities.weapon.TestWeapon;
import se.chalmers.TDA367.group13.entities.weapon.Weapon;
import se.chalmers.TDA367.group13.util.Direction;
import se.chalmers.TDA367.group13.util.Stats;


public class Player extends Entity implements IMoveable{

	private Direction direction;
	private Weapon weapon;
	private XMLPackedSheet playerSheet;
	private Image [] right, left, standLeft, standRight, jumpingLeft, jumpingRight;
	private long lastHurt, invincibility = 1000;
	private int health = 5;
	private Point rightShoulder, leftShoulder;
	private HealthBar healthBar;
	private AbstractPlayerState state, playerJumping, playerStill, playerWalking; 


	public Player(float x, float y, String sheet, String xml) throws SlickException {
		super(x, y, new Image(sheet));
		playerSheet = new XMLPackedSheet(sheet, xml);
		setImage(playerSheet.getSprite("Still.png"));
		rightShoulder = new Point(29, 13);
		leftShoulder = new Point(15, 13);
		weapon = new TestWeapon(x, y);
		
		direction = Direction.RIGHT;
		
		playerStill = new PlayerStill();
		playerWalking = new PlayerWalking();
		playerJumping = new PlayerJumping();
		state = playerStill; 
		initAnimations();
		healthBar = new HealthBar(health);
	}
	
	@Override
	public float getNextLeftX(){
		return x - state.getVelocity().x;
	}
	@Override
	public float getNextRightX(){
		return x + state.getVelocity().x;
	}
	@Override
	public float getNextY(){
		return y + state.getVelocity().y;
	}
	@Override
	public void moveLeft(){
		direction = Direction.LEFT;
		setX(x - state.getVelocity().x);
	}
	@Override
	public void moveRight(){
		direction = Direction.RIGHT;
		setX(x + state.getVelocity().x);
	}
	@Override
	public void moveY(){
		setY(getY() + state.getVelocity().y);
	}
		
	public void initAnimations() {
		left = new Image[]
			{playerSheet.getSprite("walk1.png"),playerSheet.getSprite("walk2.png"),
			playerSheet.getSprite("walk3.png"),playerSheet.getSprite("walk4.png")};
		
		right = new Image[]
				{playerSheet.getSprite("walk1.png").getFlippedCopy(true, false),
				playerSheet.getSprite("walk2.png").getFlippedCopy(true, false),
				playerSheet.getSprite("walk3.png").getFlippedCopy(true, false),
				playerSheet.getSprite("walk4.png").getFlippedCopy(true, false)};
		
		standLeft = new Image[]{playerSheet.getSprite("Still.png")};
		standRight = new Image[]{playerSheet.getSprite("Still.png").getFlippedCopy(true, false)};
		
		jumpingLeft = new Image[]{playerSheet.getSprite("jump.png")};
		jumpingRight =new Image[]{playerSheet.getSprite("jump.png").getFlippedCopy(true, false)};
	
		resize(2);	
		int animationSpeed = 200;
		
		playerStill.setAnimation(new Animation(standLeft,animationSpeed), Direction.LEFT);
		playerStill.setAnimation(new Animation(standRight,animationSpeed), Direction.RIGHT);
		playerWalking.setAnimation(new Animation(left,animationSpeed),Direction.LEFT);
		playerWalking.setAnimation(new Animation(right,animationSpeed),Direction.RIGHT);
		playerJumping.setAnimation(new Animation(jumpingLeft,animationSpeed),Direction.LEFT);
		playerJumping.setAnimation(new Animation(jumpingRight,animationSpeed),Direction.RIGHT);	
	}
	
	public void moveWeapon(){
		if (direction == Direction.LEFT){
			weapon.setCenterX(x + leftShoulder.getX());
			weapon.setCenterY(y + leftShoulder.getY());
		}
		else{
			weapon.setCenterX(x + rightShoulder.getX());
			weapon.setCenterY(y + rightShoulder.getY());
		}
	}

	public void render(Graphics g) {
		if(isInvincible()){
			g.drawAnimation(state.getAnimation(direction), getX(), getY(), getInvincibleColor());
			weapon.render(g, direction, getInvincibleColor());
		} else {
			g.drawAnimation(state.getAnimation(direction), getX(), getY());
			weapon.render(g, direction);
		}


		
		healthBar.render(g, health);
		
	}

	public void setPlayerStill() {
		playerStill.setStateStartedMillis();
		state = playerStill;
	}
	
	public void setPlayerWalking() {
		playerWalking.setStateStartedMillis();
		state = playerWalking;
	}
	
	public void setPlayerJumping() {
		playerJumping.setStateStartedMillis();
		state = playerJumping;
	}
	
	public AbstractPlayerState getPlayerJumping() {
		return playerJumping;
	}

	public AbstractPlayerState getPlayerStill() {
		return playerStill;
	}

	public AbstractPlayerState getPlayerWalking() {
		return playerWalking;
	}

	public AbstractPlayerState getState(){
		return state;
	}
	
	public Direction getDirection() {
		return direction;
	}
		
	public Image[] resizeImages(Image[] images, float scale){
		for (int i = 0; i<images.length; i++){
			images[i].setFilter(Image.FILTER_NEAREST);
			images[i] = images[i].getScaledCopy(scale);
		
		}
		return images;
	}
	
	@Override
	public void resize(float scale){
		right = resizeImages(right, scale); 
		left = resizeImages(left, scale); 
		standLeft =  resizeImages(standLeft, scale);
		standRight = resizeImages(standRight, scale); 
		jumpingLeft = resizeImages(jumpingLeft, scale); 
		jumpingRight = resizeImages(jumpingRight, scale);
		leftShoulder.setLocation(leftShoulder.getX() * scale, leftShoulder.getY() *scale);
		rightShoulder.setLocation(rightShoulder.getX() * scale, rightShoulder.getY() *scale);
		setImage(standRight[0]);
		weapon.resize(scale);
	}
	
	public void loseHealth(){
		if ((System.currentTimeMillis()-lastHurt) > invincibility){
			health = health -1;
			lastHurt = System.currentTimeMillis();
			Stats.getInstance().incrementDamageTaken();
		}
	}
	
	public boolean isDead(){
		return health <= 0;
	}
	
	public boolean isInvincible(){
		return timeSinceHurt() < invincibility;
	}
	
	public float timeSinceHurt(){
		return System.currentTimeMillis() - lastHurt;
	}
	
	public Color getInvincibleColor(){
		return new Color(((int)(timeSinceHurt() % 255)), 0,0);
	}
	
	public Weapon getWeapon() {
		return weapon;
	}
	
}
