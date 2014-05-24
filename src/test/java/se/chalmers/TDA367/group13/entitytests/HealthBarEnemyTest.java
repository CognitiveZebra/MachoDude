package se.chalmers.TDA367.group13.entitytests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.Color;

import se.chalmers.TDA367.group13.model.entities.enemies.HealthBarEnemy;
import se.chalmers.TDA367.group13.model.entities.enemies.enemy1.Enemy_1;

public class HealthBarEnemyTest {

	HealthBarEnemy test = new HealthBarEnemy();

	@Test
	public void testGetColor() {
		Color testColor = test.getColor(3,5);
		Color equalColor = new Color(0.4f,0.6f,0.0f);
		assertEquals(equalColor, testColor);
	}



}
