package game;

import java.util.Random;

import edu.monash.fit2099.engine.*;

public class miniBoss extends Actor {
	
	private Random rand = new Random();
	
	public miniBoss(String name) {
		super(name, 'd', 5, 10);
		Item rocketEngine = Item.newInventoryItem("Rocket Engine", 'e');
		this.addItemToInventory(rocketEngine);
	}
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		Action action = new DropItemAction(null);
		while(action instanceof DropItemAction || action instanceof PickUpItemAction) {
			action = actions.get(rand.nextInt(actions.size()));
		}
		
		return action;
	}
}
