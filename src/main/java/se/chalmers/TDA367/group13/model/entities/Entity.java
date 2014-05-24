package se.chalmers.TDA367.group13.model.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entity extends Rectangle {
	protected Image image;

	public Entity(float x, float y, Image image) {
		super(x, y, image.getWidth(), image.getHeight());
		this.image = image;
	}
	

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
		setBounds(getX(), getY(), image.getWidth(), image.getHeight());
	}

	public void render(Graphics g) {
		g.drawImage(image, getX(), getY());
	}
	
	public void render(Graphics g, Color c) {
		g.drawImage(image, getX(), getY(), c);
	}
	
	
	public void resize(float scale){
		image.setFilter(Image.FILTER_NEAREST);
		image = image.getScaledCopy(scale);
		setImage(image);
		
	}

}
