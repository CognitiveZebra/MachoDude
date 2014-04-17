package se.chalmers.TDA367.group13.factory;

import java.io.IOException;

import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;


public class ParticleFactory {

	public static ConfigurableEmitter createEmitter(String emitter) throws IOException{
		switch(emitter){
		case "rain":
			return  ParticleIO.loadEmitter("res/Particles/rain.xml");
		case "rainSplatter":
			return ParticleIO.loadEmitter("res/Particles/rainSplatter.xml");
		case "smoke": 
			return ParticleIO.loadEmitter("res/Particles/smoke.xml");
		case "blood":
			return ParticleIO.loadEmitter("res/Particles/blood.xml");
		case "fire":
			return ParticleIO.loadEmitter("res/Particles/fire.xml");
		default:
			return null;
		}
	}

}
