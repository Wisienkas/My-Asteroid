package dk.sdu.mmmi.cbse.java;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import playn.core.PlayN;
import playn.java.JavaPlatform;
import dk.sdu.mmmi.cbse.project.asteroids.AsteroidsGame;
import dk.sdu.mmmi.cbse.project.common.World;

public class AsteroidsJava implements AsteroidsGameService {

	@Override
	public void startGame() {
		JavaPlatform.Config config = new JavaPlatform.Config();
		// use config to customize the Java platform, if needed
		config.appName = "Asteroids";
		config.width = (int) World.SCREENWITDH;
		config.height = (int) World.SCREENHEIGHT;
		JavaPlatform.register(config);
		PlayN.run(new AsteroidsGame());
	}
}
