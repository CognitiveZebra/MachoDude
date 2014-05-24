package se.chalmers.TDA367.group13.model.entities;

import org.newdawn.slick.Animation;

public interface IState {
	Animation getAnimation();
	void setAnimation(Animation a);

}
