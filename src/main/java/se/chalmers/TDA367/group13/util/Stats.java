package se.chalmers.TDA367.group13.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Stats {
	private  Scanner scanner;
	private  BufferedWriter bw;
	private  FileWriter fw;
	private  int highscore, enemiesKilled, score, deaths, damageTaken;
	private long timePlayed;
	private static Stats instance = null;
	
	private Stats(){
		readStats();
	}

	private void readStats() {
		try {
			File file = new File("res/User/Stats/Stats.save");

			if (file.exists()) {
				scanner = new Scanner(file);
				scanner.useDelimiter(";");
				highscore = scanner.nextInt();
				enemiesKilled = scanner.nextInt();
				timePlayed = scanner.nextLong();
				deaths = scanner.nextInt();
				damageTaken = scanner.nextInt();
				scanner.close();
				score = 0;
			} else {
				reset();
				saveStats();
				readStats();
			}
		} catch (FileNotFoundException e) {
			reset();
			saveStats();
			readStats();
		}
	}
	
	public static synchronized Stats getInstance(){
		if(instance == null){
			instance = new Stats();
		}
		return instance; 
	}

	public void reset() {
		highscore = 0;
		enemiesKilled = 0;
		timePlayed = 0;
		deaths = 0;
		damageTaken = 0;
	}

	public String getTimePlayedAsString() {
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

		return String.format("%d Days\n%d Hours\n%d Minutes\n%d Seconds", days, hours, minutes, seconds);
	}

	public void saveStats() {
		try {
			File file = new File("res/User/Stats/Stats.save");

			if (!file.exists()) {
				file.createNewFile();
			}

			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			String save = highscore + ";" + enemiesKilled + ";" + timePlayed + ";" + deaths + ";" + damageTaken;
			bw.write(save);

			bw.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	public int getEnemiesKilled() {
		return enemiesKilled;
	}

	public void setEnemiesKilled(int enemiesKilled) {
		this.enemiesKilled = enemiesKilled;
	}

	public  long getTimePlayed() {
		return timePlayed;
	}

	public void setTimePlayed(long timePlayed) {
		this.timePlayed = timePlayed;
	}

	public void incrementEnemiesKilled() {
		enemiesKilled++;
	}

	public  void addTimePlayed(long time) {
		timePlayed += time;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
		
		if(this.score > highscore){
			highscore = score;
		}
	}
	
	public void addScore(int i){
		score += i;
		
		if(this.score > highscore){
			highscore = score;
		}
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	
	public void incrementDeaths(){
		deaths++;
	}

	public int getDamageTaken() {
		return damageTaken;
	}

	public void setDamageTaken(int damageTaken) {
		this.damageTaken = damageTaken;
	}
	
	public void incrementDamageTaken(){
		damageTaken++;
	}
	
	public LinkedList<String> getStatsAsStrings(){
		LinkedList<String> list = new LinkedList<>();
		list.add("Stats");
		list.add("Highscore: " + highscore);
		list.add("Deaths: " + deaths);
		list.add("Enemies killed: " + enemiesKilled); 
		list.add("Damage taken: " + damageTaken);
		list.add("Time played:\n" + getTimePlayedAsString());
		return list;
	}
	
	
}
