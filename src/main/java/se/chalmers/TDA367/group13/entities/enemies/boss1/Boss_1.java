package se.chalmers.TDA367.group13.entities.enemies.boss1;



import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;

import se.chalmers.TDA367.group13.entities.Entity;
import se.chalmers.TDA367.group13.entities.IDestructable;
import se.chalmers.TDA367.group13.entities.IMoveable;
import se.chalmers.TDA367.group13.entities.projectile.Boss1Projectile;
import se.chalmers.TDA367.group13.entities.projectile.Projectile;
import se.chalmers.TDA367.group13.level.Level;
import se.chalmers.TDA367.group13.util.Direction;

public class Boss_1 extends Entity implements IMoveable, IDestructable {
	private XMLPackedSheet bossSheet;
	private Direction direction;
	private State state;	
	private long time;
	private Level level;
	private Image laserBegin, laserBeam, rightLaserBegin,stillImg, openImg, rightStillImg, rightOpenImg;
	private Image[] mouthOpen, mouthClose, rightMouthOpen, rightMouthClose;
	private Animation openMouth, closeMouth, rightOpenMouth, rightCloseMouth,  still, open, rightOpen, rightStill;
	private float health, walkingspeed, cooldown, maxHealth;


	
	
	public Boss_1(float x, float y) throws SlickException{
		super(x, y, new Image("/res/Sprites/Bosses/1/boss_1_head.png"));
		bossSheet = new XMLPackedSheet("/res/Sprites/Bosses/1/boss_1_sheet.png","/res/Sprites/Bosses/1/boss_1_sheet.xml");
		this.health = 20;
		maxHealth = 20;
		this.walkingspeed = 3;
		time = System.currentTimeMillis();
		direction = Direction.LEFT;
		state = State.STILL;
		cooldown = 5000;
		this.level = level;
		initAnimations();
	}

	public enum State {
		STILL, OPENMOUTH, CLOSEMOUTH, OPEN;
	}

	public void initAnimations() throws SlickException{
		int animationSpeed = 200;
		image = bossSheet.getSprite("still.png");

		stillImg = bossSheet.getSprite("still.png");
		openImg =  bossSheet.getSprite("open5.png");
		rightOpenImg =  openImg.getFlippedCopy(true, false);
		rightStillImg = stillImg.getFlippedCopy(true, false);



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

		laserBegin = new Image("/res/Sprites/Bosses/1/laser_begin.png");
		laserBeam = new Image("/res/Sprites/Bosses/1/laser_beam.png");
		rightLaserBegin = laserBegin.getFlippedCopy(true, false);

		resize(5);

		openMouth = new Animation(mouthOpen, animationSpeed);
		closeMouth = new Animation(mouthClose, animationSpeed);
		openMouth.setLooping(false);
		closeMouth.setLooping(false);

		still = new Animation(new Image[] {stillImg}, animationSpeed);
		open = new Animation(new Image[] {openImg}, animationSpeed);
		rightOpen = new Animation(new Image[] {rightOpenImg}, animationSpeed);
		rightStill = new Animation(new Image[] {rightStillImg}, animationSpeed);

		rightOpenMouth = new Animation(rightMouthOpen, animationSpeed);
		rightCloseMouth = new Animation(mouthClose, animationSpeed);

		rightOpenMouth.setLooping(false);
		rightCloseMouth.setLooping(false);




	}



	public float getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}

	public void render(Graphics g) {

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
		stillImg = stillImg.getScaledCopy(scale);
		openImg =  openImg.getScaledCopy(scale);
		rightOpenImg =  rightOpenImg.getScaledCopy(scale);
		rightStillImg = rightStillImg.getScaledCopy(scale);
		laserBeam = laserBeam.getScaledCopy(scale);
		resizeImages(mouthClose, scale);
		resizeImages(mouthOpen, scale);

	}

	public void resizeBeam(float scale){
		laserBegin.setFilter(Image.FILTER_NEAREST);
		laserBegin = laserBegin.getScaledCopy(scale);
		laserBeam.setFilter(Image.FILTER_NEAREST);
	}

	public void fireLaser(){
		if ((System.currentTimeMillis() - time) > cooldown) {
			
			
			if (!openMouth.isStopped()){
				System.out.println("opening mouth");
				state = state.OPENMOUTH;
			}
			
			
			if (openMouth.isStopped()){
				System.out.println("Mouth has been opened");
				state = state.OPEN;
				while(true){
					
					level.getProjectiles().add(new Boss1Projectile(x-64, y-64, laserBeam, 0, 5, direction));
					if (System.currentTimeMillis() - time > 3000)
						break;
				}
				System.out.println("Lasers ha been fired");
			}
			time = System.currentTimeMillis();
			System.out.println("closing mouth");
			if (!closeMouth.isStopped()){
				System.out.println("closing moduth");
				state=state.CLOSEMOUTH;
			}

		}
		if (closeMouth.isStopped() && openMouth.isStopped()){
			System.out.println("resetting animations");
			closeMouth.restart();
			openMouth.restart();
			state=state.STILL;
		}


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
		return health <= 0;
	}

	@Override
	public boolean isHurt() {
		// start flashing
		// play "is hurt sound"

		return health < maxHealth;
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
		if (state == state.STILL)
			setY(getY() + getWalkingSpeed());		
	}
	public void movedownY() {
		if (state == state.STILL)
			setY(getY() - getWalkingSpeed());		
	}
}

