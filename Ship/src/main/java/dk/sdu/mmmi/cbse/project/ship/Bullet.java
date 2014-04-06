package dk.sdu.mmmi.cbse.project.ship;

import static com.decouplink.Utilities.context;

import com.decouplink.Link;

import dk.sdu.mmmi.cbse.project.common.Body;
import dk.sdu.mmmi.cbse.project.common.Entity;
import dk.sdu.mmmi.cbse.project.common.Health;
import dk.sdu.mmmi.cbse.project.common.ImageData;
import dk.sdu.mmmi.cbse.project.common.Physics;
import dk.sdu.mmmi.cbse.project.common.World;

@SuppressWarnings("restriction")
public class Bullet implements IShip{

	private int lifetime;
	private Link<Entity> link;
	Link<ImageData> imgLink;

	public Bullet(float angle, double x, double y) {
		Body body = new Body();
		body.angle = angle;
		body.radius = 5;
		body.x = (float) x;
		body.y = (float) y;
		
		ImageData img = new ImageData();
		img.scale = 1;
		img.path = "images/Bullet.png";
		
		Physics physics = new Physics();
		physics.velocityX = (float)Math.cos(angle) * 5;
		physics.velocityY = (float)Math.sin(angle) * 5;
		
		Health health = new Health();
		health.hits = 1;
		
		lifetime = 50;
		
		Entity entity = new Entity();
		
		link = context(World.Layer.ENTITYLAYER).add(Entity.class, entity);
		context(entity).add(Body.class, body);
		context(entity).add(ImageData.class, img);
		context(entity).add(Physics.class, physics);
		context(entity).add(Health.class, health);
		context(entity).add(Link.class, link);
		
	}

	public void process() {
		Entity entity = link.getDestination();
		Body body = context(entity).one(Body.class);
		Physics physics = context(entity).one(Physics.class);
		lifetime--;
		body.x += physics.velocityX;
		body.y += physics.velocityY;
		if(lifetime < 1){
			context(World.Layer.DEADLAYER).add(Entity.class, entity);
		}
	}

}
