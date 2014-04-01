package dk.sdu.mmmi.cbse.project1.ship;

import static com.decouplink.Utilities.context;

import java.util.Random;

import com.decouplink.Link;

import dk.sdu.mmmi.cbse.project1.common.Body;
import dk.sdu.mmmi.cbse.project1.common.Entity;
import dk.sdu.mmmi.cbse.project1.common.Health;
import dk.sdu.mmmi.cbse.project1.common.ImageData;
import dk.sdu.mmmi.cbse.project1.common.Physics;
import dk.sdu.mmmi.cbse.project1.common.World;

public class EnemyShip extends Entity implements IShip{
	
	private int moves;
	private boolean moveLeft;
	private Random rnd;
	private Body body;
	private Health health;
	private Physics physics;
	private ImageData img;
	private Link<Entity> link;
	
	public EnemyShip(){
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
		img.path = "images/EnemyShip.png";
		img.scale = 1;
		
		link = context(World.getInstace()).add(Entity.class, this);
		context(this).add(Body.class, body);
		context(this).add(Health.class, health);
		context(this).add(Physics.class, physics);
		context(this).add(ImageData.class, img);
		
	}

	@Override
	public void process() {
		if(rnd == null){
			rnd = new Random();
		}
		
		if(moves == 0){
			moves = rnd.nextInt(20) + rnd.nextInt(30);
			moveLeft = rnd.nextBoolean();
		}
		if(moveLeft){
			body.angle += 0.1f;
		}else{
			body.angle -= 0.1f;
		}
		
		if((rnd.nextInt(6) + 1) % 2 == 0){
			physics.velocityX = (float)Math.cos(body.angle) * 2;
			physics.velocityY = (float)Math.sin(body.angle) * 2;
		}
		body.x += physics.velocityX;
		body.y += physics.velocityY;
		moves--;
	}
	
	
}
