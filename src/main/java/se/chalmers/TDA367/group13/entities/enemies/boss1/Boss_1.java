package se.chalmers.TDA367.group13.entities.enemies.boss1;

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

public class Boss_1 extends MoveableEntity {
	private XMLPackedSheet bossSheet;	
	private Boss_1HealthBar healthBar;
	private Boolean showHealthBar;
	private Sound hurt;
	private Image laserBegin, laserBeam;
	private Image[] mouthOpen, mouthClose;
	private Animation openMouth, closeMouth;
	private float health, maxHealth;
	private int score, yOffset = 80;
	private AbstractBoss_1State up, down, shooting;
	private float scale;
	
	public Boss_1(float x, float y) throws SlickException {
		super(x, y, new Image("/res/Sprites/Bosses/1/boss_1_head.png"));
		bossSheet = new XMLPackedSheet("/res/Sprites/Bosses/1/boss_1_sheet.png","/res/Sprites/Bosses/1/boss_1_sheet.xml");
		this.health = 50;
		maxHealth = health;
		direction = Direction.LEFT;
		initAnimations();
		up = new Boss_1Moving(closeMouth);
		up.setVelocity(new Vector2f(0,-2));
		down = new Boss_1Moving(closeMouth);
		down.setVelocity(new Vector2f(0,2));
		shooting = new Boss_1Shooting(openMouth);
		state = shooting;
		healthBar = new Boss_1HealthBar();
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
			g.drawImage(laserBegin,x-laserBegin.getWidth(), y+yOffset);
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

		laserBegin = new Image("/res/Sprites/Bosses/1/laser_begin.png");
		laserBeam = new Image("/res/Sprites/Bosses/1/laser_beam.png");

		resize(5);

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
		resizeBeam();
		resizeImages(mouthClose, scale);
		resizeImages(mouthOpen, scale);
	}

	public void resizeBeam(){
		laserBegin.setFilter(Image.FILTER_NEAREST);
		laserBegin = laserBegin.getScaledCopy(Player.scale);
		laserBeam.setFilter(Image.FILTER_NEAREST);
		laserBeam = laserBeam.getScaledCopy(Player.scale);
	}

	public Projectile fireLaser(){
		return new Boss1Projectile(x-laserBeam.getWidth()-laserBegin.getWidth(), y+yOffset, (float)Math.PI, direction);
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
		if(((AbstractBoss_1State)state).isNextState()){
			if(state == shooting){
				setState(getMovingState(p));
			} else {
				setState(shooting);
			}
		}
	}
	
	public void setState(AbstractMoveableEntityState state){
		this.state = state;
		((AbstractBoss_1State)state).setStateStartedMillis();
		state.getAnimation(direction).restart();
	}
	public AbstractBoss_1State getUpState(){
		return (AbstractBoss_1State) up;
	}
	
	public AbstractBoss_1State getDownState(){
		return (AbstractBoss_1State) down;
	}
	
	
	public AbstractBoss_1State getShootingState(){
		return (AbstractBoss_1State) shooting;
	}
	
	public AbstractBoss_1State getState(){
		return (AbstractBoss_1State) state;
	}

	public AbstractBoss_1State getMovingState(Player p) {	
		if(p.getY() < this.getCenterY()){
			return up;
		} else {
			return down;
		}
		
	}

}
