package dev.lucas.game;

import dev.lucas.game.gfx.GameCamera;
import dev.lucas.game.input.KeyManager;
import dev.lucas.game.input.MouseManager;
import dev.lucas.game.worlds.World;

/** 
 * <i><b>Handler</b></i>
 * <pre>	public class Handler</pre>
 * <p>This class is used to allow other classes to interact or get information from other classes.</p>
 * @see {@link dev.lucas.game.Game Game} , {@link dev.lucas.game.worlds.World World} , {@link dev.lucas.game.gfx.GameCamera GameCamera} , {@link dev.lucas.game.input.KeyManager KeyManager} , {@link dev.lucas.game.MouseManager MouseManager}
 * **/
public class Handler {
	// Initializes a Game variable and World Variable.
	private Game game;
	private World world;
	
	/** 
	 * <i><b>Handler</b></i>
	 * <pre>	public Handler(Game game)</pre>
	 * <p>The Handler constructor takes in a Game object and stores it.</p>
	 * @param Game
	 * @see {@link dev.lucas.game.Game Game}**/
	public Handler(Game game) {
		this.game = game;
	}
	
	// Helpful getters and setters for various important classes.
	/** 
	 * <i><b>getGameCamera</b></i>
	 * <pre>	public GameCamera getGameCamera()</pre>
	 * <p>Gets the GameCamera from the Game class using the getGameCamera method.</p>
	 * @param None
	 * @return GameCamera
	 * @see {@link dev.lucas.game.gfx.GameCamera GameCamera} , {@link dev.lucas.game.Game Game}
	 * **/
	public GameCamera getGameCamera(){
		return game.getGameCamera();
	}
	
	/** 
	 * <i><b>getKeyManager</b></i>
	 * <pre>	public KeyManager getKeyManager()</pre>
	 * <p>Gets the KeyManager from the Game class using the getKeyManager method.</p>
	 * @param None
	 * @return KeyManager
	 * @see {@link dev.lucas.game.input.KeyManager KeyManager} , {@link dev.lucas.game.Game Game}
	 * **/
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	/** 
	 * <i><b>getMouseManager</b></i>
	 * <pre>	public MouseManager getMouseManager()</pre>
	 * <p>Gets the MouseManager from the Game class using the getMouseManager method.</p>
	 * @param None
	 * @return MouseManager
	 * @see {@link dev.lucas.game.input.MouseManager MouseManager} , {@link dev.lucas.game.Game Game}
	 * **/
	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}
	
	/** 
	 * <i><b>getWidth</b></i>
	 * <pre>	public int getWidth()</pre>
	 * <p>Gets the width from the Game class using the getWidth method.</p>
	 * @param None
	 * @return int
	 * @see {@link dev.lucas.game.Game Game}
	 * **/
	public int getWidth() {
		return game.getWidth();
	}
	
	/** 
	 * <i><b>getHeight</b></i>
	 * <pre>	public int getHeight()</pre>
	 * <p>Gets the height from the Game class using the getHeight method.</p>
	 * @param None
	 * @return int
	 * @see {@link dev.lucas.game.Game Game}
	 * **/
	public int getHeight() {
		return game.getHeight();
	}
	
	/** 
	 * <i><b>getGame</b></i>
	 * <pre>	public Game getGame()</pre>
	 * <p>Gets the Game Object stored in the variable 'game'.</p>
	 * @param None
	 * @return Game
	 * @see {@link dev.lucas.game.Game Game}
	 * **/
	public Game getGame() {
		return game;
	}

	/** 
	 * <i><b>setGame</b></i>
	 * <pre>	public void setGame(Game game)</pre>
	 * <p>Sets the 'game' variables's value to the passed in Game Object.</p>
	 * @param Game
	 * @return None
	 * @see {@link dev.lucas.game.Game Game}
	 * **/
	public void setGame(Game game) {
		this.game = game;
	}

	/** 
	 * <i><b>getWorld</b></i>
	 * <pre>	public World getWorld()</pre>
	 * <p>Gets the World object stores in the variable 'world'.</p>
	 * @param None
	 * @return World
	 * @see {@link dev.lucas.game.worlds.World World }
	 * **/
	public World getWorld() {
		return world;
	}

	/** 
	 * <i><b>setWorld</b></i>
	 * <pre>	public void setWorld(World world)</pre>
	 * <p>Sets the 'world' variable to the passed int World Object.</p>
	 * @param World world
	 * @return None
	 * @see {@link dev.lucas.game.worlds.World World}
	 * **/
	public void setWorld(World world) {
		this.world = world;
	}
}
