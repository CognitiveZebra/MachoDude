package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

import se.chalmers.TDA367.group13.entities.Player.Direction;


public class Enemy_1 extends Enemy{

	private Image[] right, left, standLeft, standRight;
	private Animation stillLeft, stillRight, walkLeft, walkRight;
	private Point rightShoulder, leftShoulder;
	private float walkingspeed = 1;

	public Enemy_1(float x, float y) throws SlickException {
		super(x, y, "res/Sprites/Enemies/Enemy_1/Enemy_1.png", "res/Sprites/Enemies/Enemy_1/sheet.xml");
		weapon = new Enemy_1_Weapon(x, y);
		rightShoulder = new Point(6, 13);
		leftShoulder = new Point(11, 13);
		initAnimations();
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
		
		resize(2);	
		
		stillLeft = new Animation(standLeft,animationSpeed);
		stillRight = new Animation(standRight,animationSpeed);
		walkLeft = new Animation(left,animationSpeed);
		walkRight = new Animation(right,animationSpeed);
	}
	
	public void render(Graphics g) {
		Animation animation;
		switch (state) {
		case STILL:
			animation = (direction == Direction.LEFT) ? stillLeft: stillRight;
			break;
		case WALKING:
			animation = (direction == Direction.LEFT) ? walkLeft : walkRight;
			break;
		default:
			animation = stillLeft;
			break;
		}
		g.drawAnimation(animation, getX(), getY());
		g.drawRect(weapon.getX(), weapon.getY(), weapon.getWidth(), weapon.getHeight());
		
		if (direction == Direction.LEFT){
			weapon.setCenterX(x + leftShoulder.getX());
			weapon.setCenterY(y + leftShoulder.getY());
		//	weapon.setImage(weapon.getLeftImage());
		}
		else{
		//	weapon.setImage(weapon.getRightImage());
			weapon.setCenterX(x + rightShoulder.getX());
			weapon.setCenterY(y + rightShoulder.getY());
		}
		weapon.render(g);
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

	@Override
	public float getWalkingSpeed() {
		return walkingspeed ;
	}
}
