package se.chalmers.TDA367.group13;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ListIterator;

import org.newdawn.slick.Graphics;


public class Menu {
	LinkedList<MenuItem> menuItems;
	MenuItem selected;
	
	public Menu(LinkedList<MenuItem> menuItems){
			this.menuItems = menuItems;

	}
	
	public void render(Graphics g){
		for(MenuItem item : menuItems){
			item.render(g);
		}
	}
	
	public LinkedList<MenuItem> getItems(){
		return menuItems;
	}
	

	

}
