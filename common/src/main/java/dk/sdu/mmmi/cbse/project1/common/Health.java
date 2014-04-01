package dk.sdu.mmmi.cbse.project1.common;


public class Health {
	public Integer hits;

	public void hit(Entity source, Integer damage) {
		hits -= damage;
	}
}
