package se.chalmers.TDA367.group13.entities;

import java.util.LinkedList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Weapon extends Entity {
	private String name;
	private float damage;
	private float angle;
	private LinkedList<Projectile> projectiles;
	
	public Weapon(float x, float y, Image image, String name, float damage){
		super(x, y, image);
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
	
	public void pointAt(float x, float y){

		double deltaY;
		double deltaX;
		double newAngle;
		deltaY = this.y - y;
		deltaX = x - this.x;
		
		newAngle = Math.atan2(deltaY,deltaX);
		angle += newAngle - angle;
		getImage().setRotation(-(float)Math.toDegrees(angle));

	}

	
	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		for (Projectile projectile : projectiles)
			g.drawImage(projectile.getImage(), projectile.getX(), projectile.getY());
	}

	public abstract void fireWeapon();
	
	
}