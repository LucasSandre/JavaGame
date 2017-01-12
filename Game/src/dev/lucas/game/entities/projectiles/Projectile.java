package dev.lucas.game.entities.projectiles;

import dev.lucas.game.Handler;
import dev.lucas.game.entities.Entity;
import dev.lucas.game.tiles.Tile;

public abstract class Projectile extends Entity {

	// Some statics for speed control and projectile size.
	public static final float SPEED = 8.0f;
	public static final int DEFAULT_PROJECTILE_WIDTH = 32, DEFAULT_PROJECTILE_HEIGHT = 32;
	
	// protected variables for movement
	protected float projectile_speed;
	protected float proj_xMove, proj_yMove;
	protected double direction;
	
	// The Class Constructor
	public Projectile(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		projectile_speed = SPEED;
		proj_xMove = 0;
		proj_yMove = 0;
		this.direction = 0;
	}

	public void move() {
		// When Called It checks if it collided with any Entity
			// If True, damages entity and kills the projectile
			// Else, continues to move.
		if (!checkEntityProjectileCollisions(proj_xMove,0f)) {
			moveX();
		}
		if (!checkEntityProjectileCollisions(0f,proj_yMove)) {
			moveY();
		}
	}
	
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
	
	private boolean collisionWithTile(int x, int y) {
		// returns the tile at the x,y co-ords and returns the is solid value.
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	
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
	
	// Getters & Setters

	public float getProjectile_speed() {
		return projectile_speed;
	}

	public void setProjectile_speed(float projectile_speed) {
		this.projectile_speed = projectile_speed;
	}

	public float getProj_xMove() {
		return proj_xMove;
	}

	public void setProj_xMove(float proj_xMove) {
		this.proj_xMove = proj_xMove;
	}

	public float getProj_yMove() {
		return proj_yMove;
	}

	public void setProj_yMove(float proj_yMove) {
		this.proj_yMove = proj_yMove;
	}
	

}
