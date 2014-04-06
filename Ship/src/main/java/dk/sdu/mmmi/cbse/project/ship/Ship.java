package dk.sdu.mmmi.cbse.project.ship;

import static com.decouplink.Utilities.context;


import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.decouplink.Link;

import dk.sdu.mmmi.cbse.project.common.Body;
import dk.sdu.mmmi.cbse.project.common.Entity;
import dk.sdu.mmmi.cbse.project.common.Health;
import dk.sdu.mmmi.cbse.project.common.ImageData;
import dk.sdu.mmmi.cbse.project.common.KeyAction;
import dk.sdu.mmmi.cbse.project.common.Keys;
import dk.sdu.mmmi.cbse.project.common.Physics;
import dk.sdu.mmmi.cbse.project.common.World;
@SuppressWarnings("restriction")
public class Ship extends Entity implements IShip{
	
	private Entity me;
	private ImageData img;
	private Physics physics;
	private Health health;
	private Body body;
	private KeyAdapter ka;
	private Link<Entity> link;
	private List<IShip> ammoList = new ArrayList<IShip>();
	
	public Ship(){
		Random rnd = new Random();
		body = new Body();
		body.angle = 0;
		body.radius = 20;
		body.x = rnd.nextInt(400);
		body.y = rnd.nextInt(400);
		
		health = new Health();
		health.hits = 5;
		
		physics = new Physics();
		physics.drag = 0.9f;
		physics.velocityX = 1f;
		physics.velocityY = 1f;
		
		img = new ImageData();
		img.path = "images/Ship.png";
		img.scale = 1;
		
		me = new Entity();
		
		link = context(World.Layer.ENTITYLAYER).add(Entity.class, me);
		context(me).add(Body.class, body);
		context(me).add(Health.class, health);
		context(me).add(Physics.class, physics);
		context(me).add(ImageData.class, img);
	}


	public void process() {
		for (IShip bul : ammoList ) {
			bul.process();
		}
		Keys keys = context(World.Layer.ENTITYLAYER).one(Keys.class);
		if(keys.keys.get(KeyAction.A)){
			body.angle-=0.1f;
		}
		if(keys.keys.get(KeyAction.D)){
			body.angle+= 0.1f;
		}
		if(keys.keys.get(KeyAction.W)){
			accelerate(1f);
		}
		if(keys.keys.get(KeyAction.S)){
			accelerate(-1f);
		}
		if(keys.keys.get(KeyAction.SPACE)){
			fire();
		}
		body.x += physics.velocityX;
		body.y += physics.velocityY;
		physics.velocityX *= physics.drag;
		physics.velocityY *= physics.drag;
	}

	private void fire() {
		ammoList.add(new Bullet(body.angle, body.x + Math.cos(body.angle) * 20, body.y + Math.sin(body.angle) * 20));
	}

	private void accelerate(float f) {
		physics.velocityX += Math.cos(body.angle) * f;
		physics.velocityY += Math.sin(body.angle) * f;
	}
}
