package se.chalmers.TDA367.group13.entities.enemies;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;

import se.chalmers.TDA367.group13.entities.Entity;
import se.chalmers.TDA367.group13.entities.IDestructable;
import se.chalmers.TDA367.group13.entities.IMoveable;
import se.chalmers.TDA367.group13.entities.Projectile;
import se.chalmers.TDA367.group13.entities.enemies.Enemy.State;
import se.chalmers.TDA367.group13.util.Direction;

public class Boss_1 extends Entity implements IMoveable, IDestructable {
	private XMLPackedSheet bossSheet;
	private Direction direction;
	private State state;
	private Image laserBegin, laserBeam, rightLaserBegin;
	private Image[] mouthOpen, mouthClose, rightMouthOpen, rightMouthClose;
	private Animation openMouth, closeMouth, rightOpenMouth, rightCloseMouth,  still, open, rightOpen, rightStill;
	private float health, walkingspeed;
	
	
	
	public Boss_1(float x, float y) throws SlickException{
		super(x, y, new Image("/res/Sprites/Bosses/1/boss_1_head.png"));
		bossSheet = new XMLPackedSheet("/res/Sprites/Bosses/1/boss_1_sheet.png","/res/Sprites/Bosses/1/boss_1_sheet.xml");
		this.health = 20;
		this.walkingspeed = 3;

		direction = Direction.LEFT;
		state = State.STILL;
		
		initAnimations();
	}
	
	public enum State {
		STILL, OPENMOUTH, CLOSEMOUTH, OPEN;
	}
	
	public void initAnimations() throws SlickException{
		int animationSpeed = 200;
		
		still = new Animation(new Image[] { bossSheet.getSprite("still.png")}, animationSpeed);
		open = new Animation(new Image[] { bossSheet.getSprite("open5.png")}, animationSpeed);
		rightOpen = new Animation(new Image[] { bossSheet.getSprite("open5.png").getFlippedCopy(true, false)}, animationSpeed);
		rightStill = new Animation(new Image[] { bossSheet.getSprite("still.png").getFlippedCopy(true, false)}, animationSpeed);
		
		mouthOpen = new Image[]{
				bossSheet.getSprite("open1.png"),
				bossSheet.getSprite("open2.png"),
				bossSheet.getSprite("open3.png"),
				bossSheet.getSprite("open4.png"),
				bossSheet.getSprite("open5.png")
		};
		
		rightMouthOpen = new Image[]{
				bossSheet.getSprite("open1.png").getFlippedCopy(true, false),
				bossSheet.getSprite("open2.png").getFlippedCopy(true, false),
				bossSheet.getSprite("open3.png").getFlippedCopy(true, false),
				bossSheet.getSprite("open4.png").getFlippedCopy(true, false),
				bossSheet.getSprite("open5.png").getFlippedCopy(true, false)
		};
		
		mouthClose = new Image[]{
				bossSheet.getSprite("open5.png"),
				bossSheet.getSprite("open4.png"),
				bossSheet.getSprite("open3.png"),
				bossSheet.getSprite("open2.png"),
				bossSheet.getSprite("open1.png")
		};
		
		rightMouthClose = new Image[]{
				bossSheet.getSprite("open5.png").getFlippedCopy(true, false),
				bossSheet.getSprite("open4.png").getFlippedCopy(true, false),
				bossSheet.getSprite("open3.png").getFlippedCopy(true, false),
				bossSheet.getSprite("open2.png").getFlippedCopy(true, false),
				bossSheet.getSprite("open1.png").getFlippedCopy(true, false)
		};
		resize(5);
		
		openMouth = new Animation(mouthOpen, animationSpeed);
		closeMouth = new Animation(mouthClose, animationSpeed);
		
		rightOpenMouth = new Animation(rightMouthOpen, animationSpeed);
		rightCloseMouth = new Animation(mouthClose, animationSpeed);
		

		laserBegin = new Image("/res/Sprites/Bosses/1/laser_begin.png");
		laserBeam = new Image("/res/Sprites/Bosses/1/laser_beam.png");
		
		rightLaserBegin = laserBegin.getFlippedCopy(true, false);
		
	}



	public float getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}
	
	public void render(Graphics g) {
		super.render(g);
		
		Animation animation;
		switch (state) {
		case STILL:
			animation = (direction == Direction.LEFT) ? still: rightStill;
			break;
		case OPENMOUTH:
			animation = (direction == Direction.LEFT) ? openMouth : rightOpenMouth;
			break;
		case CLOSEMOUTH:
			animation = (direction == Direction.LEFT) ? closeMouth : rightCloseMouth;
			break;
		case OPEN:
			animation = (direction == Direction.LEFT) ? open : rightOpen;
			break;
		default:
			animation = still;
			break;
		}
		g.drawAnimation(animation, getX(), getY());
	}
	
	public void resize(float scale){
		image.setFilter(Image.FILTER_NEAREST);
		image = image.getScaledCopy(scale);
		resizeImages(mouthClose, scale);
		resizeImages(mouthOpen, scale);

	}
	
	public void resizeBeam(float scale){
		laserBegin.setFilter(Image.FILTER_NEAREST);
		laserBegin = laserBegin.getScaledCopy(scale);
		laserBeam.setFilter(Image.FILTER_NEAREST);
	}
	
	public void fireLaser(){
		new Projectile(getX(), getY(), laserBeam, 0, 20, Direction.LEFT );
	}
	

	public void setHealth(float health) {
		this.health = health;
	}

	@Override
	public void setY(float y){
		this.y = y;
	}
	
	public Image[] resizeImages(Image[] images, float scale){
		for (int i = 0; i<images.length; i++){
			images[i].setFilter(Image.FILTER_NEAREST);
			images[i] = images[i].getScaledCopy(scale);
		
		}
		return images;
	}

	@Override
	public void loseHealth() {
		health--;
		
	}

	@Override
	public boolean isDestroyed() {
		// explode
		// play death sound
		return false;
	}

	@Override
	public boolean isHurt() {
		// start flashing
		// play "is hurt sound"
		
		return false;
	}

	public float getWalkingSpeed(){
		return walkingspeed;
	}
	
	public float getNextRightX() {
		return x + getWalkingSpeed();
	}
	
	public float getNextLeftX() {
		return x - getWalkingSpeed(); 
	}
	
	public float getNextY() {
		return y + getWalkingSpeed();
	}

	public void moveRight() {
		setX(getX() + getWalkingSpeed());
	}
	
	public void moveLeft() {
		setX(getX() - getWalkingSpeed());
	}

	public void moveY() {
		setY(getY() + getWalkingSpeed());		
	}
}

