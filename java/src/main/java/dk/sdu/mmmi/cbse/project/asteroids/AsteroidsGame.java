package dk.sdu.mmmi.cbse.project.asteroids;

import static com.decouplink.Utilities.context;
import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.decouplink.Link;

import playn.core.Game;
import playn.core.GroupLayer;
import playn.core.ImageLayer;
import playn.core.PlayN;
import playn.core.util.Clock;
import dk.sdu.mmmi.cbse.project.common.Body;
import dk.sdu.mmmi.cbse.project.common.Entity;
import dk.sdu.mmmi.cbse.project.common.ImageData;
import dk.sdu.mmmi.cbse.project.common.World;
import dk.sdu.mmmi.cbse.project.gamespi.IGamePlugin;

public class AsteroidsGame extends Game.Default {

	private final Clock.Source clock = new Clock.Source(33);
	private GroupLayer layer;
	private List<IGamePlugin> sl;
	private KeyBoard kb;

	public AsteroidsGame() {
		super(33); // call update every 33ms (30 times per second)
		sl = ServiceSPI.locateAll(IGamePlugin.class);
	}

	public void init() {

		// adding keyboard
		PlayN.keyboard().setListener(new KeyBoard());
		
		// create a group layer to hold everything
		layer = graphics().rootLayer();

		// create and add background image layer
		layer.add(graphics().createImmediateLayer(new StarRenderer(clock)));

		// add Player
		for (IGamePlugin igp : sl) {
			igp.create();
			igp.create();
		}
	}

	@Override
	public void update(int delta) {
		clock.update(delta);
		List<Body> col_list = new ArrayList<Body>();
		for (Entity entity : context(World.Layer.ENTITYLAYER).all(Entity.class)) {
			Body body = context(entity).one(Body.class);
			if (body != null) {
				col_list.add(body);
			}
		}
		for (int i = 0; i < col_list.size() - 1; i++) {
			for (int j = i + 1; j < col_list.size(); j++) {
				testCollision(col_list.get(i), col_list.get(j));
			}
		}
		
		for (IGamePlugin igp : sl) {
			igp.process(delta);
		}

	}

	public boolean testCollision(Body b1, Body b2) {
		float dx;
		float dy;

		dx = b1.x - b2.x;
		dy = b1.y - b2.y;

		boolean isCollision = Math.sqrt((dx * dx) + (dy * dy)) <= b1.radius
				+ b2.radius;

//		if (isCollision) {
//			System.out.println(String.format(
//					"%s hits %s, dist=%s, totalRadius=%s", b1, b2,
//					Math.sqrt((dx * dx) + (dy * dy)), b1.radius + b2.radius));
//		}

		return isCollision;
	}

	private ImageLayer getImageLayer(Entity entity){
		
		ImageData img_date = context(entity).one(ImageData.class);
		Body body = context(entity).one(Body.class);
		ImgData alreadyLayer = context(entity).one(ImgData.class);
		
		if(body == null || img_date == null){
			return null;
		}

		if(alreadyLayer != null){
			return alreadyLayer.currentLayer;
		}
		ImageLayer img = graphics().createImageLayer(assets().getImageSync(img_date.path));
		img.setScale(img_date.scale);
		img.setAlpha(1f);
		ImgData imgd = new ImgData();
		imgd.currentLayer = img;
		context(entity).add(ImgData.class, imgd);
		layer.add(img);
		return img;
	}
	
	private class ImgData{
		ImageLayer currentLayer;
	}
	
	@Override
	public void paint(float alpha) {
		// the background automatically paints itself, so no need to do anything
		// here!
		clock.paint(alpha);
		
		for (Entity e : context(World.Layer.DEADLAYER).all(Entity.class)) {
			ImgData img_data = context(e).one(ImgData.class);
			if(img_data != null && img_data.currentLayer != null){
				img_data.currentLayer.destroy();
			}
			context(e).one(Link.class).dispose();
		}

		for (Entity e : context(World.Layer.ENTITYLAYER).all(Entity.class)) {
			ImageLayer img = getImageLayer(e);
			if(img == null){
				continue;
			}
			Body body = context(e).one(Body.class);
			img.setTranslation(body.x, body.y);
			img.setRotation(body.angle);
		}
	}
}
