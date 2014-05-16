package se.chalmers.TDA367.group13.entities.projectile;

import se.chalmers.TDA367.group13.entities.enemies.boss1.Boss_1;
import se.chalmers.TDA367.group13.entities.player.Player;
import se.chalmers.TDA367.group13.util.Direction;

public class Boss1Projectile extends Projectile {

	public Boss1Projectile(float x, float y, float angle, Direction direction) {
		super(x, y, ProjectileImageFactory.getImage("boss").getScaledCopy(Boss_1.projectileScale), angle, 10, direction);
		damage = 3;
	}

}
