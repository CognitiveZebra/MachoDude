package se.chalmers.TDA367.group13.entities;


import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.util.Direction;

public class Projectile extends Entity {
	private float angle, speed;
	private Vector2f vector;
	
	public Projectile(float x, float y, Image image, float angle, float speed, Direction direction){
		super (x, y, image);
		if (direction == Direction.RIGHT) {
			getImage().setRotation(-(float)Math.toDegrees(angle));
		} else if  (direction == Direction.LEFT) {
			getImage().setRotation(-(float)Math.toDegrees(angle) - 180);
		}
		this.angle = angle;
		this.speed = speed;
		vector = new Vector2f(Math.toDegrees(angle));
		vector.normalise();
	}
	
	public void update(){
		setX(getX() + (speed*vector.x));
		setY(getY() - (speed*vector.y));
	}
}
