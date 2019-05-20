package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

public class RocketPadGround extends Ground {

	public RocketPadGround() {
		super('x');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		if(actor instanceof StunnablePlayer) {
			return true;
		}
		return false;
	}

}
