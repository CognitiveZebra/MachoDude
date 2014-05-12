package se.chalmers.TDA367.group13.view;

import java.util.LinkedList;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import se.chalmers.TDA367.group13.Game;
import se.chalmers.TDA367.group13.util.Stats;
import se.chalmers.TDA367.group13.util.Util;

public class GameOverView {
	
	Font font32 = Util.getFont32();

	private LinkedList<TextItem> items = new LinkedList<>();
	
	public GameOverView(){
		String[] arr = new String[3];
		arr[0] = "GAME OVER";
		arr[1] = "Your score: " + Stats.getInstance().getScore();
		arr[2] = "Your Highscore: " + Stats.getInstance().getHighscore();
		initItems(arr);
	}
	
	private void initItems(String[] arr) {
		Stats stats = Stats.getInstance();

		float width = 350;
		float middleX = Game.WIDTH/2 - width/2;
		
		float heightY = 200;
		
		for(String stat : arr){
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
