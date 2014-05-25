package se.chalmers.TDA367.group13.model.entities.player;

import java.util.LinkedList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.geom.Point;

import se.chalmers.TDA367.group13.model.entities.MoveableEntity;
import se.chalmers.TDA367.group13.model.entities.projectile.Projectile;
import se.chalmers.TDA367.group13.model.entities.weapon.PlayerWeapon;
import se.chalmers.TDA367.group13.model.entities.weapon.Weapon;
import se.chalmers.TDA367.group13.util.Constants;
import se.chalmers.TDA367.group13.util.Direction;
import se.chalmers.TDA367.group13.util.Stats;


public class Player extends MoveableEntity {

	private Weapon weapon;
	private XMLPackedSheet playerSheet;
	private Image [] right, left, standLeft, standRight, jumpingLeft, jumpingRight;
	private long lastHurt, invincibility = 1000;
	private int health = 10;
	private Point rightShoulder, leftShoulder;
	private HealthBar healthBar;
	private AbstractPlayerState playerJumping, playerStill, playerWalking;
	private Sound jumpSound;
	private LinkedList<Projectile> projectiles;
	public final static int scale = 2;



	public Player(float x, float y, String sheet, String xml) throws SlickException {
		super(x, y, new Image(sheet));
		playerSheet = new XMLPackedSheet(sheet, xml);
		setImage(playerSheet.getSprite("Still.png"));
		rightShoulder = new Point(29, 13);
		leftShoulder = new Point(15, 13);
		weapon = new PlayerWeapon(x, y);
		projectiles = new LinkedList<Projectile>();
		direction = Direction.RIGHT;
		
		playerStill = new PlayerStill();
		playerWalking = new PlayerWalking();
		playerJumping = new PlayerJumping();
		state = playerStill; 
		initAnimations();
		healthBar = new HealthBar(health);
		
		jumpSound = new Sound("/res/Sound/Jump.wav");
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
	
		resize(scale);	
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
		if(isFlashing()){
			g.drawAnimation(state.getAnimation(direction), getX(), getY(), getInvincibleColor());
			weapon.render(g, getInvincibleColor());
		} else {
			g.drawAnimation(state.getAnimation(direction), getX(), getY());
			weapon.render(g, direction);
		}
		healthBar.render(g, health);
		for (Projectile p : projectiles) {
			p.render(g);
		}
		
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
		jumpSound.play();
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
		return (AbstractPlayerState) state;
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
	
	public void loseHealth(float damage){
		if ((System.currentTimeMillis()-lastHurt) > invincibility){
			health -= damage;
			lastHurt = System.currentTimeMillis();
			Stats.getInstance().incrementDamageTaken();
		}
	}
	
	public void loseHealth(){
		loseHealth(1);
	}
	
	public boolean isDead(){
		return y > Constants.HEIGHT || health <= 0;
	}
	
	public boolean isFlashing(){
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
	
	public Projectile fireWeapon(){
		return weapon.fireWeapon(direction);
	}

	public LinkedList<Projectile> getProjectiles() {
		return projectiles;
	}
	@Override 
	public void moveY(int delta){
		((PlayerJumping)playerJumping).getYVelocity();
		super.moveY(delta);	
	}
	@Override
	public float getNextY(int delta){
		((PlayerJumping)playerJumping).getYVelocity();
		return super.getNextY(delta);	
	}



	public int getHealth() {
		return health;
	}
	
}
