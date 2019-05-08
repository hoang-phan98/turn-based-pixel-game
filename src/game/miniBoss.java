package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class miniBoss extends Actor {
	
	public miniBoss(String name) {
		super(name, 'd', 5, 10);
		Item engine = new RocketEngine("Rocket Engine", 'e');
		this.addItemToInventory(engine);
		
	}
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}
	
	
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(1, "lightly slaps");
	}

	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		
		return super.playTurn(actions,  map,  display);
	}
}

