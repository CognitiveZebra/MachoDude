package se.chalmers.TDA367.group13.model.entities;

public interface IDestructable {

	public void loseHealth();
	
	public boolean isDestroyed();
	
	public boolean isHurt();
}
