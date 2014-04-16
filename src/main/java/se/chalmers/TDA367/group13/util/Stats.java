package se.chalmers.TDA367.group13.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.Scanner;

public class Stats {
	private static Scanner scanner;
	private static BufferedWriter bw;
	private static FileWriter fw;
	private static int highscore, enemiesKilled;
	private static long timePlayed;

	public static void readStats() {
		try {
			File file = new File("res/User/Stats/Stats.save");

			if (file.exists()) {
				scanner = new Scanner(file);
				scanner.useDelimiter(";");
				highscore = scanner.nextInt();
				enemiesKilled = scanner.nextInt();
				timePlayed = scanner.nextLong();
				scanner.close();
			} else {
				Stats.reset();
				Stats.saveStats();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void reset() {
		highscore = 0;
		enemiesKilled = 0;
		timePlayed = 0;
	}

	public static String getStatsString() {
		long time = timePlayed;
		
		long days = time /1000*60*60*24;
		time = time % 1000*60*60*24;
 
		long hours = time / 1000*60*60;
		time = time % 1000*60*60;
 
		long minutes = time / 1000*60;
		time = time % 1000*60;

		String timeString = String.format("Days %d, Hours %d, Minutes %d", days, hours, minutes);
		return String.format(
				"Highscore: %d\nEnemies killed: %d\nTime Played: %s",
				highscore, enemiesKilled, timeString);
	}

	public static void saveStats() {
		try {
			File file = new File("res/User/Stats/Stats.save");

			if (!file.exists()) {
				file.createNewFile();
			}

			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			String save = highscore + ";" + enemiesKilled + ";" + timePlayed;
			bw.write(save);

			bw.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getHighscore() {
		return highscore;
	}

	public static void setHighscore(int highscore) {
		Stats.highscore = highscore;
	}

	public static int getEnemiesKilled() {
		return enemiesKilled;
	}

	public static void setEnemiesKilled(int enemiesKilled) {
		Stats.enemiesKilled = enemiesKilled;
	}

	public static long getTimePlayed() {
		return timePlayed;
	}

	public static void setTimePlayed(long timePlayed) {
		Stats.timePlayed = timePlayed;
	}

	public static void addEnemiesKilled(int i) {
		enemiesKilled += i;
	}

	public static void addTimePlayed(long time) {
		timePlayed += time;
	}
}
