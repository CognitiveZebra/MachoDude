package se.chalmers.TDA367.group13;

import java.io.IOException;

import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ConfigurableEmitterFactory;
import org.newdawn.slick.particles.ParticleIO;


public class ParticleFactory implements ConfigurableEmitterFactory {
	ConfigurableEmitter rain;	
		
	public ParticleFactory(){
		try {
			rain = ParticleIO.loadEmitter("res/Particles/rain.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ConfigurableEmitter createEmitter(String emitter) {
		switch(emitter){
		case "rain":
			return rain;
		default:
			return null;
		}
	}

}
