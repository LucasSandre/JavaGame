package dev.lucas.game.states;

import java.awt.Graphics;

import dev.lucas.game.Handler;
import dev.lucas.game.worlds.World;

/** 
 * <i><b>GameState</b></i>
 * <pre> public class GameState extends State</pre>
 * <p>This class defines all the necessary methods and variables for a GameState.</p>
 * @see {@link dev.lucas.game.states.State State}
 * **/
public class GameState extends State {
	
	// Initializes a World type
	private World world;

	// constructor for GameState, it takes in a handler object and  passes it to the super constructor, a new world along with a filepath, and sets the current world
	/**
	 * <i><b>GameState</b></i>
	 * <pre>	public GameState(Handler handler)</pre>
	 * <p>The class constructor send the parsed handler to the super constructor, creates a world and sets the world in the Handler class to the new one. </p>
	 * @param
	 * @see {@link dev.lucas.game.Handler Handler} , {@link dev.lucas.game.worlds.World World}
	 * **/
	public GameState(Handler handler) {
		super(handler);
		
		world = new World(handler, "res/World/world.txt");
		handler.setWorld(world);
	}
	
	// ticks the world
	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This method ticks the world.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.worlds.World World}
	 * **/
	@Override
	public void tick() {
		world.tick();
	}
	
	// renders the world.
	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>This method renders the world.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.worlds.World World}
	 * **/
	@Override
	public void render(Graphics g) {
		world.render(g);
	}


}
