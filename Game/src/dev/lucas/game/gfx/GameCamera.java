package dev.lucas.game.gfx;

import dev.lucas.game.Handler;
import dev.lucas.game.entities.Entity;
import dev.lucas.game.tiles.Tile;

/** 
 * <i><b>GameCamera</b></i>
 * <pre> public class GameCamera</pre>
 * <p>This class defines all the necessary methods and variables for a GameCamera.</p>
 * **/
public class GameCamera {

	// Initializes private floating points
	private float x_offset,y_offset;
	
	// Initializes a handler variable
	private Handler handler;
	
	// GameCamera constructor, takes in a handler, and a x and y offset to start the screen at, and sets the respective variables.
	
	/**
	 * <i><b>GameCamera</b></i>
	 * <pre>	public GameCamera(Handler handler, float x_offset, float y_offset)</pre>
	 * <p>The class constructor saves the parsed values.</p>
	 * @param Handler handler,
	 * @param Float x_offset,
	 * @param Float y_offset
	 * **/
	public GameCamera(Handler handler, float x_offset, float y_offset){
		this.handler = handler;
		this.x_offset = x_offset;
		this.y_offset = y_offset;
	}

	/**
	 * <i><b>checkBlankSpace</b></i>
	 * <pre>	public void checkBlankSpace()</pre>
	 * <p>this method checks for empty space on the screen and tries to move the camera to get rid of it, by having the centered entity no longer be centered.</p>
	 * @param None
	 * @return void
	 * @see {@link dev.lucas.game.tiles.Tile Tile}
	 * **/
	public void checkBlankSpace() {
		if (x_offset < 0) {
			x_offset = 0;
		}
		else if (x_offset > handler.getWorld().getWidth()*Tile.TILEWIDTH - handler.getWidth()){
			x_offset = handler.getWorld().getWidth()*Tile.TILEWIDTH - handler.getWidth();
		}
		
		if (y_offset < 0) {
			y_offset = 0;
		}
		else if (y_offset > handler.getWorld().getHeight()*Tile.TILEHEIGHT - handler.getHeight()){
			y_offset = handler.getWorld().getHeight()*Tile.TILEHEIGHT - handler.getHeight();
		}
	}
	
	/**
	 * <i><b>centerOnEntity</b></i>
	 * <pre>	public void centerOnEntity(Entity e)</pre>
	 * <p>This method takes in an entity and tries to center it on the screen unless if empty space occurs.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.entities.Entity Entity}
	 * **/
	public void centerOnEntity(Entity e) {
		x_offset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2 ;
		y_offset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace();
	}
	
	/**
	 * <i><b>move</b></i>
	 * <pre>	public void move(float x_amt, float y_amt)</pre>
	 * <p>This method moves the game camera around by floats passes into it, after it checks for empty space.</p>
	 * @param
	 * @return void
	 * **/
	public void move(float x_amt, float y_amt){
		x_offset += x_amt;
		y_offset += y_amt;
		checkBlankSpace();
	}
	
	// getters and setters
	/**
	 * <i><b>getX_offset</b></i>
	 * <pre>	public float getX_offset()</pre>
	 * <p>Gets the x offset of the GameCamera.</p>
	 * @param None
	 * @return float
	 * **/
	public float getX_offset() {
		return x_offset;
	}

	/**
	 * <i><b>setX_offset</b></i>
	 * <pre>	public void setX_offset(float x_offset)</pre>
	 * <p>Sets the GameCamera's x offset.</p>
	 * @param
	 * @return void
	 * **/
	public void setX_offset(float x_offset) {
		this.x_offset = x_offset;
	}

	/**
	 * <i><b>getY_offset</b></i>
	 * <pre>	public float getY_offset()</pre>
	 * <p>Gets the y offset of the GameCamera.</p>
	 * @param None
	 * @return float
	 * **/
	public float getY_offset() {
		return y_offset;
	}

	/**
	 * <i><b>setY_offset</b></i>
	 * <pre>	public void setY_offset(float y_offset)</pre>
	 * <p>Sets the GameCamera's y offset.</p>
	 * @param
	 * @return void
	 * **/
	public void setY_offset(float y_offset) {
		this.y_offset = y_offset;
	}
	
}
