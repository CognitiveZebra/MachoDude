package se.chalmers.TDA367.group13;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ListIterator;

import org.newdawn.slick.Graphics;


public class Menu {
	private LinkedList<MenuItem> menuItems;
	private MenuItem selected;
	
	public Menu(LinkedList<MenuItem> menuItems){
			this.menuItems = menuItems;
			selected = menuItems.getFirst();

	}
	
	public void render(Graphics g){
		for(MenuItem item : menuItems){
			item.render(g, item.equals(selected));
		}
	}
	
	public LinkedList<MenuItem> getItems(){
		return menuItems;
	}
	
	public void setSelected(MenuItem item){
		selected = item;
	}
	
	public MenuItem getSelected(){
		return selected;
	}
	
	public void up(){
		int currentPos = menuItems.indexOf(selected);
		int nextPos = currentPos + 1;

		if(nextPos >= menuItems.size()){
			setSelected(menuItems.getFirst());
	
		} else {
			setSelected(menuItems.get(nextPos));
		}
	}
	
	public void down(){
		int currentPos = menuItems.indexOf(selected);
		int nextPos = currentPos - 1;

		if(nextPos < 0){
			setSelected(menuItems.getLast());

		} else {
			setSelected(menuItems.get(nextPos));
		}
	}
	

}
