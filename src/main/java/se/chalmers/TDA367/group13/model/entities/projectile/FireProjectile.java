package se.chalmers.TDA367.group13.model.entities.projectile;

import org.newdawn.slick.Graphics;

import se.chalmers.TDA367.group13.particles.ParticleFactory;
import se.chalmers.TDA367.group13.util.Direction;

public class FireProjectile extends ParticleProjectile {
	private static float speed = 0.5f;
	
	public FireProjectile(float x, float y, float angle, Direction direction) {
		super(x, y, ProjectileImageFactory.getImage("fire"), angle, speed, direction);
		damage = 1;
		particleEmitter = ParticleFactory.createEmitter("fire");
		ps.addEmitter(particleEmitter);
	}
	
	@Override 
	public void render(Graphics g){
		ps.render();
	}
}
