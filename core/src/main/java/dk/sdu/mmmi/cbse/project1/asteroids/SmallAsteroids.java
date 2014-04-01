package dk.sdu.mmmi.cbse.project1.asteroids;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

import java.util.Random;

import playn.core.Image;
import dk.sdu.mmmi.cbse.project1.engine.Body;
import dk.sdu.mmmi.cbse.project1.engine.Entity;
import dk.sdu.mmmi.cbse.project1.engine.Health;
import dk.sdu.mmmi.cbse.project1.engine.Physics;
import dk.sdu.mmmi.cbse.project1.event.Events.DestroyEvent;
import dk.sdu.mmmi.cbse.project1.event.Events.IEntityListener;
import dk.sdu.mmmi.cbse.project1.event.Events.UpdateEvent;

public class SmallAsteroids extends Entity{

	
	public SmallAsteroids(float x, float y){
		Image shipImage = assets().getImageSync("images/Asteroid.png");
		view = graphics().createImageLayer(shipImage);
		view.setOrigin(x, y);
		
		view.setHeight(view.height() / 4);
		view.setWidth(view.width() / 4);
		
		body = new Body(this);
		body.x = graphics().width();
		body.y = graphics().height();
		body.radius = shipImage.height() / 4;

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
		if(health.hits < -20){
			System.out.println("DEAD");
			for (IEntityListener listener : listenerList) {
				listener.onDetroy(new DestroyEvent(SmallAsteroids.this));
			}
			listenerList.clear();
		}
	}
}
