package dev.lucas.game.states;

import java.awt.Graphics;

import dev.lucas.game.Handler;
import dev.lucas.game.worlds.World;

public class GameState extends State {
	
	private World world;

	
	public GameState(Handler handler) {
		super(handler);
		
		world = new World(handler, "res/World/world.txt");
		handler.setWorld(world);
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}


}
