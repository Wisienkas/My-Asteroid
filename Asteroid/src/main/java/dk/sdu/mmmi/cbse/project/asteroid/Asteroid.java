package dk.sdu.mmmi.cbse.project.asteroid;

import static com.decouplink.Utilities.context;

import java.util.Random;

import com.decouplink.Link;

import dk.sdu.mmmi.cbse.project.common.Body;
import dk.sdu.mmmi.cbse.project.common.Entity;
import dk.sdu.mmmi.cbse.project.common.Health;
import dk.sdu.mmmi.cbse.project.common.ImageData;
import dk.sdu.mmmi.cbse.project.common.Physics;
import dk.sdu.mmmi.cbse.project.common.World;

public class Asteroid{
	
	private Entity entity;
	private float scale;
	private static final float imgRadius = 80 / 2;
	private static final float Asteroid_lower_bound = 0.2f;
	
	public Asteroid(float scale){
		entity = new Entity();
		Link<Entity> link = context(World.Layer.ENTITYLAYER).add(Entity.class, entity);
		
		context(entity).add(Link.class, link);
		
		Random rnd = new Random();
		
		Body body = new Body();
		body.x = (float) rnd.nextInt((int)World.SCREENWITDH);
		body.y = (float) rnd.nextInt((int)World.SCREENHEIGHT);
		body.radius = imgRadius * scale;
		
		body.angle = (float) (((double) rnd.nextInt(1000) * Math.PI) / 500);
		context(entity).add(Body.class, body);
		
		ImageData img = new ImageData();
		img.path = "images/Asteroid.png";
		img.scale = 1f;
		scale = img.scale;
		context(entity).add(ImageData.class, img);
		
		Health health = new Health();
		health.hits = 1;
		context(entity).add(Health.class, health);
		
		Physics physics = new Physics();
		physics.drag = 1f;
		physics.velocityX = (float)(Math.random() * 4) + 2;
		physics.velocityY = (float)(Math.random() * 4) + 2;
		if(rnd.nextBoolean()){
			physics.velocityX *= -1;
		}
		if(rnd.nextBoolean()){
			physics.velocityY *= -1;
		}
		context(entity).add(Physics.class, physics);
	}
	
	public void process(){
		// imports
		Body body = context(entity).one(Body.class);
		Physics physics = context(entity).one(Physics.class);
		Health health = context(entity).one(Health.class);
		
		body.x += physics.velocityX;
		body.y += physics.velocityY;

		if(body.x < (body.radius * -1) - 1){body.x = World.SCREENWITDH + body.radius;}
		if(body.y < (body.radius * -1) - 1){body.y = World.SCREENHEIGHT + body.radius;}
		if(body.x > (body.radius) + World.SCREENWITDH){body.x = body.radius * -1;}
		if(body.y > (body.radius) + World.SCREENHEIGHT){body.y = body.radius * -1;}

		if(health.hits < 1){
			if(scale / 2 >= Asteroid_lower_bound){
				AsteroidFactory.createAsteroid(scale / 2);
				AsteroidFactory.createAsteroid(scale / 2);
			}
			AsteroidFactory.removeAsteroid(this);
		}
	}
}
