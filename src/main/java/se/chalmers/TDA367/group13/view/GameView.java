package se.chalmers.TDA367.group13.view;

import javax.swing.text.html.parser.Entity;

import org.newdawn.slick.Graphics;

import se.chalmers.TDA367.group13.model.GameModel;
import se.chalmers.TDA367.group13.util.Constants;
import se.chalmers.TDA367.group13.util.Stats;

public class GameView {
	GameModel model;
	
	public GameView(GameModel model){
		this.model = model;
	}

	public void render(Graphics g){
		renderLevel(g);
		renderPlayer(g);
		new TextItem("Score: " + Stats.getInstance().getScore(), Constants.WIDTH - 200, 10).render(g);
	}

	public void renderLevel(Graphics g){
		model.getLevel().render(g);
		
	}
		
	public void renderPlayer(Graphics g){
		model.getPlayer().render(g);
	}
}
