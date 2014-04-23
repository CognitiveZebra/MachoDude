package se.chalmers.TDA367.group13.entities;

import java.util.LinkedList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import se.chalmers.TDA367.group13.util.Direction;


public abstract class Weapon extends Entity {
	protected long time;
	protected float cooldown;
	private String name;
	private float damage;
	private float angle;
	private Image rightImage, leftImage, projectileImage;
	protected LinkedList<Projectile> projectiles;
	
	public Weapon(float x, float y, Image rightImage, Image leftImage, Image projectileImage, String name, float damage){
		super(x, y, rightImage);
		this.rightImage = rightImage;
		this.leftImage = leftImage;
		this.projectileImage = projectileImage;
		this.name = name;
		this.damage = damage;
		projectiles = new LinkedList<Projectile>();
		
		
	}

	public LinkedList<Projectile> getProjectiles() {
		return projectiles;
	}

	public void setProjectiles(LinkedList<Projectile> projectiles) {
		this.projectiles = projectiles;
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

	
	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public void render(Graphics g, Direction direction) {
		super.render(g);
		
		for (Projectile projectile : projectiles)
			g.drawImage(projectile.getImage(), projectile.getX(), projectile.getY());
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
	
	public void updateProjectiles(){
		for (Projectile projectile : projectiles)
			projectile.update();
	}


	public abstract void fireWeapon(Direction direction);

	public Image getRightImage() {
		return rightImage;
	}
	
	public Image getLeftImage() {
		return leftImage;
	}
	
	public Image getProjectileImage() {
		return projectileImage;
	}




	
	
}