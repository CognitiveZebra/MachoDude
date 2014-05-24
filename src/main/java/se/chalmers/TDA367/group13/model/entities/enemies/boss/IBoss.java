package se.chalmers.TDA367.group13.model.entities.enemies.boss;

import se.chalmers.TDA367.group13.model.entities.IMoveable;
import se.chalmers.TDA367.group13.model.entities.player.Player;



public interface IBoss extends IMoveable {
	
	public void update(Player p);
}
