package dk.sdu.mmmi.cbse.project.common;


public class Health {
	public Integer hits;

	public void hit(Entity source, Integer damage) {
		hits -= damage;
	}
}
