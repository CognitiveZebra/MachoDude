package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Image;

public abstract class Weapon extends Entity {
	private String name;
	private float damage;
	
	public Weapon(float x, float y, Image image, String name, float damage){
		super(x, y, image);
		this.name = name;
		this.damage = damage;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}
}
