package se.chalmers.TDA367.group13;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame {

    /** Screen width */
    public static final int WIDTH = 1216;
    /** Screen height */
    public static final int HEIGHT = 768;
    
    /** A counter... */
    private int counter;

    public Game() {
        super("MachoDude");
    }

    public void render(GameContainer container, Graphics g) throws SlickException {

    }

    @Override
    public void init(GameContainer container) throws SlickException {
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    }
    
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new GameStateController("MachoDude"));
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setForceExit(false);
        app.setTargetFrameRate(60);
        app.start();
    }

}
