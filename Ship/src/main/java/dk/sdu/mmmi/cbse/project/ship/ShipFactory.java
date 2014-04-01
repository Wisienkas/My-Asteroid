package dk.sdu.mmmi.cbse.project.ship;
import java.util.ArrayList;
import java.util.List;

import dk.sdu.mmmi.cbse.project.common.Entity;
import dk.sdu.mmmi.cbse.project.gamespi.IGamePlugin;


public class ShipFactory implements IGamePlugin{
	
	List<IShip> list = new ArrayList<IShip>();
	
	public ShipFactory(){
		list.add(new Ship());
	}

	@Override
	public void process(int delta) {
		for (IShip is: list) {
			is.process();
		}
	}

	@Override
	public void create() {
		list.add(new EnemyShip());
	}
}
