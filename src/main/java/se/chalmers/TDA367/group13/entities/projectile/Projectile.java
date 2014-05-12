package se.chalmers.TDA367.group13.entities.projectile;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;

import se.chalmers.TDA367.group13.entities.Entity;
import se.chalmers.TDA367.group13.particles.ParticleFactory;
import se.chalmers.TDA367.group13.util.Direction;

public abstract class Projectile extends Entity {
	private float angle, speed;
	protected float damage;
	private Vector2f vector;

	
	public Projectile(float x, float y, Image image, float angle, float speed, Direction direction){
		super (x, y, image);
		if (direction == Direction.RIGHT) {
			getImage().setRotation(-(float)Math.toDegrees(angle));
		} else if  (direction == Direction.LEFT) {
			getImage().setRotation(-(float)Math.toDegrees(angle) - 180);
		}
		this.angle = angle;
		this.speed = speed;
		vector = new Vector2f(Math.toDegrees(angle));
		vector.normalise();
	}
	
	public void update(){
		setX(getX() + (speed*vector.x));
		setY(getY() - (speed*vector.y));
	}
	
	public void update(int delta){
		setX(getX() + (speed*vector.x));
		setY(getY() - (speed*vector.y));
	}
	
	
	public float getDamage(){
		return damage;
	}
	
}
