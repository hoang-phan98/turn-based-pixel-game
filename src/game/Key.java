package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.DropItemAction;

public class Key extends Item {
	public Key(String name, char displayChar) {
		super(name, displayChar);
		allowableActions = new Actions(new DropItemAction(this));
	}
}
