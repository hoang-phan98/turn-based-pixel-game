package game;

import edu.monash.fit2099.engine.*;

public class miniBoss extends Actor {
	
	public miniBoss(String name) {
		super(name, 'd', 5, 10);
		Item key = new Key("key", 'k');
		this.addItemToInventory(key);
	}
}
