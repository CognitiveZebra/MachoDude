package se.chalmers.TDA367.group13.util;

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
	private static int highscore, enemiesKilled, score;


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
				score = 0;
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
		long timeSecond = 1000;
		long timeMinute = timeSecond*60;
		long timeHour = timeMinute*60;
		long timeDay = timeHour*24;
		
		long days = time / timeDay;
		time = time - (timeDay*days);
 
		long hours = time / timeHour;
		time = time - (timeHour*hours);
 
		long minutes = time / timeMinute;
		time = time - (timeMinute*minutes);
		
		long seconds = time / timeSecond;

		String timeString = String.format("Days %d, Hours %d, Minutes %d, Seconds %d", days, hours, minutes, seconds);
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

	public static void incrementEnemiesKilled() {
		enemiesKilled++;
	}

	public static void addTimePlayed(long time) {
		timePlayed += time;
	}
	
	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		Stats.score = score;
		
		if(Stats.score > highscore){
			highscore = score;
		}
	}
	
	public static void addScore(int i){
		score += i;
		
		if(Stats.score > highscore){
			highscore = score;
		}
	}
}
