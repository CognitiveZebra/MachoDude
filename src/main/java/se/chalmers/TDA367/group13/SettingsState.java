package se.chalmers.TDA367.group13;

import java.util.LinkedList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.TDA367.group13.util.Controls;

public class SettingsState extends BasicGameState{
	public static final int ID = 15, RIGHT = 0,LEFT = 1,JUMP = 2,SHOOT = 3;
	GameContainer gc;
	private Image background, menuItemImage;
	private Input input;
	private SettingsView settingsView;
	private Point mouse;
	private ParticleFactory pf;
	private ParticleSystem ps;
	private Integer key, button;
	private boolean settable = false;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		this.gc = gc;
		input = gc.getInput();
		background = new Image("res/Backgrounds/Jungle_Test.gif");
		initSettings();
		input = gc.getInput();
		pf = new ParticleFactory();
		ps = new ParticleSystem(new Image("res/Particles/particle_rain.png"), 2000);
		ConfigurableEmitter rainEmitter = pf.createEmitter("rain");
		ps.addEmitter(rainEmitter);
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawImage(background,0,0);

		ps.render();
		
		settingsView.render(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		mouse = new Point(input.getMouseX(), input.getMouseY());
		boolean isMousePressed = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);
		
		
		for(SettingsItem item : settingsView.getSettingsItems()){

			if(item.contains(mouse)){
				settingsView.setSelected(item);
			}
			
			if(item.contains(mouse) && isMousePressed){
				for(SettingsItem i : settingsView.getSettingsItems()){
					if (i.getText() == "??"){
						i.setText(i.getControlText());
					}
				}
				item.setText("??");
				settable = true;
			}
			
			if(item.getText().equals("??")){
				if (key != null) {
					item.setControl(key);
					item.setText(item.getControlText());
					key = null;
					settable = false;
				}
				for (SettingsItem i : settingsView.getSettingsItems()) {
					if(i.contains(mouse) && !i.equals(item)){
						i.setText(i.getControlText());
						button = null;
						settable = false;
					} else if (button != null){
						item.setControl(button);
						item.setText(item.getControlText());
						button = null;
						settable = false;
					}
				}
		
			}
		}
		
		for (MenuItem item : settingsView.getMenuItems()) {
			if(item.contains(mouse)){
				settingsView.setSelected(item);
			}
			
			if(item.contains(mouse) && isMousePressed){
				if (item.getID() == -1) {
					Controls.reset();
					resetSettingsItems();
				} else {
					sbg.enterState(item.getID());
				}
					
				
			}
		}

		ps.update(delta);
		
		
	}
	
	private void resetSettingsItems() {
		for (SettingsItem item : settingsView.getSettingsItems()) {
			switch (item.getID()) {
			case RIGHT: item.setControl(Controls.getRightKey());	break;
			case LEFT: 	item.setControl(Controls.getLeftKey()); 	break;
			case JUMP:	item.setControl(Controls.getJumpKey()); 	break;
			case SHOOT: item.setControl(Controls.getShootKey()); 	break;
			default:
				break;
			}
			item.setText(item.getControlText());
		}
		
	}

	public void initSettings(){
		try {
			menuItemImage = new Image("res/GUI/menuItem.png");
			int settingMiddleX = gc.getWidth()/2 - menuItemImage.getWidth()/2;
			int menuMiddleX = gc.getWidth()/2 - menuItemImage.getWidth()/2;
			SettingsItem rightButton = new SettingsItem(settingMiddleX, 100, menuItemImage, "RIGHT", RIGHT, Controls.getRightKey());
			SettingsItem leftButton = new SettingsItem(settingMiddleX, 200, menuItemImage, "LEFT",LEFT, Controls.getLeftKey());
			SettingsItem jumpButton = new SettingsItem(settingMiddleX, 300, menuItemImage, "JUMP",JUMP, Controls.getJumpKey());
			SettingsItem shootButton = new SettingsItem(settingMiddleX, 400, menuItemImage, "SHOOT",SHOOT, Controls.getShootKey());
			MenuItem resetButton = new MenuItem(menuMiddleX, 525, menuItemImage, "RESET CONTROLS");
			MenuItem mainButton = new MenuItem(menuMiddleX, 625, menuItemImage, "MAIN MENU", GameStateController.getMenuState().getID());
			
			
			LinkedList<SettingsItem> SettingsItems = new LinkedList<SettingsItem>();
			LinkedList<MenuItem> menuItems = new LinkedList<MenuItem>();

			menuItems.add(mainButton);
			menuItems.add(resetButton);
			
			
			SettingsItems.add(rightButton);
			SettingsItems.add(leftButton);
			SettingsItems.add(jumpButton);
			SettingsItems.add(shootButton);
			
			settingsView = new SettingsView(SettingsItems, menuItems);
			
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if (settable)
		this.key = key;
	}
	
	
	@Override
	public void mousePressed(int button, int x, int y) {
		if(settable)
		this.button = button;
	}

}
