package se.chalmers.TDA367.group13.entities.projectile;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;

import se.chalmers.TDA367.group13.particles.ParticleFactory;
import se.chalmers.TDA367.group13.util.Direction;

public class PlayerProjectile extends Projectile {

	private ParticleSystem ps;
	private ConfigurableEmitter smoke; 
	
	public PlayerProjectile(float x, float y, Image image, float angle,
			float speed, Direction direction) {
		super(x, y, image, angle, speed, direction);
		damage = 1;
		ps = new ParticleSystem("res/Particles/particle.png", 1500);
		smoke = ParticleFactory.createEmitter("smoke");
		ps.addEmitter(smoke);
	}
	
	@Override 
	public void update(int delta){
		super.update();
		smoke.setPosition(getCenterX(),getCenterY(), false);
		ps.update(delta);
	}
	
	@Override 
	public void render(Graphics g){
		super.render(g);
		ps.render();
	}
}
