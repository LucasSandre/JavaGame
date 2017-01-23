package dev.lucas.game.entities.creatures;

import dev.lucas.game.Handler;
import dev.lucas.game.entities.Entity;
import dev.lucas.game.tiles.Tile;
/** 
 * <i><b>Creature</b></i>
 * <pre>	public abstract class Creature extends Entity</pre>
 * <p>This Class defines the necessary variables and methods a Creature needs.</p>
 * @see {@link dev.lucas.game.entities.Entity Entity}**/
public abstract class Creature extends Entity {

	// Sets some final values necessary for momentum and size.
	public static final float MAX_SPEED = 7.5f;
	public static final float SPEED_CHANGE = 0.2f;
	public static final int DEFAULT_CREATURE_WIDTH = 64,
							DEFAULT_CREATURE_HEIGHT = 64;
	
	// for movement
	protected float speed;
	protected float xMove, yMove;
	
	/** 
	 * <i><b>Creature</b></i>
	 * <pre>	public Creature(Handler handler, 
	 *                          float x,
	 *                          float y,
	 *                            int width,
	 *                           int height)</pre>
	 * <p>The class constructor, passes what it receives to the super constructor and set three variables <code>speed</code>, <code>xMove</code>, and<code>yMove</code>.</p>
	 * @param
	 * @see {@link dev.lucas.game.entities.Entity Entity}
	 * **/
	public Creature(Handler handler,float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = 0;
		xMove = 0; 
		yMove = 0;
	}

	/** 
	 * <i><b>Move</b></i>
	 * <pre>	public void move()</pre>
	 * <p>This method moves the Creature only if it does not collide with any entity.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.entities.Entity Entity}
	 * **/
	public void move() {
		// Checks if the entity will collide with any other entity before it moves
		if (!checkEntityCollisions(xMove,0f)) {
			moveX();	
		}
		if (!checkEntityCollisions(0f,yMove)) {
			moveY();	
		}
	}
	
	/**
	 * <i><b>MoveX</b></i>
	 * <pre>	public void moveX()</pre>
	 * <p> This method checks to see if the Creature is going to collide with a solid tile in the next tick for both left and right directions. If the Creature is going to collide 
	 * it sets its position 1 pixel away from the tile. If it won't collide it advances the Creature.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.tiles.Tile Tile}
	 *  **/
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
	
	/**
	 * <i><b>MoveY</b></i>
	 * <pre>	public void moveY()</pre>
	 * <p> This method checks to see if the Creature is going to collide with a solid tile in the next tick for both up and down directions. If the Creature is going to collide 
	 * it sets its position 1 pixel away from the tile. If it won't collide it advances the Creature.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.tiles.Tile Tile}
	 *  **/
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
	/**
	 * <i><b>CollisionWithTile</b></i>
	 * <pre>	public boolean collisionWithTile(int x,
	 *                                         int y)</pre>
	 * <p>This method takes in an x and y, gets the tile stored in the Tile class in an multidimensional Array and gets the isSolid method from it and returns its value.</p>
	 * @param 
	 * @return Boolean
	 * @see {@link dev.lucas.game.tiles.Tile Tile}
	 *  **/
	protected boolean collisionWithTile(int x, int y) {
		// Checks the Tiles property solid
		return handler.getWorld().getTile(x, y).isSolid();
	}

	// Anything Below Getters/Setters
	/** 
	 * <i><b>GetxMove</b></i>
	 * <pre>	public float getxMove()</pre>
	 * <p>Gets the xMove of the Creature.</p>
	 * @param None
	 * @return Int
	 * **/
	public float getxMove() {
		return xMove;
	}
	
	/** 
	 * <i><b>SetxMove</b></i>
	 * <pre>	public void setxMove(float xMove)</pre>
	 * <p>Sets the xMove of the Creature.</p>
	 * @param
	 * @return None
	 * **/
	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	/** 
	 * <i><b>GetyMove</b></i>
	 * <pre>	public float getyMove()</pre>
	 * <p>Gets the yMove of the Creature.</p>
	 * @param None
	 * @return Int
	 * **/
	public float getyMove() {
		return yMove;
	}

	/** 
	 * <i><b>SetyMove</b></i>
	 * <pre>	public void setyMove(float yMove)</pre>
	 * <p>Sets the yMove of the Creature.</p>
	 * @param
	 * @return None
	 * **/
	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	/** 
	 * <i><b>GetHealth</b></i>
	 * <pre>	public int getHealth()</pre>
	 * <p>Gets the Creatures health.</p>
	 * @param None
	 * @return Int
	 * **/
	public int getHealth() {
		return health;
	}

	/** 
	 * <i><b>SetHealth</b></i>
	 * <pre>	public void setHealth(int health)</pre>
	 * <p>Sets the Creature's health.</p>
	 * @param
	 * @return None
	 * **/
	public void setHealth(int health) {
		this.health = health;
	}

	/** 
	 * <i><b>GetSpeed</b></i>
	 * <pre>	public float getSpeed()</pre>
	 * <p>Gets the speed of the Creature.</p>
	 * @param none
	 * @return Float
	 * **/
	public float getSpeed() {
		return speed;
	}

	/** 
	 * <i><b>SetSpeed</b></i>
	 * <pre>	public void setSpeed(float speed)</pre>
	 * <p>Sets the speed of the Creature.</p>
	 * @param
	 * @return None
	 * **/
	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
