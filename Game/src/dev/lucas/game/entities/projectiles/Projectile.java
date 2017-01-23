package dev.lucas.game.entities.projectiles;

import dev.lucas.game.Handler;
import dev.lucas.game.entities.Entity;
import dev.lucas.game.tiles.Tile;

/** 
 * <i><b>Projectile</b></i>
 * <pre> public class Projectile extends Entity</pre>
 * <p>This class defines all the necessary methods and variables for (a/an) Projectile</p>
 * @see {@link dev.lucas.game.entities.Entity Entity}
 * **/
public abstract class Projectile extends Entity {

	// Some statics for speed control and projectile size.
	public static final float SPEED = 8.0f;
	public static final int DEFAULT_PROJECTILE_WIDTH = 32, DEFAULT_PROJECTILE_HEIGHT = 32;
	
	// protected variables for movement
	protected float projectile_speed;
	protected float proj_xMove, proj_yMove;
	protected double direction;
	
	public String sender;
	
	// The Class Constructor
	/**
	 * <i><b> Projectile</b></i>
	 * <pre>	public Projectile(Handler handler,
	 *                            float x,
	 *                            float y,
	 *                            int width,
	 *                            int height)</pre>
	 * <p>The class constructor takes in the parsed values and passes them to the super constructor. It sets the default speed of the Projectile,
	 *  sets its xMove and yMove to 0 as well as its direction.</p>
	 * @param Handler handler,
	 * @param Float x,
	 * @param Float y,
	 * @param Int width,
	 * @param Int height
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public Projectile(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		projectile_speed = SPEED;
		proj_xMove = 0;
		proj_yMove = 0;
		this.direction = 0;
	}
	
	/**
	 * <i><b>checkEntityCollisions</b></i>
	 * <pre>	public boolean checkEntityCollisions()</pre>
	 * <p>This method overrides the one in the Entitiy class to work with projectiles.</p>
	 * @param
	 * @return boolean
	 * @see {@link dev.lucas.game.entities.Entity Entity}
	 * **/
	@Override
	public boolean checkEntityCollisions (float x_offset, float y_offset) {
		// Checks for Entity Collisions by ignoring itself and seeing if bounding boxes collide with another entity
		for (Entity e : handler.getWorld().getEntity_manager().getEntities()){
			if (e.equals(this)) {
				continue;
			}
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(x_offset,y_offset))) {
				if (e.getName().equals(this.sender)) {
					continue;
				}
				e.hurt(this.getDmg());
				this.die();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <i><b>update</b></i>
	 * <pre>	public void update()</pre>
	 * <p>This methods updates the Projectiles xMove and yMove variables.</p>
	 * @param None
	 * @return void
	 * **/
	public void update() {
		// updates the projectiles movement variables.
		proj_xMove = (float) (projectile_speed * Math.cos(direction));
		proj_yMove = (float) (projectile_speed * Math.sin(direction));
	}
	
	/**
	 * <i><b>move</b></i>
	 * <pre>	public void move()</pre>
	 * <p>This method sees if it should move the Projectile.</p>
	 * @param None
	 * @return void
	 * **/
	public void move() {
		// When Called It checks if it collided with any Entity
			// If True, damages entity and kills the projectile
			// Else, continues to move.
		if (!checkEntityCollisions(proj_xMove,0f)) {
			moveX();
		}
		if (!checkEntityCollisions(0f,proj_yMove)) {
			moveY();
		}
	}
	
	/**
	 * <i><b>moveX</b></i>
	 * <pre>	public void moveX()</pre>
	 * <p>This method checks to see how it should move the Projectile in the x dimension.</p>
	 * @param None
	 * @return void
	 * @see {@link dev.lucas.game.entities.tiles.Tile Tile}
	 * **/
	public void moveX() {
		// Checks which direction the entity is moving
				// gets the tile position the player is moving in or to and checks if it is solid or not.
					// If solid it sets the entities position equal to the tile before the solid tile.
					// Else it allows the entity to move in the direction
		if (proj_xMove > 0) { // Moving right
			
			int tx = (int) (x + proj_xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			if (!collisionWithTile(tx, (int)( y + bounds.y)/Tile.TILEHEIGHT) &&
					!collisionWithTile(tx,(int)( y + bounds.y + bounds.height)/Tile.TILEHEIGHT)) {
				x += proj_xMove;
			}
			else {
				die();
			}
			
		}
		else if (proj_xMove < 0) { // MOving left
			int tx = (int) (x + proj_xMove + bounds.x) / Tile.TILEWIDTH;
			
			if (!collisionWithTile(tx, (int)( y + bounds.y)/Tile.TILEHEIGHT) &&
					!collisionWithTile(tx,(int)( y + bounds.y + bounds.height)/Tile.TILEHEIGHT)) {
				x += proj_xMove;
			}
			else{
				die();
			}
			
		}
	}	
	
	/**
	 * <i><b>moveY</b></i>
	 * <pre>	public void moveY()</pre>
	 * <p>This method checks to see how it should move the Projectile in the y dimension.</p>
	 * @param None
	 * @return void
	 * @see {@link dev.lucas.game.entities.tiles.Tile Tile}
	 * **/
	public void moveY() {
		// Checks which direction the entity is moving
				// gets the tile position the player is moving in or to and checks if it is solid or not.
					// If solid it sets the entities position equal to the tile before the solid tile.
					// Else it allows the entity to move in the direction
		
		if (proj_yMove< 0){ // Moving Up
			
			int ty = (int) (y + proj_yMove + bounds.y)/ Tile.TILEHEIGHT;
			if (!collisionWithTile((int) (x + bounds.x)/ Tile.TILEWIDTH,ty) &&
			   (!collisionWithTile((int) (x + bounds.x + bounds.width)/ Tile.TILEWIDTH,ty))) {
				y += proj_yMove;
			}
			else{
				die();
				
			}
		}
		else if (proj_yMove > 0){ // Moving Down
			int ty = (int) (y + proj_yMove + bounds.y + bounds.height)/ Tile.TILEHEIGHT;
		
			if (!collisionWithTile((int) (x + bounds.x)/ Tile.TILEWIDTH,ty) &&
					   (!collisionWithTile((int) (x + bounds.x + bounds.width)/ Tile.TILEWIDTH,ty))) {
						y += proj_yMove;
			}
			else{
				die();
			}
		}
	}
	
	/**
	 * <i><b>collisionWithTile</b></i>
	 * <pre>	public boolean collisionWithTile(int x, int y)</pre>
	 * <p>This method checks to see if the tile stored in the x and y position is solid.</p>
	 * @param
	 * @return boolean
	 * @see {@link dev.lucas.game.entities.tiles.Tile Tile}
	 * **/
	private boolean collisionWithTile(int x, int y) {
		// returns the tile at the x,y co-ords and returns the is solid value.
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	// Getters & Setters

	/**
	 * <i><b>getProjectile_speed</b></i>
	 * <pre>	public float getProjectile_speed()</pre>
	 * <p>Gets the projectile's speed.</p>
	 * @param None
	 * @return float
	 * **/
	public float getProjectile_speed() {
		return projectile_speed;
	}

	/**
	 * <i><b>setProjectile_speed</b></i>
	 * <pre>	public void setProjectile_speed(float projectile_speed)</pre>
	 * <p>Sets the Projectile's speed.</p>
	 * @param
	 * @return void
	 * **/
	public void setProjectile_speed(float projectile_speed) {
		this.projectile_speed = projectile_speed;
	}

	/**
	 * <i><b>getProj_xMove</b></i>
	 * <pre>	public float getProj_xMove()</pre>
	 * <p>Gets the Projectile's xMove variable.</p>
	 * @param None
	 * @return float
	 * **/
	public float getProj_xMove() {
		return proj_xMove;
	}

	/**
	 * <i><b>setProj_xMove</b></i>
	 * <pre>	public void setProj_xMove(float proj_xMove)</pre>
	 * <p>Sets the Projectile's xMove variable.</p>
	 * @param
	 * @return void
	 * **/
	public void setProj_xMove(float proj_xMove) {
		this.proj_xMove = proj_xMove;
	}

	/**
	 * <i><b>getProj_yMove</b></i>
	 * <pre>	public float getProj_yMove()</pre>
	 * <p>Gets the Projectile's yMove variable.</p>
	 * @param None
	 * @return float
	 * **/
	public float getProj_yMove() {
		return proj_yMove;
	}

	/**
	 * <i><b>setProj_yMove</b></i>
	 * <pre>	public void setProj_yMove(float proj_yMove)</pre>
	 * <p>Sets the Projectile's yMove variable.</p>
	 * @param
	 * @return void
	 * **/
	public void setProj_yMove(float proj_yMove) {
		this.proj_yMove = proj_yMove;
	}
	

}
