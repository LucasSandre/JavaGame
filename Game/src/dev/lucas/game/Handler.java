package dev.lucas.game;

import dev.lucas.game.gfx.GameCamera;
import dev.lucas.game.input.KeyManager;
import dev.lucas.game.input.MouseManager;
import dev.lucas.game.worlds.World;

public class Handler {
	// Initializes a Game variable and World Variable.
	private Game game;
	private World world;
	
	// the handler constructor takes in a game object and sets the game variable equal to it.
	public Handler(Game game) {
		this.game = game;
	}
	
	// Helpful getters and setters for various important classes.
	public GameCamera getGameCamera(){
		return game.getGameCamera();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
