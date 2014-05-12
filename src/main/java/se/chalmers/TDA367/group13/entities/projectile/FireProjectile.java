package se.chalmers.TDA367.group13.entities.projectile;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import se.chalmers.TDA367.group13.particles.ParticleFactory;
import se.chalmers.TDA367.group13.util.Direction;

public class FireProjectile extends ParticleProjectile {

	public FireProjectile(float x, float y, Image image, float angle,
			float speed, Direction direction) {
		super(x, y, image, angle, speed, direction);
		damage = 1;
		particleEmitter = ParticleFactory.createEmitter("fire");
		ps.addEmitter(particleEmitter);
	}
	
	@Override 
	public void render(Graphics g){
		ps.render();
	}
}
