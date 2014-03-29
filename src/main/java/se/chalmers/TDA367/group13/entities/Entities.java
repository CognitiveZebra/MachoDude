package se.chalmers.TDA367.group13.entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entities extends Rectangle {
	private Image image;
	
	public Entities (float x, float y, Image image){
		super(x, y, image.getWidth(), image.getHeight());
		this.image = image;
	}

	public Image getImage()
	{
		return image;
	}
	
	public void setImage(Image image)
	{
		this.image = image;
		setBounds(getX(), getY(), image.getWidth(), image.getHeight());
	}
	
	public void draw(Graphics g){ 
		g.drawImage(image, getX(), getY()); 
		}

}
