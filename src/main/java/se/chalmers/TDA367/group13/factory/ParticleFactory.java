package se.chalmers.TDA367.group13.factory;

import java.io.IOException;

import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;


public class ParticleFactory {

	public static ConfigurableEmitter createEmitter(String emitter){
		String path = "res/Particles/rain.xml";
		
		switch(emitter){
		case "rain":
			path = "res/Particles/rain.xml"; break;
		case "rainSplatter":
			path = "res/Particles/rainSplatter.xml"; break;
		case "smoke": 
			path = "res/Particles/smoke.xml"; break;
		case "blood":
			path = "res/Particles/blood.xml"; break;
		case "fire":
			path = "res/Particles/fire.xml"; break; 
		}

		try {
			return ParticleIO.loadEmitter(path);
		} catch (IOException e) {

		}
		return null; 
	}	
		
}
