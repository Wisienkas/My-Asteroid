package dk.sdu.mmmi.cbse.project1.ship;

import static com.decouplink.Utilities.context;

import com.decouplink.Link;

import dk.sdu.mmmi.cbse.project1.common.Body;
import dk.sdu.mmmi.cbse.project1.common.Entity;
import dk.sdu.mmmi.cbse.project1.common.Health;
import dk.sdu.mmmi.cbse.project1.common.ImageData;
import dk.sdu.mmmi.cbse.project1.common.Physics;
import dk.sdu.mmmi.cbse.project1.common.World;

public class Bullet extends Entity implements IShip{

	private Body body;
	private ImageData img;
	private Physics physics;
	private Health health;
	private int lifetime;
	private Link<Entity> link;

	public Bullet(float angle, double x, double y) {
		body = new Body();
		body.angle = angle;
		body.radius = 5;
		body.x = (float) x;
		body.y = (float) y;
		
		img = new ImageData();
		img.scale = 1;
		img.path = "images/Bullet.png";
		
		physics = new Physics();
		physics.velocityX = (float)Math.cos(angle) * 5;
		physics.velocityY = (float)Math.sin(angle) * 5;
		
		health = new Health();
		health.hits = 1;
		
		lifetime = 50;
		
		link = context(World.getInstace()).add(Entity.class, this);
		context(this).add(Body.class, body);
		context(this).add(ImageData.class, img);
		context(this).add(Physics.class, physics);
		context(this).add(Health.class, health);
		
	}

	@Override
	public void process() {
		lifetime--;
		body.x += physics.velocityX;
		body.y += physics.velocityY;
		if(lifetime < 1){
			link.dispose();
		}
	}

}
