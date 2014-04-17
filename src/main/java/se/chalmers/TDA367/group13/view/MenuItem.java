package se.chalmers.TDA367.group13.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import se.chalmers.TDA367.group13.entities.Entity;
import se.chalmers.TDA367.group13.util.Util;


public class MenuItem extends Entity{
	private int ID;
	private String name; 
	private Color selectedColor = new Color(0,0,0,0.4f);
	public Rectangle selectedRec;
	

	public MenuItem(float x, float y, Image image, String name, int ID) {
		super(x, y, image);
		this.name = name;
		this.ID = ID;
		selectedRec = new Rectangle(getX() - 3, getY() - 3, getWidth() + 6, getHeight() + 6 );
	}
	
	public MenuItem(float x, float y, Image image, String name) {
		this(x,y,image,name, -1);
	}

	public void render(Graphics g, boolean isSelected){
		
		//blue frame behind image when selected
		if(isSelected){
			g.setColor(new Color(0.035f,0.804f,0.855f, 0.5f));
			g.fill(selectedRec);
		}
		
		//menu item image
		super.render(g);
		
		//darker shade over image when selected
		if(isSelected){
			g.setColor(selectedColor);
			g.fill(this);
		}
		
		//white text over everything else
		g.setColor(Color.white);
		g.setFont(Util.getFont32());
		g.drawString(name, getX() + getWidth() / 2 - g.getFont().getWidth(name)/2, getY() + getHeight() / 2 - g.getFont().getHeight(name)/2);
	}
	
	public int getID(){
		return ID;
	}
	
	public String getName(){
		return name;
	}
	
}


