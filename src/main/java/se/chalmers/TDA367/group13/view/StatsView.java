package se.chalmers.TDA367.group13.view;

import java.util.LinkedList;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import se.chalmers.TDA367.group13.Game;
import se.chalmers.TDA367.group13.util.Stats;
import se.chalmers.TDA367.group13.util.Util;

public class StatsView {
	Font font32 = Util.getFont32();

	private LinkedList<TextItem> items;
	
	public StatsView(){
		initItems(Stats.getInstance().getStatsAsStrings());
	}
	
	private void initItems(LinkedList<String> list) {
		items = new LinkedList<TextItem>();
		
		float width = 350;
		float middleX = Game.WIDTH/2 - width/2;
		
		float heightY = 30;
		
		for(String stat : list){
			items.add(new TextItem(stat, middleX, heightY, width, font32.getHeight(stat) + 20));
			heightY += font32.getHeight(stat) + 30;
		}
	}

	public void render(Graphics g){
		g.setFont(Util.getFont32());
		for(TextItem item : items){
			item.render(g);
		}
		
	}
	
	public void update(){
		initItems(Stats.getInstance().getStatsAsStrings());
	}

}
