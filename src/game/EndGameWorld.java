package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.SkipTurnAction;
import edu.monash.fit2099.engine.World;

/**
 * Extension of World class with the different methods of ending the game
 */
public class EndGameWorld extends World {
	private int endGameID;
	private boolean quit = false;

	public EndGameWorld(Display display) {
		super(display);
	}
	
	/**
	 * Set to true if the player chooses to quit the game
	 */
	public void quit() {
		this.quit = true;
	}
	
	@Override
	/**
	 * Setting the endGameID based on how the game ends
	 * endGameID is used to determine the endGameMessage printed out
	 */
	protected boolean stillRunning() {
		
		// If the player chooses to quit
		if(this.quit) {
			return false;
		}
		
		// If the player's inventory contains Yugo's unconscious body 
		// and while they are standing on earth (Floor)
		for(Item item: this.player.getInventory()) {
			String name = item.toString();
			if(name == "Sleeping Yugo Maxx") {
				Location here = actorLocations.locationOf(player);
				if(here.getGround() instanceof Floor) {
					this.endGameID = 1;
					return false;
				}
			}
		}
		
		// If the player gets knocked out
		if(!(actorLocations.contains(player))) {
			this.endGameID = 2;
			return false;
		}
		
		return true;
	}
	
	@Override
	/**
	 * Prints out different messages depending on how the game ends
	 */
	protected String endGameMessage() {
		if(endGameID == 1) {
			return "Congratulations, you win!";
		}
		
		if(endGameID == 2) {
			return "Game over, you've been knocked out";
		}
		
		return "Game over";
	}
	
	@Override
	/**
	 * Giving the player the option to end the game
	 */
	protected void processActorTurn(Actor actor) {
		Location here = actorLocations.locationOf(actor);
		GameMap map = here.map();

		Actions actions = new Actions();
		for (Item item : actor.getInventory()) {
			actions.add(item.getAllowableActions());
		}

		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (actorLocations.isAnActorAt(destination)) {
				Actor adjacentActor = actorLocations.actorAt(destination);
				actions.add(adjacentActor.getAllowableActions(actor, exit.getName(), map));
			} else {
				Ground adjacentGround = map.groundAt(destination);
				actions.add(adjacentGround.allowableActions(actor, destination, exit.getName()));
				actions.add(adjacentGround.getMoveAction(actor, destination, exit.getName(), exit.getHotKey()));
			}
		}

		for (Item item : here.getItems()) {
			actions.add(item.getAllowableActions());
		}
		actions.add(new SkipTurnAction());
		
		if(actor instanceof StunnablePlayer) {
			actions.add(new EndGameAction(this));
		}
		
		Action action = actor.playTurn(actions, map, display);
		String result = action.execute(actor, map);
		display.println(result);
	}
}
