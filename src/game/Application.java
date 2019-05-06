package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.World;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(), new Door());
		GameMap gameMap;

		List<String> map = Arrays.asList(
				".......................",
				"....#####....######....",
				"....#...#....#....#....",
				"....#...]....#....#....",
				"....#####....##.###....",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................");
		gameMap = new GameMap(groundFactory, map);
		world.addMap(gameMap);
		
        List<String> map2 = Arrays.asList(
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................",
				".......................");
        
        GameMap gameMap2 = new GameMap(groundFactory, map2);
        world.addMap(gameMap2);

        Item rocket = Item.newFurniture("Rocket", '>');
        rocket.getAllowableActions().add(new MoveActorAction(gameMap2.at(10, 5), "to Map 2!"));
        gameMap.addItem(rocket, 22, 5);
        
        Item rocket2 = Item.newFurniture("Rocket", '<');
        rocket2.getAllowableActions().add(new MoveActorAction(gameMap.at(22, 5), "to Map 1!"));
        gameMap2.addItem(rocket2, 0, 5);
		
		Actor player = new Player("Player", '@', 1, 100);
		world.addPlayer(player, gameMap, 5, 10);
		
		Grunt grunt = new Grunt("Mongo", player);
		gameMap.addActor(grunt, 0, 0);
		Grunt grunt2 = new Grunt("Norbert", player);
		gameMap.addActor(grunt2,  10, 10);
		Ninja ninja = new Ninja("Kwab", player);
		gameMap.addActor(ninja, 5, 5);
		Goon goon = new Goon("Heckler", player);
		goon.addItemToInventory(key);
		gameMap.addActor(goon, 7, 7);
		
		Item rocketPlan = new Item("Rocket Plan", 'p');
		gameMap.addItem(rocketPlan, 6, 2);
		world.run();
	}
}
