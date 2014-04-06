package dk.sdu.mmmi.cbse.project.common;

public class World {

	public enum Layer{
		ENTITYLAYER, DEADLAYER
	}
	
	private static World INSTANCE;
	
	public static float SCREENWITDH = 1000;
	public static float SCREENHEIGHT = 800;
	
	
	public static World getInstace(){
		if(INSTANCE == null){
			INSTANCE = new World();
		}
		return INSTANCE;
	}
	
	private World(){
		
	}
}
