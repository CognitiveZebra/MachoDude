package se.chalmers.TDA367.group13;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Stats {
	private static Scanner scanner;
	private static BufferedWriter bw;
	private static FileWriter fw;
	private static int enemyCount;


	public static int readEnemyCount() {
		try {
			File file = new File("res/User/Stats/enemyCount.save");

			if (file.exists()) {
				scanner = new Scanner(file);
				if(scanner.hasNextInt()){
					enemyCount = scanner.nextInt();
					System.out.println("Enemycount = " + enemyCount );
				} else {
					Stats.writeEnemyCount(0);
				}

				scanner.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			return enemyCount;
	}

	public static void addEnemyCount(int score) {
		int total = score + Stats.readEnemyCount();
		Stats.writeEnemyCount(total);
	}
	
	public static void writeEnemyCount(int enemyCount){
		try {
			File file = new File("res/User/Stats/enemyCount.save");
			
			if(!file.exists()){
				file.createNewFile();
			}
			
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			
			bw.write(enemyCount + "");

			bw.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
