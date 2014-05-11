package se.chalmers.TDA367.group13.entities.enemies.enemy1;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Point;

import se.chalmers.TDA367.group13.entities.enemies.Enemy;
import se.chalmers.TDA367.group13.entities.enemies.EnemyStill;
import se.chalmers.TDA367.group13.entities.enemies.EnemyWalking;
import se.chalmers.TDA367.group13.entities.weapon.Weapon;
import se.chalmers.TDA367.group13.entities.weapon.enemy1weapon.Enemy_1_Weapon;
import se.chalmers.TDA367.group13.util.Direction;
import se.chalmers.TDA367.group13.view.Level;


public class Enemy_1 extends Enemy{

	private Image[] right, left, standLeft, standRight;
	private final static int scale = 2;

	public Enemy_1(float x, float y, Level level) throws SlickException {
		super(x, y, "res/Sprites/Enemies/Enemy_1/Enemy_1.png", "res/Sprites/Enemies/Enemy_1/sheet.xml", scale);
		rightShoulder = new Point(6, 13);
		leftShoulder = new Point(11, 13);
		weapon = new Enemy_1_Weapon(x, y, level);
		maxHealth = 5;
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
				{enemySheet.getSprite("walk1"),enemySheet.getSprite("walk2")};
			
		left = new Image[]
				{enemySheet.getSprite("walk1").getFlippedCopy(true, false),
				enemySheet.getSprite("walk2").getFlippedCopy(true, false)};
					
		standRight = new Image[]{enemySheet.getSprite("walk1")};
		standLeft = new Image[]{enemySheet.getSprite("walk1").getFlippedCopy(true, false)};
		
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
