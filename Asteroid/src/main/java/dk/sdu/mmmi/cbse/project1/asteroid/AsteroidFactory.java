package dk.sdu.mmmi.cbse.project1.asteroid;

import java.util.ArrayList;
import java.util.List;

import dk.sdu.mmmi.cbse.project1.common.Entity;
import dk.sdu.mmmi.cbse.project1.gamespi.IGamePlugin;

public class AsteroidFactory implements IGamePlugin{
	
	List<Asteroid> list = new ArrayList<Asteroid>();
	
	@Override
	public void process(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Entity> destroy(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity create() {
		// TODO Auto-generated method stub
		return null;
	}

}
