package se.chalmers.TDA367.group13.model.entities.enemies.enemy2;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;

import se.chalmers.TDA367.group13.model.entities.enemies.Enemy;
import se.chalmers.TDA367.group13.model.entities.enemies.EnemyStill;
import se.chalmers.TDA367.group13.model.entities.enemies.EnemyWalking;
import se.chalmers.TDA367.group13.model.entities.weapon.enemy2weapon.Enemy_2_Weapon;

public class Enemy_2 extends Enemy {
	
	private Image[] right, left, standLeft, standRight;
	private static final int scale = 2;

	public Enemy_2(float x, float y)
			throws SlickException {
		super(x, y+64, "res/Sprites/Enemies/Enemy_2/Enemy_2Sheet.png", "res/Sprites/Enemies/Enemy_2/Enemy_2Sheet.xml", scale);
		rightShoulder = new Point(10, 20);
		leftShoulder = new Point(5, 20);
		weapon = new Enemy_2_Weapon(x, y);
		aggroRange = new Circle(getCenterX(), getCenterY(), 300);
		maxHealth = 8;
		health = maxHealth;
		scoreValue = 5;
		hurtSound = new Sound("res/Sound/Enemy_1/Hurt.wav");
		deathSound = new Sound("res/Sound/Enemy_1/Dies.wav");
		initAnimations();
		still = new EnemyStill(stillLeft, stillRight);
		walking = new EnemyWalking(walkLeft,walkRight);
		state = still;
	}
	
	public void initAnimations() {
		
		right = new Image[]
				{enemySheet.getSprite("Enemy_2Still.png").getFlippedCopy(true, false),
				enemySheet.getSprite("Enemy_2Walk.png").getFlippedCopy(true, false)};
			
		left = new Image[]
				{enemySheet.getSprite("Enemy_2Still.png"),
				enemySheet.getSprite("Enemy_2Walk.png")};
					
		standRight = new Image[]{enemySheet.getSprite("Enemy_2Still.png").getFlippedCopy(true, false)};
		standLeft = new Image[]{enemySheet.getSprite("Enemy_2Still.png")};
		
		int animationSpeed = 200;
		
		resize(scale);	
		
		stillLeft = new Animation(standLeft,animationSpeed);
		stillRight = new Animation(standRight,animationSpeed);
		walkLeft = new Animation(left,animationSpeed);
		walkRight = new Animation(right,animationSpeed);
	}
		
	@Override
	public void resize(float scale){
		standLeft =  resizeImages(standLeft, scale);
		standRight = resizeImages(standRight, scale);
		right = resizeImages(right, scale); 
		left = resizeImages(left, scale);
		leftShoulder.setLocation(leftShoulder.getX() * scale, leftShoulder.getY() *scale);
		rightShoulder.setLocation(rightShoulder.getX() * scale, rightShoulder.getY() *scale);
		weapon.resize(scale);
		setImage(standRight[0]);
	}
	
	public Image[] resizeImages(Image[] images, float scale){
		for (int i = 0; i<images.length; i++){
			images[i].setFilter(Image.FILTER_NEAREST);
			images[i] = images[i].getScaledCopy(scale);
		
		}
		return images;
	}


}
