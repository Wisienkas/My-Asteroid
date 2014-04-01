package dk.sdu.mmmi.cbse.project1.asteroids;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

import java.util.Random;

import playn.core.Image;
import dk.sdu.mmmi.cbse.project1.engine.Body;
import dk.sdu.mmmi.cbse.project1.engine.Entity;
import dk.sdu.mmmi.cbse.project1.engine.Health;
import dk.sdu.mmmi.cbse.project1.engine.IEntity;
import dk.sdu.mmmi.cbse.project1.engine.Physics;
import dk.sdu.mmmi.cbse.project1.event.Events.CreateEvent;
import dk.sdu.mmmi.cbse.project1.event.Events.DestroyEvent;
import dk.sdu.mmmi.cbse.project1.event.Events.IEntityListener;
import dk.sdu.mmmi.cbse.project1.event.Events.UpdateEvent;

public class LargeAsteroid extends Entity{
	
	private int detonate;
	
	public LargeAsteroid(){
		
		Image shipImage = assets().getImageSync("images/Asteroid.png");
		view = graphics().createImageLayer(shipImage);
		view.setOrigin(shipImage.width() - 100, shipImage.height() - 100);

		body = new Body(this);
		body.x = graphics().width();
		body.y = graphics().height();
		body.radius = shipImage.height();

		physics = new Physics(this);
		physics.drag = 0.9;

		health = new Health(this);
		health.hits = 1;
		
		Random rnd = new Random();
		body.angle = (rnd.nextFloat() % ((float)Math.PI * 2));
		
	}

	@Override
	public void onUpdate(UpdateEvent event) {
		super.onUpdate(event);
		
		physics.thrust(1.0d);
		if(health.hits < 0){
			for (IEntityListener listener : listenerList) {
				listener.onDetroy(new DestroyEvent(LargeAsteroid.this));
			}
			listenerList.clear();
		}
	}
	
	
	
	@Override
	public void onDetroy(DestroyEvent e) {
		System.out.println("Asteroid destreoyed");
		
		AsteroidsGame.getInstance().createSomething(new SmallAsteroids(body.x, body.y));
		AsteroidsGame.getInstance().createSomething(new SmallAsteroids(body.x - 100, body.y - 100));
		super.onDetroy(e);
	}
	
	
}
