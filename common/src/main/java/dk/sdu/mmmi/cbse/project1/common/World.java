package dk.sdu.mmmi.cbse.project1.common;

public class World {

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
