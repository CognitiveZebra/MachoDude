package se.chalmers.TDA367.group13.controller;

import java.io.IOException;
import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.TDA367.group13.factory.ParticleFactory;
import se.chalmers.TDA367.group13.util.Stats;
import se.chalmers.TDA367.group13.util.Util;
import se.chalmers.TDA367.group13.view.Menu;
import se.chalmers.TDA367.group13.view.MenuItem;

public class GameOverState extends BasicGameState {

	public static final int ID = 1337;
	private Image itemImage, background;
	private Menu menu;
	private GameContainer gc;
	private Input input;
	private ParticleFactory pf;
	private ParticleSystem ps;
	private Point mouse;
	


	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.gc = container;
		background = new Image("res/Backgrounds/Jungle_Test.gif");
		initMenu();
		input = container.getInput();
		pf = new ParticleFactory();
		ps = new ParticleSystem(new Image("res/Particles/particle_rain.png"), 2000);
		ConfigurableEmitter rainEmitter;
		try {
			rainEmitter = pf.createEmitter("rain");
			ps.addEmitter(rainEmitter);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		g.drawImage(background,0,0);

		String s = "GAME OVER";
		g.setFont(Util.getFont32());
		g.drawString(s,gc.getWidth() / 2 - g.getFont().getWidth(s)/2, 250 );
		ps.render();
		
		menu.render(g);
		String scoreString = "Your score: " + Stats.getScore();
		g.drawString(scoreString,gc.getWidth() / 2 - g.getFont().getWidth(scoreString)/2, 300);
		String highscoreString = "Your Highscore: " + Stats.getHighscore();
		g.drawString(highscoreString,gc.getWidth() / 2 - g.getFont().getWidth(highscoreString)/2, 350);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		mouse = new Point(input.getMouseX(), input.getMouseY());
		boolean isMousePressed = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);
		
		for(MenuItem item : menu.getItems()){

			if(item.contains(mouse)){
				menu.setSelected(item);
			}
			
			if(item.contains(mouse) && isMousePressed){
					game.enterState(item.getID());
			}
			
			if(input.isKeyPressed(Input.KEY_DOWN)){
				menu.down();
			}
			
			if(input.isKeyPressed(Input.KEY_UP)){
				menu.up();
			}
			
			if(input.isKeyDown(Input.KEY_ENTER) && item.equals(menu.getSelected())){
				game.enterState(menu.getSelected().getID());
			}
		}
		

		
		ps.update(delta);
	}
		

	@Override
	public int getID() {
		return ID;
	}
	
	public void initMenu(){
		try {
			itemImage = new Image("res/GUI/menuItem.png");
			int middleX = gc.getWidth()/2 - itemImage.getWidth()/2;

			MenuItem mainButton = new MenuItem(middleX, gc.getHeight() - 300, itemImage, "MAIN MENU", GameStateController.getMenuState().getID());
			MenuItem quitButton = new MenuItem(middleX, gc.getHeight() - 200, itemImage, "QUIT", GameStateController.getQuitState().getID());
			
			LinkedList<MenuItem> items = new LinkedList<MenuItem>();
			items.add(mainButton);
			items.add(quitButton);
			menu = new Menu(items);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
