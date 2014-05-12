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

	private LinkedList<StatsItem> items = new LinkedList<>();
	
	public StatsView(){
		initItems();
	}
	
	private void initItems() {
		Stats stats = Stats.getInstance();
		
		String header = "Stats";
		String highscore = "Highscore: " + stats.getHighscore();
		String deaths = "Deaths: " + stats.getDeaths();
		String damageTaken = "Damage taken: " + stats.getDamageTaken();
		String enemiesKilled = "Enemies killed: " + stats.getEnemiesKilled();
		String timePlayed = "Time played:\n" + stats.getTimePlayedAsString();
		
		float width = 350;
		float middleX = Game.WIDTH/2 - width/2;
		
		float heightY = 30;
		items.add(new StatsItem(header, middleX, heightY, width, font32.getHeight(header) + 20));
		heightY += font32.getHeight(highscore) + 30;
		items.add(new StatsItem(highscore, middleX, heightY, width, font32.getHeight(highscore) + 20));
		heightY += font32.getHeight(highscore) + 30;
		items.add(new StatsItem(deaths, middleX, heightY, width, font32.getHeight(deaths) + 20));
		heightY += font32.getHeight(deaths) + 30;
		items.add(new StatsItem(damageTaken, middleX, heightY, width, font32.getHeight(damageTaken) + 20));
		heightY += font32.getHeight(damageTaken) + 30;
		items.add(new StatsItem(enemiesKilled, middleX, heightY, width, font32.getHeight(enemiesKilled) + 20));
		heightY += font32.getHeight(enemiesKilled) + 30;
		items.add(new StatsItem(timePlayed, middleX, heightY, width, font32.getHeight(timePlayed) + 20));
	}

	public void render(Graphics g){
		g.setFont(Util.getFont32());
		for(StatsItem item : items){
			item.render(g);
		}
		
	}

}
