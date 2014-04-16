package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Image;

public class DestructableBlock extends Block implements IDestructable {
	private float health;
	private float maxHealth;

	public DestructableBlock(float x, float y, Image image, float health) {
		super(x, y, image);
		this.maxHealth = health;
		this.health = health;
	}
	
	public void loseHealth(){	
		health--;
	}
	
	public boolean isDestroyed() {
		return health <= 0;
	}
	
	
	public boolean isHurt(){
		return health < maxHealth;
	}

}
