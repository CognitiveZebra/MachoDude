package se.chalmers.TDA367.group13.entities.weapon;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.TDA367.group13.entities.Projectile;
import se.chalmers.TDA367.group13.util.Direction;

public class TestWeapon extends Weapon {

	
	
	public TestWeapon(float x, float y) throws SlickException {
		super(x, y, new Image("res/Sprites/testArm.png").getFlippedCopy(true, false),new Image("res/Sprites/testArm.png"), new Image("res/Sprites/testArm.png"), "TestWeapon", 1);
		time = System.currentTimeMillis();
		cooldown = 300;
	}
	
	public void fireWeapon(Direction direction){
		if (direction == Direction.RIGHT) {
			if ((System.currentTimeMillis()-time) > cooldown){
				getProjectiles().add(new Projectile(x, y, getProjectileImage().copy().getFlippedCopy(true, false), getAngle(), 10, direction));
				time = System.currentTimeMillis();
			}
		} else if (direction == Direction.LEFT) {
			if ((System.currentTimeMillis()-time) > cooldown){
				getProjectiles().add(new Projectile(x, y, getProjectileImage().copy(), getAngle(), 10, direction));
				time = System.currentTimeMillis();
			}
		}
		
	}
	
	@Override
	public void render(Graphics g, Direction direction){
		if ((System.currentTimeMillis()-time) > cooldown) {
			super.render(g, direction);
		}
		for (Projectile projectile : projectiles){
			g.drawImage(projectile.getImage(), projectile.getX(), projectile.getY());
		}
	}
	
	@Override
	public void render(Graphics g, Direction direction, Color c){
		if ((System.currentTimeMillis()-time) > cooldown) {
			super.render(g, direction, c);
		}
		for (Projectile projectile : projectiles){
			g.drawImage(projectile.getImage(), projectile.getX(), projectile.getY());
		}
	}

}
