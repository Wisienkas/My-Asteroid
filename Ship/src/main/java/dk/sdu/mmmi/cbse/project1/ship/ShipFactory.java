package dk.sdu.mmmi.cbse.project1.ship;
import java.util.ArrayList;
import java.util.List;

import dk.sdu.mmmi.cbse.project1.common.Entity;
import dk.sdu.mmmi.cbse.project1.gamespi.IGamePlugin;


public class ShipFactory implements IGamePlugin{
	
	List<IShip> list = new ArrayList<IShip>();
	private boolean hasControllable = false;
	
	public ShipFactory(){
	}

	@Override
	public void process(int delta) {
		for (IShip is: list) {
			is.process();
		}
	}

	@Override
	public List<Entity> destroy(Entity entity) {
		return null;
	}

	@Override
	public Entity create() {
		if(hasControllable){
			EnemyShip es = new EnemyShip();
			list.add(es);
			return es;
		}else{
			hasControllable = true;
			Ship s = new Ship();
			list.add(s);
			return s;
		}
	}
}
