package se.chalmers.TDA367.group13;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

import org.newdawn.slick.Input;


public class Controls {

	public static void readControls() {
        Scanner sc = null;

        try {
        	File file = new File("res/User/Controls/controls.save");
        	if (file.exists()) {
	        	sc = new Scanner(new FileReader(file));
	            sc.useDelimiter(";");
	            if (sc.hasNext()) {
	            	SettingsState.setRightKey(sc.nextInt());
	            	SettingsState.setLeftKey(sc.nextInt());
	            	SettingsState.setJumpKey(sc.nextInt());
	            	SettingsState.setShootKey(sc.nextInt());
	            }
	            sc.close();
        	} else {
        		writeControls();
        	}

        } catch (FileNotFoundException e) {
            throw new RuntimeException("I/O error. Could not read from file.", e);
        }
	}

	public static void writeControls() {
		Writer writer = null;
		try {
			File file = new File("res/User/Controls/controls.save");
			 
			if(!file.exists()){
				writer = new PrintWriter(file, "UTF-8");
				file.createNewFile();
                writer.write(String.format("%s;%s;%s;%s;", Input.KEY_D,Input.KEY_A,Input.KEY_W,Input.MOUSE_LEFT_BUTTON));
            	SettingsState.setRightKey(Input.KEY_D);
            	SettingsState.setLeftKey(Input.KEY_A);
            	SettingsState.setJumpKey(Input.KEY_W);
            	SettingsState.setShootKey(Input.MOUSE_LEFT_BUTTON);
			} else {
				writer = new PrintWriter(file, "UTF-8");
				writer.write(String.format("%s;%s;%s;%s;", SettingsState.getRightKey(),
             											SettingsState.getLeftKey(),
             											SettingsState.getJumpKey(),
             											SettingsState.getShootKey()));
			}

             writer.close();
			
			


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
