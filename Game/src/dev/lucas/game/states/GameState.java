package dev.lucas.game.states;

import java.awt.Graphics;

import dev.lucas.game.Handler;
import dev.lucas.game.worlds.World;

public class GameState extends State {
	
	// Initializes a World type
	private World world;

	// constructor for GameState, it takes in a handler object and  passes it to the super constructor, a new world along with a filepath, and sets the current world
	public GameState(Handler handler) {
		super(handler);
		
		world = new World(handler, "res/World/world.txt");
		handler.setWorld(world);
	}
	
	// ticks the world
	@Override
	public void tick() {
		world.tick();
	}
	
	// renders the world.
	@Override
	public void render(Graphics g) {
		world.render(g);
	}


}
