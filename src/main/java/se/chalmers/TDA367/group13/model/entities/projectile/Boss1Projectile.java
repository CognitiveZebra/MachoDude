package se.chalmers.TDA367.group13.model.entities.projectile;

import se.chalmers.TDA367.group13.model.entities.enemies.boss1.Boss_1;
import se.chalmers.TDA367.group13.model.entities.player.Player;
import se.chalmers.TDA367.group13.util.Direction;

public class Boss1Projectile extends Projectile {
	private static float speed = 0.66f;
	public Boss1Projectile(float x, float y, float angle, Direction direction) {
		super(x, y, ProjectileImageFactory.getImage("boss").getScaledCopy(Boss_1.projectileScale), angle, speed, direction);
		damage = 3;
	}

}
