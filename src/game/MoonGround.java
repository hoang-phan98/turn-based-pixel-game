package game;

import edu.monash.fit2099.demo.DemoSkills;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

public class MoonGround extends Ground {

	public MoonGround() {
		super(',');
	}
	
	@Override
	public boolean canActorEnter(Actor a) {
		return a.hasSkill(Skills.SPACETRAVELLER);
	}
}
