package se.chalmers.TDA367.group13.view;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.geom.Rectangle;

import se.chalmers.TDA367.group13.util.Util;


public class TextItem extends Rectangle {
	private String text;
	private Color color;
	private static float padding = 10;

	public TextItem(String text, float x, float y, float width, float height) {
		super(x, y, width, height);
		this.text = text; 
		color = new Color(0.25f,0.25f,0.25f,0.8f);
	}
	
	public TextItem(String text, float x, float y) {
		super(x, y, Util.getFont32().getWidth(text) + padding * 2, Util.getFont32().getHeight(text) + padding * 2);
		this.text = text; 
		color = new Color(0.25f,0.25f,0.25f,0.8f);
	}
	
	public void render(Graphics g){
		g.setColor(color);
		g.fill(this);
		g.setColor(Color.white);
		g.drawString(text, x + padding, y + padding);
	}
	

	public void updateText(String text) {
		this.text = text;
		setWidth(Util.getFont32().getWidth(text) + padding * 2);
	}
}
