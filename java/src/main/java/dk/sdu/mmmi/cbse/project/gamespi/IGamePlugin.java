package dk.sdu.mmmi.cbse.project.gamespi;

import java.util.List;

import dk.sdu.mmmi.cbse.project.common.Entity;

public interface IGamePlugin {
	
	void process(int delta);
	void create();
	
}
