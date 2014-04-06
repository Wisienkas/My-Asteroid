package dk.sdu.mmmi.cbse.project.ship;
import java.util.ArrayList;
import java.util.List;

import dk.sdu.mmmi.cbse.project.gamespi.IGamePlugin;


public class ShipFactory implements IGamePlugin{
	
	List<IShip> list = new ArrayList<IShip>();
	
	public ShipFactory(){
		list.add(new Ship());
	}

	public void process(int delta) {
		for (IShip is: list) {
			is.process();
		}
	}

	public void create() {
		list.add(new EnemyShip());
	}
}
