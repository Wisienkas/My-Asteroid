package dk.sdu.mmmi.cbse.project1.gamespi;

import java.util.List;

import dk.sdu.mmmi.cbse.project1.*;
import dk.sdu.mmmi.cbse.project1.common.Entity;

public interface IGamePlugin {
	
	void process(int delta);
	List<Entity> destroy(Entity entity);
	Entity create();
	
}
