package dk.sdu.mmmi.cbse.project.asteroids;

import static com.decouplink.Utilities.context;

import dk.sdu.mmmi.cbse.project.common.KeyAction;
import dk.sdu.mmmi.cbse.project.common.Keys;
import dk.sdu.mmmi.cbse.project.common.World;

import playn.core.Key;
import playn.core.Keyboard;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.TypedEvent;

public class KeyBoard extends Keyboard.Adapter{
	
	public KeyBoard(){
		Keys keys = context(World.getInstace()).one(Keys.class);
		if(keys == null){
			keys = new Keys();
			keys.keys.put(KeyAction.A, false);
			keys.keys.put(KeyAction.S, false);
			keys.keys.put(KeyAction.D, false);
			keys.keys.put(KeyAction.W, false);
			keys.keys.put(KeyAction.SPACE, false);
			context(World.getInstace()).add(Keys.class, keys);
		}
	}
	
	@Override
	public void onKeyDown(Event e) {
		System.out.println("pressed: " + e.key());
		Keys keys = context(World.getInstace()).one(Keys.class);
		if(e.key().equals(Key.A)){
			keys.keys.put(KeyAction.A, true);
		}else if(e.key().equals(Key.S)){
			keys.keys.put(KeyAction.S, true);
		}else if(e.key().equals(Key.W)){
			keys.keys.put(KeyAction.W, true);
		}else if(e.key().equals(Key.D)){
			keys.keys.put(KeyAction.D, true);
		}else if(e.key().equals(Key.SPACE)){
			keys.keys.put(KeyAction.SPACE, true);
		}
	}

	@Override
	public void onKeyUp(Event e) {
		Keys keys = context(World.getInstace()).one(Keys.class);
		if(e.key().equals(Key.A)){
			keys.keys.put(KeyAction.A, false);
		}else if(e.key().equals(Key.S)){
			keys.keys.put(KeyAction.S, false);
		}else if(e.key().equals(Key.W)){
			keys.keys.put(KeyAction.W, false);
		}else if(e.key().equals(Key.D)){
			keys.keys.put(KeyAction.D, false);
		}else if(e.key().equals(Key.SPACE)){
			keys.keys.put(KeyAction.SPACE, false);
		}
	}
	
}
