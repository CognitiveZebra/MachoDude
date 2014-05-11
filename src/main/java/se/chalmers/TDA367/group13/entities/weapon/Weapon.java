package se.chalmers.TDA367.group13.entities.weapon;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.entities.Entity;
import se.chalmers.TDA367.group13.entities.Projectile;
import se.chalmers.TDA367.group13.util.Direction;

public abstract class Weapon extends Entity {
	protected long time;
	protected float cooldown;
	private String name;
	private float damage;
	private float angle;
	private Image rightImage, leftImage, projectileImage;
	private Vector2f shoulder, nuzzle;
	private Line distanceToNuzzle;
	private double nuzzleAngle;
	protected Sound firingSound;

	public Weapon(float x, float y, Image rightImage, Image leftImage, Image projectileImage, String name, float damage){
		super(x, y, rightImage);
		this.rightImage = rightImage;
		this.leftImage = leftImage;
		this.projectileImage = projectileImage;
		this.name = name;
		this.damage = damage;
	}
	
	public Weapon(float x, float y, Vector2f shoulder, Vector2f nuzzle, Image rightImage, Image leftImage, Image projectileImage, String name, float damage){
		super(x, y, rightImage);
		this.rightImage = rightImage;
		this.leftImage = leftImage;
		this.projectileImage = projectileImage;
		this.name = name;
		this.damage = damage;
		this.shoulder = shoulder;
		this.nuzzle = nuzzle;
		distanceToNuzzle = new Line(nuzzle, shoulder);
		nuzzleAngle = Math.atan2(distanceToNuzzle.getX1() - distanceToNuzzle.getX2(), distanceToNuzzle.getY1() - distanceToNuzzle.getY2());
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}
	
	
	public void pointAt(float x, float y, Direction direction){
		double deltaY;
		double deltaX;
		double newAngle;
		deltaY = this.y - y;
		deltaX = x - this.x;
		newAngle = Math.atan2(deltaY,deltaX);
		angle += newAngle - angle;
		if (direction == Direction.RIGHT) {
			setImage(rightImage);
			getImage().setRotation(-(float)Math.toDegrees(angle));
		} else if  (direction == Direction.LEFT) {
			setImage(leftImage);
			getImage().setRotation(-(float)Math.toDegrees(angle) - 180);
		}


	}
	
	public boolean isReady() {
		if ((System.currentTimeMillis() - time) > cooldown) {
			return true;
		} else {
			return false;
		}
	}

	
	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public void render(Graphics g, Direction direction) {
		super.render(g);
	}
	
	public void render(Graphics g, Color c) {
		super.render(g,c);
	}

	
	@Override
	public void resize(float scale){
		rightImage.setFilter(Image.FILTER_NEAREST);
		rightImage = rightImage.getScaledCopy(scale);
		
		leftImage.setFilter(Image.FILTER_NEAREST);
		leftImage = leftImage.getScaledCopy(scale);
		
		projectileImage.setFilter(Image.FILTER_NEAREST);
		projectileImage = projectileImage.getScaledCopy(scale);
		
		setImage(rightImage);
		
	}
	
	public abstract Projectile fireWeapon(Direction direction);

	public Image getRightImage() {
		return rightImage;
	}
	
	public Image getLeftImage() {
		return leftImage;
	}
	
	public Image getProjectileImage() {
		return projectileImage;
	}
	
	public float getProjectileX(Direction direction) {
		if (direction == Direction.RIGHT) {
			return getCenterX() + (float) ((distanceToNuzzle.length() * 2) * Math.cos(getAngle() - nuzzleAngle));
		} else {
			return getCenterX() + (float) ((distanceToNuzzle.length() * 2) * Math.cos(getAngle() + nuzzleAngle));
		}
	}
	
	public float getProjectileY(Direction direction) {
		if (direction == Direction.RIGHT) {
			return getCenterY() - (float) ((distanceToNuzzle.length() * 2) * Math.sin(getAngle() - nuzzleAngle));
		} else {
			return getCenterY() - (float) ((distanceToNuzzle.length() * 2) * Math.sin(getAngle() + nuzzleAngle));
		}
	}




	
	
}