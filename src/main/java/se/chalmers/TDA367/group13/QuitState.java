package se.chalmers.TDA367.group13;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



public class QuitState extends BasicGameState {
	private static final int ID = 666;
	private long enterTime, duration = 1000;
	private AngelCodeFont font32;
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		enterTime = System.currentTimeMillis();

		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		String s = "FIGHTING SPIRIT?";
		g.setFont(font32);
		g.drawString(s,gc.getWidth() / 2 - g.getFont().getWidth(s)/2,gc.getHeight() / 2 - g.getFont().getHeight(s)/2 );
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if(enterTime + duration < System.currentTimeMillis()){
			gc.exit();
		}
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		font32 = new AngelCodeFont("res/Fonts/Visitor1.fnt", "res/Fonts/Visitor1_0.png");
	}
}
