package se.chalmers.TDA367.group13.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.geom.Rectangle;


public class TextItem extends Rectangle {
	private String stat;
	private Color color;
	private float padding = 10;

	public TextItem(String stat, float x, float y, float width, float height) {
		super(x, y, width, height);
		this.stat = stat; 
		color = new Color(0.25f,0.25f,0.25f,0.8f);
	}
	
	public void render(Graphics g){
		g.setColor(color);
		g.fill(this);
		g.setColor(Color.white);
		g.drawString(stat, x + padding, y + padding);
	}
}
