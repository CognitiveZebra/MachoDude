package se.chalmers.TDA367.group13.entities.projectile;

import org.newdawn.slick.Image;

import se.chalmers.TDA367.group13.entities.player.Player;
import se.chalmers.TDA367.group13.particles.ParticleFactory;
import se.chalmers.TDA367.group13.util.Direction;

public class PlayerProjectile extends ParticleProjectile {
	private static float speed = 0.66f;

	
	public PlayerProjectile(float x, float y, float angle, Direction direction) {
		super(x, y, (direction == Direction.LEFT) ? ProjectileImageFactory.getImage("player").getScaledCopy(Player.scale) : ProjectileImageFactory.getImage("player").getFlippedCopy(true,false).getScaledCopy(Player.scale), angle, speed, direction);
		this.getImage().setFilter(Image.FILTER_NEAREST);
		damage = 1;
		particleEmitter = ParticleFactory.createEmitter("smoke");
		ps.addEmitter(particleEmitter);
	}
	
}
