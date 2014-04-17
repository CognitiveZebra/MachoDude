package se.chalmers.TDA367.group13.factory;

import java.io.IOException;

import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ConfigurableEmitterFactory;
import org.newdawn.slick.particles.ParticleIO;


public class ParticleFactory implements ConfigurableEmitterFactory {
	ConfigurableEmitter rain, rainSplatter,smoke, blood, fire;	
		
	public ParticleFactory(){
		try {
			rain = ParticleIO.loadEmitter("res/Particles/rain.xml");
			rainSplatter = ParticleIO.loadEmitter("res/Particles/rainSplatter.xml");
			smoke = ParticleIO.loadEmitter("res/Particles/smoke.xml");
			blood = ParticleIO.loadEmitter("res/Particles/blood.xml");
			fire = ParticleIO.loadEmitter("res/Particles/fire.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ConfigurableEmitter createEmitter(String emitter) {
		switch(emitter){
		case "rain":
			return rain;
		case "rainSplatter":
			return rainSplatter;
		case "smoke": 
			return smoke;
		case "blood":
			return blood;
		case "fire":
			return fire;
		default:
			return null;
		}
	}

}
