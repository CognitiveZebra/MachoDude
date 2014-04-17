package se.chalmers.TDA367.group13.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import se.chalmers.TDA367.group13.util.Controls;
import se.chalmers.TDA367.group13.util.Util;


public class SettingsItem extends MenuItem{
	private int ID, control;
	private String title, controlText;
	private Color selectedColor = new Color(0,0,0,0.4f);
	public Rectangle selectedRec;
	

	public SettingsItem(float x, float y, Image image, String title, int ID, int control) {
		super(x, y, image, title, control);
		this.ID = ID;
		this.title = title;
		this.control = control;
		switch (control) {
			case 0: 
				controlText = "LMB";
				break;
			case 1: 
				controlText = "RMB";
				break;
			case 2: 
				controlText = "MMB";
				break;
			default:
				controlText = Input.getKeyName(control); 
		}
		selectedRec = new Rectangle(getX() - 3, getY() - 3, getWidth() + 6, getHeight() + 6 );
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
		g.drawString(title, getX() - 200, getY() + getHeight() / 2 - g.getFont().getHeight(title)/2);
		g.drawString(controlText, getX() + getWidth() / 2 - g.getFont().getWidth(controlText)/2, getY() + getHeight() / 2 - g.getFont().getHeight(controlText)/2);
	}
	
	public int getID(){
		return ID;
	}
	
	public String getName(){
		return title;
	}

	public void setControl(int control) {
		this.control = control;
		switch (ID) {
			case 0: 
				Controls.setRightKey(control);
				break;
			case 1: Controls.setLeftKey(control);break;
			case 2: Controls.setJumpKey(control);break;
			case 3: Controls.setShootKey(control);break;
		default:
			break;
		}
	}
	
	public void setText(String controlText) {
		this.controlText = controlText;
	}
	
	public String getText() {
			return controlText;
	}

	public String getControlText() {
		switch (control) {
			case 0: return "LMB";
			case 1: return "RMB";
			case 2: return "MMB";
			default: return Input.getKeyName(control); 
		}
	}

	
}


