package se.chalmers.TDA367.group13.controller;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.TDA367.group13.view.LevelMenuItem;
import se.chalmers.TDA367.group13.view.MenuItem;
import se.chalmers.TDA367.group13.view.MenuView;

public class LevelState extends AbstractMachoDudeState {
	public static final int ID = 55;
	private Image itemImage;
	private MenuView menu;
	private Point mouse;


	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.init(gc,sbg);
		initMenu(sbg);	

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		super.render(gc,sbg,g);
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
				if(item instanceof LevelMenuItem){
					((GameStateController)sbg).setLevel(((LevelMenuItem)item).getLevel());
					sbg.enterState(item.getID());
				} else {
					item.clicked(sbg);
				}

			}
		}

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			menu.down();
		}

		if (input.isKeyPressed(Input.KEY_UP)) {
			menu.up();
		}

		if (input.isKeyPressed(Input.KEY_ENTER)) {
			if(menu.getSelected() instanceof LevelMenuItem){
				((GameStateController)sbg).setLevel(((LevelMenuItem)menu.getSelected()).getLevel());
				sbg.enterState(menu.getSelected().getID());
			} else {
				menu.getSelected().clicked(sbg);
			}
		}
		
		weather.windFactor.setValue(-((gc.getWidth() / 2) - input.getMouseX()) / 20);
		super.update(gc,sbg,delta);
	}

	public void initMenu(StateBasedGame sbg) {
		try {
			itemImage = new Image("res/GUI/menuItem.png");
			int middleX = gc.getWidth() / 2 - itemImage.getWidth() / 2;

			MenuItem level1 = new LevelMenuItem(middleX, 100,
					itemImage, "Level 1", ((GameStateController)sbg).getGameState()
							.getID(),1);
			
			MenuItem level2 = new LevelMenuItem(middleX, 250,
					itemImage, "Level 2", ((GameStateController)sbg).getGameState()
							.getID(),2);
			
			MenuItem mainButton = new MenuItem(middleX, gc.getHeight() - 300,
					itemImage, "MAIN MENU", ((GameStateController)sbg).getMenuState()
							.getID());


			LinkedList<MenuItem> items = new LinkedList<MenuItem>();

			items.add(level1);
			items.add(level2);
			items.add(mainButton);


			menu = new MenuView(items);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getID() {
		return ID;
	}

}
