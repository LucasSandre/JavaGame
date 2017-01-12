package dev.lucas.game.entities.creatures;

import dev.lucas.game.Handler;
import dev.lucas.game.entities.Entity;
import dev.lucas.game.tiles.Tile;

public abstract class Creature extends Entity {

	// Sets some final values necessary for momentum and size.
	public static final float MAX_SPEED = 7.5f;
	public static final float SPEED_CHANGE = 0.2f;
	public static final int DEFAULT_CREATURE_WIDTH = 64,
							DEFAULT_CREATURE_HEIGHT = 64;
	
	// for movement
	protected float speed;
	protected float xMove, yMove;
	
	// the Class Constructor, passes what it recieves to the super constructor and sets variables 'speed', 'xMove', 'yMove' to 0.
	public Creature(Handler handler,float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = 0;
		xMove = 0; 
		yMove = 0;
	}

	public void move() {
		// Checks if the entity will collide with any other entity before it moves
		if (!checkEntityCollisions(xMove,0f)) {
			moveX();	
		}
		if (!checkEntityCollisions(0f,yMove)) {
			moveY();	
		}
	}
	
	public void moveX() {
		// Checks which direction the entity is moving
		// gets the tile position the player is moving in or to and checks if it is solid or not.
			// If solid it sets the entities position equal to the tile before the solid tile.
			// Else it allows the entity to move in the direction
		
		if (xMove > 0) { // Moving right
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			if (!collisionWithTile(tx, (int)( y + bounds.y)/Tile.TILEHEIGHT) &&
					!collisionWithTile(tx,(int)( y + bounds.y + bounds.height)/Tile.TILEHEIGHT)) {
				x += xMove;
			}
			else {
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
			}
			
		}
		else if (xMove < 0) { // MOving left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			
			if (!collisionWithTile(tx, (int)( y + bounds.y)/Tile.TILEHEIGHT) &&
					!collisionWithTile(tx,(int)( y + bounds.y + bounds.height)/Tile.TILEHEIGHT)) {
				x += xMove;
			}
			else{
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
			
		}
	}
	
	public void moveY() {
		// Checks which direction the entity is moving
		// gets the tile position the player is moving in or to and checks if it is solid or not.
			// If solid it sets the entities position equal to the tile before the solid tile.
			// Else it allows the player to move in the direction
		if (yMove< 0){ // Moving Up
			
			int ty = (int) (y + yMove + bounds.y)/ Tile.TILEHEIGHT;
			if (!collisionWithTile((int) (x + bounds.x)/ Tile.TILEWIDTH,ty) &&
			   (!collisionWithTile((int) (x + bounds.x + bounds.width)/ Tile.TILEWIDTH,ty))) {
				y += yMove;
			}
			else{
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT- bounds.y;
				
			}
		}
		else if (yMove > 0){ // Moving Down
			int ty = (int) (y + yMove + bounds.y + bounds.height)/ Tile.TILEHEIGHT;
		
			if (!collisionWithTile((int) (x + bounds.x)/ Tile.TILEWIDTH,ty) &&
					   (!collisionWithTile((int) (x + bounds.x + bounds.width)/ Tile.TILEWIDTH,ty))) {
						y += yMove;
			}
			else{
				y = ty *Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		// Checks the Tiles property solid
		return handler.getWorld().getTile(x, y).isSolid();
	}

	// Anything Below Getters/Setters
	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	
}
