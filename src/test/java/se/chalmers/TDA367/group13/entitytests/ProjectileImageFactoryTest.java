package se.chalmers.TDA367.group13.entitytests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Image;

import se.chalmers.TDA367.group13.entities.projectile.ProjectileImageFactory;

public class ProjectileImageFactoryTest {

	@Test
	public void testGetImage() throws Exception {
		assertEquals(new Image("res/Sprites/testArm.png").getClass(),ProjectileImageFactory.getImage("player").getClass());
	}

}
