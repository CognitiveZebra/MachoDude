package se.chalmers.TDA367.group13.util;

import java.awt.Toolkit;

public class Constants {
	public static final double ratio = Toolkit.getDefaultToolkit().getScreenSize().getWidth()/ Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	/** Screen height */
	public static final int HEIGHT = (ratio > 1.6) ? 768 : 800;
	/** Screen width */
	public static final int WIDTH = (ratio > 1.6) ? 1360 : 1280;

	public static final int FRAME_TARGET = 60;
	public static float GRAVITY = 0.981f;
}
