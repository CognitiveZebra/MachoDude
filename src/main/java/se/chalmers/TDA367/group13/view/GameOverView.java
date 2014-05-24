package se.chalmers.TDA367.group13.view;

import java.util.LinkedList;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import se.chalmers.TDA367.group13.util.Constants;
import se.chalmers.TDA367.group13.util.Stats;
import se.chalmers.TDA367.group13.util.Util;

public class GameOverView {
	
	Font font32 = Util.getFont32();

	private LinkedList<TextItem> items = new LinkedList<>();
	
	public GameOverView(){
		LinkedList<String> list = new LinkedList<String>();
		list.add("GAME OVER");
		list.add("Score: " + Stats.getInstance().getScore());
		list.add("Highscore: " + Stats.getInstance().getHighscore());
		initItems(list);
	}
	
	private void initItems(LinkedList<String> list) {
		Stats stats = Stats.getInstance();

		float width = 350;
		float middleX = Constants.WIDTH/2 - width/2;
		
		float heightY = 200;
		
		for(String stat : list){
			items.add(new TextItem(stat, middleX, heightY, width, font32.getHeight(stat) + 20));
			heightY += font32.getHeight(stat) + 30;
		}
	}

	public void render(Graphics g){
		g.setFont(font32);
		for(TextItem item : items){
			item.render(g);
		}
		
	}

}
