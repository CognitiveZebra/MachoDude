package se.chalmers.TDA367.group13.entities.enemies.boss;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import se.chalmers.TDA367.group13.entities.MoveableEntity;
import se.chalmers.TDA367.group13.entities.projectile.Projectile;

public abstract class AbstractBoss extends MoveableEntity implements IBoss {

	private float health, maxHealth;
	protected Boolean showHealthBar;
	private Sound hurt;
	private int score;
	protected BossHealthBar healthBar;

	public AbstractBoss(float x, float y, Image image) throws SlickException {
		super(x, y, image);
		this.health = 50;
		maxHealth = health;
		showHealthBar = false;
		hurt = new Sound("res/Sound/Boss_1/hurt.wav");
		score = 100;
	}

	public void render(Graphics g){
		if(showHealthBar){
			healthBar.render(this, g);
		}
	}
	
	public int getScoreValue(){
		return score;
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
	
	public void setHealth(float health) {
		this.health = health;
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
	
	public Image[] resizeImages(Image[] images, float scale){
		for (int i = 0; i<images.length; i++){
			images[i].setFilter(Image.FILTER_NEAREST);
			images[i] = images[i].getScaledCopy(scale);

		}
		return images;
	}

	public abstract AbstractBossState getShootingState();

	public abstract AbstractBossState getState();

	public abstract Projectile fireLaser();

	public abstract void move(int delta);


}
