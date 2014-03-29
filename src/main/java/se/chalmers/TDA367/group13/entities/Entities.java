package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entities {
	private float x, y, height, width;
	private Image image;
	private Rectangle border;
	
	public Entities (float x, float y, Image image){
		this.x = x;
		this.y = y;
		this.image = image;
		this.height = image.getHeight();
		this.width = image.getWidth();
		border = new Rectangle (x, y, height, width);
		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
		update();
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
		update();
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
		update();
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
		update();
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public void update(){
		border.setBounds(x, y, height, width);
	}

}
