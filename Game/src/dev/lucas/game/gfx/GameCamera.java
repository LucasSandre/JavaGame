package dev.lucas.game.gfx;

import dev.lucas.game.Handler;
import dev.lucas.game.entities.Entity;
import dev.lucas.game.tiles.Tile;

public class GameCamera {

	// Initializes private floating points
	private float x_offset,y_offset;
	
	// Initializes a handler variable
	private Handler handler;
	
	// GameCamer constructor, takes in a handler, and a x and y offset to start the screen at, and sets the respective variables.
	public GameCamera(Handler handler,float x_offset,float y_offset){
		this.handler = handler;
		this.x_offset = x_offset;
		this.y_offset = y_offset;
	}

	// this method checks for empty space on the screen and tries to move the camera to get rid of it, by having the centerd entity no longer be centered.
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
	
	// takes in an entity and tries to center it on the screen unless if empty space occurs.
	public void centerOnEntity(Entity e) {
		x_offset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2 ;
		y_offset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace();
	}
	
	// A meatod that moves the game camera around by floats passes into it and checks for empty space.
	public void move( float x_amt,float y_amt){
		x_offset += x_amt;
		y_offset += y_amt;
		checkBlankSpace();
	}
	// gettes and setters
	public float getX_offset() {
		return x_offset;
	}

	public void setX_offset(float x_offset) {
		this.x_offset = x_offset;
	}

	public float getY_offset() {
		return y_offset;
	}

	public void setY_offset(float y_offset) {
		this.y_offset = y_offset;
	}
	
}
