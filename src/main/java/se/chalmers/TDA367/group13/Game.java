package se.chalmers.TDA367.group13;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.TDA367.group13.controller.GameStateController;
import se.chalmers.TDA367.group13.util.Controls;
import se.chalmers.TDA367.group13.util.Stats;

public class Game  {

    /** Screen width */
    public static final int WIDTH = 1216;
    /** Screen height */
    public static final int HEIGHT = 768;
    
    public static final int FRAME_TARGET = 60;
        
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new GameStateController("MachoDude"),WIDTH, HEIGHT, false);
        app.setForceExit(false);
        app.setTargetFrameRate(FRAME_TARGET);
        app.start();
        
        
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
            	Stats.getInstance().saveStats();
            	Controls.getInstance().saveControls();
            }
        }));
    }
}
