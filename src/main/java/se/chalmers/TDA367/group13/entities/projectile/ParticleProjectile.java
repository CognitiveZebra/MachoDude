package se.chalmers.TDA367.group13.entities.projectile;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;

import se.chalmers.TDA367.group13.particles.ParticleFactory;
import se.chalmers.TDA367.group13.util.Direction;

public abstract class ParticleProjectile extends Projectile {
	
	protected ParticleSystem ps;
	protected ConfigurableEmitter particleEmitter; 
	
	public ParticleProjectile(float x, float y, Image image, float angle,
			float speed, Direction direction) {
		super(x, y, image, angle, speed, direction);
		ps = new ParticleSystem("res/Particles/particle.png", 1500);		
	}
	
	@Override 
	public void update(int delta){
		super.update();
		particleEmitter.setPosition(getCenterX(),getCenterY(), false);
		ps.update(delta);
	}
	
	@Override 
	public void render(Graphics g){
		ps.render();
		super.render(g);
	}
}
