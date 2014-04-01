package dk.sdu.mmmi.cbse.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;
import dk.sdu.mmmi.cbse.project.asteroids.AsteroidsGame;
import dk.sdu.mmmi.cbse.project.common.World;

public class AsteroidsJava {

  public static void main(String[] args) {
    JavaPlatform.Config config = new JavaPlatform.Config();
    // use config to customize the Java platform, if needed  
    config.appName = "Asteroids";
    config.width = (int) World.SCREENWITDH;
    config.height = (int) World.SCREENHEIGHT;
    JavaPlatform.register(config);
    PlayN.run(new AsteroidsGame());
  }
}
