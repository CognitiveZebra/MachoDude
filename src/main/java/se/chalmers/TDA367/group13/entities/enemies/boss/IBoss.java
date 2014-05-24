package se.chalmers.TDA367.group13.entities.enemies.boss;

import se.chalmers.TDA367.group13.entities.IMoveable;
import se.chalmers.TDA367.group13.entities.player.Player;
import se.chalmers.TDA367.group13.entities.projectile.Projectile;

public interface IBoss extends IMoveable {
	
	public void update(Player p);
}
