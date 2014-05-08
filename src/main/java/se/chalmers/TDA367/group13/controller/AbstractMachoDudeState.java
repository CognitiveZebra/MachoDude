package se.chalmers.TDA367.group13.controller;

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

public abstract class AbstractMachoDudeState extends BasicGameState {
	protected Point mouse;
	protected ParticleSystem ps;
	protected ConfigurableEmitter weather;
	protected Input input;
	protected Image background;
	protected GameContainer gc;
	public static final int ID = 13;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		this.gc = container;
		input = gc.getInput();
		background = new Image("res/Backgrounds/Jungle_Test.gif");
		ps = new ParticleSystem(new Image("res/Particles/particle_rain.png"),
				2000);

		weather = ParticleFactory.createEmitter("rain");
		ps.addEmitter(weather);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		g.drawImage(background, 0, 0);

		ps.render();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		ps.update(delta);
		
	}

	@Override
	public int getID() {
		return ID;
	}

}
