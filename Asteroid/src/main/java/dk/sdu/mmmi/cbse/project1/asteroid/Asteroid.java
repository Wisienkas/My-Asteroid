package dk.sdu.mmmi.cbse.project1.asteroid;

import static com.decouplink.Utilities.context;

import java.util.Random;

import com.decouplink.Link;

import dk.sdu.mmmi.cbse.project1.common.Body;
import dk.sdu.mmmi.cbse.project1.common.Entity;
import dk.sdu.mmmi.cbse.project1.common.Health;
import dk.sdu.mmmi.cbse.project1.common.ImageData;
import dk.sdu.mmmi.cbse.project1.common.Physics;
import dk.sdu.mmmi.cbse.project1.common.World;

public abstract class Asteroid{
	
	private Entity entity;
	public Asteroid(){
		entity = new Entity();
		
		Random rnd = new Random();
		
		Body body = new Body();
		body.x = (float) rnd.nextInt((int)World.SCREENWITDH);
		body.y = (float) rnd.nextInt((int)World.SCREENHEIGHT);
		body.angle = (float) (((double) rnd.nextInt(1000) * Math.PI) / 500);
		context(entity).add(Body.class, body);
		
		ImageData img = new ImageData();
		img.path = "images/Asteroid.png";
		img.scale = 1f;
		context(entity).add(ImageData.class, img);
		
		Health health = new Health();
		health.hits = 1;
		context(entity).add(Health.class, health);
		
		Physics physics = new Physics();
		physics.drag = 1f;
		physics.velocityX = (float)(rnd.nextInt(5) - 2);
		physics.velocityX = (float)(rnd.nextInt(5) - 2);
		
		Link<Entity> link = context(World.getInstace()).add(Entity.class, entity);
		context(entity).add(Link.class, link);
	}
	
	public void process(){
		// imports
		Body body = context(entity).one(Body.class);
		Physics physics = context(entity).one(Physics.class);
		
		body.x = physics.velocityX;
		body.y = physics.velocityY;
	}
	
	
	
}
