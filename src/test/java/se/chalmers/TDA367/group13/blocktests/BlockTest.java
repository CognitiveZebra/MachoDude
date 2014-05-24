package se.chalmers.TDA367.group13.blocktests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GLContext;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.TDA367.group13.entities.Entity;
import se.chalmers.TDA367.group13.entities.block.Block;

public class BlockTest {

	Block test;
	
	@Before
	public void setUp() throws Exception{
		test = new Block(1, 1, new Image("res/Sprites/testArm.png"));

	}
	@Test
	public void testBlock() {

		assertTrue(test instanceof Entity);
	}

}
