package se.chalmers.TDA367.group13;

import java.util.LinkedList;

import org.newdawn.slick.Graphics;


public class SettingsView {
	private LinkedList<SettingsItem> settingsItems;
	private MenuItem selected;
	private LinkedList<MenuItem> menuItems;
	
	public SettingsView(LinkedList<SettingsItem> settingsItems, LinkedList<MenuItem> menuItems){
			this.settingsItems = settingsItems;
			this.menuItems = menuItems;
			selected = settingsItems.getFirst();

	}
	
	public void render(Graphics g){
		for(SettingsItem item : settingsItems){
			item.render(g, item.equals(selected));
		}
		for(MenuItem item : menuItems){
			item.render(g, item.equals(selected));
		}
	}
	
	public LinkedList<SettingsItem> getSettingsItems(){
		return settingsItems;
	}
	
	public LinkedList<MenuItem> getMenuItems(){
		return menuItems;
	}
	
	public void setSelected(MenuItem item){
		selected = item;
	}
	
	public MenuItem getSelected(){
		return selected;
	}
	
}
