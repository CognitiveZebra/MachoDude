package se.chalmers.TDA367.group13;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Highscore {
	private static Scanner scanner;
	private static BufferedWriter bw;
	private static FileWriter fw;
	private static int highscore;

	public static int readHighscore() {
		try {
			File file = new File("res/User/Highscore/highscore.save");

			if (file.exists()) {
				scanner = new Scanner(file);
				highscore = scanner.nextInt();
				scanner.close();

			} else {
				Highscore.writeHighscore(0);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		return highscore;
	}

	public static void writeHighscore(int score) {
		try {
			File file = new File("res/User/Highscore/highscore.save");
			
			if(!file.exists()){
				file.createNewFile();
			}
			
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			bw.write(score + "");

			bw.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
