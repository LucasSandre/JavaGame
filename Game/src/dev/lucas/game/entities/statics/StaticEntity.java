package dev.lucas.game.entities.statics;

import dev.lucas.game.Handler;
import dev.lucas.game.entities.Entity;


/** 
 * <i><b>StaticEntity</b></i>
 * <pre> public class StaticEntity</pre>
  * <p>This class defines all the necessary methods and variables for a StaticEntity</p>
 * @see {@link dev.game.lucas.entities.Entity Entity}
 * **/
public abstract class StaticEntity extends Entity {
	
	// The Class Constructor takes in a handler an x and y, width and height
		//passes to the Entity constructor
	
	/**
	 * <i><b> StaticEntity</b></i>
	 * <pre>	public StaticEntity(Handler handler,
	 *                              float x,
	 *                              float y,
	 *                              int width,
	 *                              int height)</pre>
	 * <p>The class constructor takes the parsed values and passe them to the super constructor.</p>
	 * @param Handler handler,
	 * @param Float x,
	 * @param Float y,
	 * @param Int width,
	 * @param Int height
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler,x,y,width,height);
	}
	

}
