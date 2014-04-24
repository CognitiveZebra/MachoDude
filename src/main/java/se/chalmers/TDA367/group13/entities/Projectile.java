package se.chalmers.TDA367.group13.entities;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;

import se.chalmers.TDA367.group13.factory.ParticleFactory;
import se.chalmers.TDA367.group13.util.Direction;

public class Projectile extends Entity {
	private float angle, speed;
	private Vector2f vector;
	private ParticleSystem ps;
	private ConfigurableEmitter smoke; 
	
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
		
		ps = new ParticleSystem("res/Particles/particle.png", 1500);
		smoke = ParticleFactory.createEmitter("smoke");
		ps.addEmitter(smoke);
	}
	
	public void update(){
		setX(getX() + (speed*vector.x));
		setY(getY() - (speed*vector.y));
	}
	
	public void update(int delta){
		setX(getX() + (speed*vector.x));
		setY(getY() - (speed*vector.y));
		smoke.setPosition(getX(),getY(), false);
		ps.update(delta);
	}
	
	@Override
	public void render(Graphics g){
		super.render(g);
		ps.render();
	}
}
