package se.chalmers.TDA367.Group13.main;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.lwjgl.LWJGLUtil;

import se.chalmers.TDA367.Group13.blocktests.BlockTest;
import se.chalmers.TDA367.Group13.blocktests.DestructableBlockTest;
import se.chalmers.TDA367.Group13.entitytests.Boss1ProjectileTest;
import se.chalmers.TDA367.Group13.entitytests.Boss_1Test;
import se.chalmers.TDA367.Group13.entitytests.Enemy1ProjectileTest;
import se.chalmers.TDA367.Group13.entitytests.Enemy_1Test;
import se.chalmers.TDA367.Group13.entitytests.Enemy_2Test;
import se.chalmers.TDA367.Group13.entitytests.FireProjectileTest;
import se.chalmers.TDA367.Group13.entitytests.HealthBarEnemyTest;
import se.chalmers.TDA367.Group13.entitytests.PlayerProjectileTest;
import se.chalmers.TDA367.Group13.entitytests.ProjectileImageFactoryTest;
import se.chalmers.TDA367.Group13.gameLogicTests.GameModelTest;
import se.chalmers.TDA367.Group13.gameLogicTests.GameTest;
import se.chalmers.TDA367.Group13.stateTests.GameOverStateTest;
import se.chalmers.TDA367.Group13.stateTests.GameStateControllerTest;
import se.chalmers.TDA367.Group13.stateTests.GameStateTest;
import se.chalmers.TDA367.Group13.stateTests.MenuStateTest;
import se.chalmers.TDA367.Group13.stateTests.QuitStateTest;
import se.chalmers.TDA367.Group13.stateTests.SettingsStateTest;
import se.chalmers.TDA367.Group13.stateTests.StatsStateTest;

@RunWith(Suite.class)
@SuiteClasses({ BlockTest.class, DestructableBlockTest.class, Boss_1Test.class, Boss1ProjectileTest.class, Enemy_1Test.class,
	Enemy_2Test.class, Enemy1ProjectileTest.class,
	FireProjectileTest.class, HealthBarEnemyTest.class,
	PlayerProjectileTest.class, ProjectileImageFactoryTest.class, GameModelTest.class, GameTest.class, GameOverStateTest.class, GameStateControllerTest.class,
	GameStateTest.class, MenuStateTest.class, QuitStateTest.class,
	SettingsStateTest.class, StatsStateTest.class })
public class AllTests {

	@BeforeClass
	public static void startUp(){
		
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"),"target"), "natives").getAbsolutePath());
		  
		System.setProperty("net.java.games.input.librarypath",System.getProperty("org.lwjgl.librarypath"));
	}


}
