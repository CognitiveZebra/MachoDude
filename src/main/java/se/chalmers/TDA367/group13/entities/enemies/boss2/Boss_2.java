package se.chalmers.TDA367.group13.entities.enemies.boss2;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.entities.AbstractMoveableEntityState;
import se.chalmers.TDA367.group13.entities.MoveableEntity;
import se.chalmers.TDA367.group13.entities.player.Player;
import se.chalmers.TDA367.group13.entities.projectile.Boss1Projectile;
import se.chalmers.TDA367.group13.entities.projectile.Projectile;
import se.chalmers.TDA367.group13.util.Direction;

public class Boss_2 extends MoveableEntity {
	private XMLPackedSheet bossSheet;	
	private Boss_2HealthBar healthBar;
	private Boolean showHealthBar;
	private Sound hurt;
	private Image laserBegin, laserBeam;
	private Image[] mouthOpen, mouthClose;
	private Animation openMouth, closeMouth;
	private float health, maxHealth;
	private int score, xOffset = 80;
	private AbstractBoss_2State left, right, shooting;
	public static float bossScale = 5, projectileScale = 1.25f;
	
	public Boss_2(float x, float y) throws SlickException {
		super(x, y, new Image("/res/Sprites/Bosses/2/boss_2_Sheet.png"));
		bossSheet = new XMLPackedSheet("/res/Sprites/Bosses/2/boss_2_Sheet.png","/res/Sprites/Bosses/2/boss_2_Sheet.xml");
		this.health = 50;
		maxHealth = health;
		direction = Direction.LEFT;
		initAnimations();
		left = new Boss_2Moving(closeMouth);
		left.setVelocity(new Vector2f(-0.2f,0));
		right = new Boss_2Moving(closeMouth);
		right.setVelocity(new Vector2f(0.2f,0));
		shooting = new Boss_2Shooting(openMouth);
		state = shooting;
		healthBar = new Boss_2HealthBar();
		showHealthBar = false;
		hurt = new Sound("res/Sound/Boss_1/hurt.wav");
		score = 100;
	}
	
	@Override
	public void render(Graphics g){
		g.drawAnimation(state.getAnimation(Direction.LEFT), getX(), getY());
		if(showHealthBar){
			healthBar.render(this, g);
		}
		if(state == getShootingState()){
			g.drawImage(laserBegin,x+xOffset, y+-laserBegin.getHeight());
		}
	}
	


	public void initAnimations() throws SlickException{
		int animationSpeed = 20;
		image = bossSheet.getSprite("still.png");

		mouthOpen = new Image[]{
				bossSheet.getSprite("open1.png"),
				bossSheet.getSprite("open2.png"),
				bossSheet.getSprite("open3.png"),
				bossSheet.getSprite("open4.png"),
				bossSheet.getSprite("open5.png")
		};

		mouthClose = new Image[]{
				bossSheet.getSprite("open5.png"),
				bossSheet.getSprite("open4.png"),
				bossSheet.getSprite("open3.png"),
				bossSheet.getSprite("open2.png"),
				bossSheet.getSprite("open1.png")
		};

		laserBegin = new Image("/res/Sprites/Bosses/2/laser_begin.png");
		laserBeam = new Image("/res/Sprites/Bosses/2/laser_beam.png");

		resize(bossScale);

		openMouth = new Animation(mouthOpen, animationSpeed);
		closeMouth = new Animation(mouthClose, animationSpeed);
		openMouth.setLooping(false);
		closeMouth.setLooping(false);

	}



	public float getHealth() {
		return health;
	}

	public float getMaxHealth(){
		return maxHealth;
	}


	public void setHealth(int health) {
		this.health = health;
	}

	public void resize(float scale){
		super.resize(scale);
		resizeBeam(projectileScale);
		resizeImages(mouthClose, scale);
		resizeImages(mouthOpen, scale);
	}

	public void resizeBeam(float scale){
		laserBegin.setFilter(Image.FILTER_NEAREST);
		laserBegin = laserBegin.getScaledCopy(scale);
		laserBeam.setFilter(Image.FILTER_NEAREST);
		laserBeam = laserBeam.getScaledCopy(scale);
	}

	public Projectile fireLaser(){
		return new Boss1Projectile(x + xOffset, y-laserBeam.getHeight()-laserBegin.getHeight(), (float)(Math.PI*1.5), direction);
	}


	public void setHealth(float health) {
		this.health = health;
	}

	public Image[] resizeImages(Image[] images, float scale){
		for (int i = 0; i<images.length; i++){
			images[i].setFilter(Image.FILTER_NEAREST);
			images[i] = images[i].getScaledCopy(scale);

		}
		return images;
	}


	public void loseHealth() {
		hurt.play();
		health--;

	}


	public boolean isDestroyed() {
		return health <= 0;
	}


	public boolean isHurt() {
		return health < maxHealth;
	}


	public void showHealthBar(){
		showHealthBar = true;
	}

	
	public void update(Player p){
		if(((AbstractBoss_2State)state).isNextState()){
			if(state == shooting){
				setState(getMovingState(p));
			} else {
				setState(shooting);
			}
		}
	}
	
	public void setState(AbstractMoveableEntityState state){
		this.state = state;
		((AbstractBoss_2State)state).setStateStartedMillis();
		state.getAnimation(direction).restart();
	}
	public AbstractBoss_2State getLeftState(){
		return (AbstractBoss_2State) left;
	}
	
	public AbstractBoss_2State getRightState(){
		return (AbstractBoss_2State) right;
	}
	
	
	public AbstractBoss_2State getShootingState(){
		return (AbstractBoss_2State) shooting;
	}
	
	public AbstractBoss_2State getState(){
		return (AbstractBoss_2State) state;
	}

	public AbstractBoss_2State getMovingState(Player p) {	
		if(p.getCenterY() < this.getCenterY()){
			return left;
		} else {
			return right;
		}
		
	}
	
	public int getScoreValue(){
		return score;
	}

}
