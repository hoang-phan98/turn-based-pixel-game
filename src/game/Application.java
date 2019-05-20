package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.World;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(), new LockedDoor(), new Water(), new RocketPadGround());
		GameMap earthLair;

		List<String> map = Arrays.asList(
				".......................",
				"....#####....######....",
				"....#...#....#....#....",
				"....#...]....#....#....",
				"....#####....##]###....",
				".......................",
				".......................",
				".......................",
				"...................~~~~",
				"...................~~~~",
				"..........x........~~~~");
		earthLair = new GameMap(groundFactory, map);
		world.addMap(earthLair);
        
        List<String> moon = Arrays.asList(
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
				"..........x............");
        
        GameMap moonBase = new GameMap(groundFactory, moon);
        world.addMap(moonBase);
		
		Actor player = new StunnablePlayer("Player", '@', 1, 100);
		world.addPlayer(player, earthLair, 5, 10);
		
		Grunt grunt = new Grunt("Mongo", player);
		earthLair.addActor(grunt, 0, 0);
		Grunt grunt2 = new Grunt("Norbert", player);
		earthLair.addActor(grunt2,  22, 0);
		Ninja ninja = new Ninja("Ninja", player);
		earthLair.addActor(ninja, 3, 5);
		Goon goon = new Goon("Heckler", player);
		earthLair.addActor(goon, 0, 10);
		MiniBoss DoctorMaybe = new MiniBoss("Doctor Maybe");
		earthLair.addActor(DoctorMaybe, 16, 2);
		
		Q q = new Q();
		earthLair.addActor(q, 8, 8);
		
		Item rocketPlan = new RocketPlan();
		earthLair.addItem(rocketPlan, 6, 2);
		
		Item rocket = new Rocket();
		rocket.getAllowableActions().add(new MoveActorAction(earthLair.at(10, 10), "to Earth!"));
		moonBase.addItem(rocket, 10, 10);
		
		Location rocketPadLocation = new Location(earthLair, 10, 10);
		Item rocketPad = new RocketPad(moonBase, rocketPadLocation);
		earthLair.addItem(rocketPad, 10, 10);
		earthLair.add(new RocketPadGround(), rocketPadLocation);
		
		FinalBoss yugoMaxx = new FinalBoss(player);
		moonBase.addActor(yugoMaxx, 0, 0);
		WaterPistol waterPistol = new WaterPistol();
		moonBase.addItem(waterPistol, 6, 5);
		
		player.addItemToInventory(new RocketBody());
		player.addItemToInventory(new RocketEngine());
		
		world.run();
	}
}
