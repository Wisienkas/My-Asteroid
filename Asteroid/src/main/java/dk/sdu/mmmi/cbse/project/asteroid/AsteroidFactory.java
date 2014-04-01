package dk.sdu.mmmi.cbse.project.asteroid;

import static com.decouplink.Utilities.context;

import com.decouplink.Link;

import dk.sdu.mmmi.cbse.project.common.World;
import dk.sdu.mmmi.cbse.project.gamespi.IGamePlugin;

public class AsteroidFactory implements IGamePlugin{
	
	
	@Override
	public void process(int delta) {
		for (Asteroid ast : context(World.getInstace()).all(Asteroid.class)) {
			ast.process();
		}
	}

	@Override
	public void create() {
		AsteroidFactory.createAsteroid(1f);
	}
	
	public static void createAsteroid(float scale){
		Asteroid a = new Asteroid(scale);
		Link<Asteroid> link = context(World.getInstace()).add(Asteroid.class, a);
		context(a).add(Link.class, link);
	}
	
	public static void removeAsteroid(Asteroid asteroid){
		Link<Asteroid> link = context(asteroid).one(Link.class);
		link.dispose();
	}
}
