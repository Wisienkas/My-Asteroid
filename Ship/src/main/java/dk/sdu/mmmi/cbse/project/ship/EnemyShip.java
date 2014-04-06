package dk.sdu.mmmi.cbse.project.ship;

import static com.decouplink.Utilities.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.decouplink.Link;

import dk.sdu.mmmi.cbse.project.common.Body;
import dk.sdu.mmmi.cbse.project.common.Entity;
import dk.sdu.mmmi.cbse.project.common.Health;
import dk.sdu.mmmi.cbse.project.common.ImageData;
import dk.sdu.mmmi.cbse.project.common.Physics;
import dk.sdu.mmmi.cbse.project.common.World;

@SuppressWarnings("restriction")
public class EnemyShip extends Entity implements IShip{
	
	private int moves;
	private boolean moveLeft;
	private Random rnd;
	private Body body;
	private Health health;
	private Physics physics;
	private ImageData img;
	private Link<Entity> link;
	private List<IShip> ammoList = new ArrayList<IShip>();
	
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
		
		link = context(World.Layer.ENTITYLAYER).add(Entity.class, this);
		context(this).add(Body.class, body);
		context(this).add(Health.class, health);
		context(this).add(Physics.class, physics);
		context(this).add(ImageData.class, img);
		
	}

	public void process() {
		for (IShip bul : ammoList) {
			bul.process();
		}
		
		if(rnd == null){
			rnd = new Random();
		}
		
		if(moves == 0){
			moves = rnd.nextInt(20) + rnd.nextInt(30) + 20;
			moveLeft = rnd.nextBoolean();
		}
		if(moveLeft){
			body.angle += 0.03f;
		}else{
			body.angle -= 0.03f;
		}
		
		physics.velocityX = (float)Math.cos(body.angle) * 4;
		physics.velocityY = (float)Math.sin(body.angle) * 4;
		
		body.x += physics.velocityX;
		body.y += physics.velocityY;
		moves--;
	}
	
	
}
