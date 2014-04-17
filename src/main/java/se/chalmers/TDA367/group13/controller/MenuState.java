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
import se.chalmers.TDA367.group13.view.Menu;
import se.chalmers.TDA367.group13.view.MenuItem;

public class MenuState extends BasicGameState {
	public static final int ID = 2;
	GameContainer gc;
	private Image background, itemImage, settingsImage;
	private Input input;
	private Menu menu;
	private Point mouse;
	private ParticleSystem ps;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		this.gc = gc;
		background = new Image("res/Backgrounds/Jungle_Test.gif");
		initMenu();
		input = gc.getInput();
		ps = new ParticleSystem(new Image("res/Particles/particle_rain.png"),
				2000);
		ConfigurableEmitter rainEmitter;
		rainEmitter = ParticleFactory.createEmitter("rain");
		ps.addEmitter(rainEmitter);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(background, 0, 0);

		ps.render();

		menu.render(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		mouse = new Point(input.getMouseX(), input.getMouseY());
		boolean isMousePressed = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);

		for (MenuItem item : menu.getItems()) {

			if (item.contains(mouse)) {
				menu.setSelected(item);
			}

			if (item.contains(mouse) && isMousePressed) {
				sbg.enterState(item.getID());
			}
		}

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			menu.down();
		}

		if (input.isKeyPressed(Input.KEY_UP)) {
			menu.up();
		}

		if (input.isKeyPressed(Input.KEY_ENTER)) {
			sbg.enterState(menu.getSelected().getID());
		}

		ps.update(delta);
	}

	public void initMenu() {
		try {
			itemImage = new Image("res/GUI/menuItem.png");
			settingsImage = new Image("res/GUI/settingsButton.png");
			int middleX = gc.getWidth() / 2 - itemImage.getWidth() / 2;

			MenuItem playButton = new MenuItem(middleX, gc.getHeight() - 400,
					itemImage, "PLAY", GameStateController.getGameState()
							.getID());
			MenuItem statsButton = new MenuItem(middleX, gc.getHeight() - 300,
					itemImage, "STATS", GameStateController.getStatsState()
							.getID());
			MenuItem quitButton = new MenuItem(middleX, gc.getHeight() - 200,
					itemImage, "QUIT", GameStateController.getQuitState()
							.getID());
			MenuItem settingsButton = new MenuItem(20, gc.getHeight()
					- settingsImage.getHeight() - 20, settingsImage, "",
					GameStateController.getSettingsState().getID());

			LinkedList<MenuItem> items = new LinkedList<MenuItem>();

			items.add(playButton);
			items.add(statsButton);
			items.add(quitButton);
			items.add(settingsButton);

			menu = new Menu(items);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getID() {
		return ID;
	}

}
