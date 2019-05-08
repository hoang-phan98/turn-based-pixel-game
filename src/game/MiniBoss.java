package game;

import java.util.Random;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.*;

public class MiniBoss extends Actor {
	
	private Random rand = new Random();
	
	public MiniBoss(String name) {
		super(name, 'd', 5, 10);
		Item rocketEngine = Item.newInventoryItem("Rocket Engine", 'e');
		this.addItemToInventory(rocketEngine);
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
		
		Action action = new DropItemAction(null);
		while(action instanceof DropItemAction || action instanceof PickUpItemAction) {
			action = actions.get(rand.nextInt(actions.size()));
		}
		
		return action;
	}
}

