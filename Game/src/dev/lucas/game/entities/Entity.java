package dev.lucas.game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.lucas.game.Handler;

public abstract class Entity {
	
	public static final int DEFAULT_HEALTH = 3;
	
	// Initializes variables necessary for a basic entity.
	protected Handler handler;
	protected float x,y;
	protected int width,height;
	protected int health;
	protected boolean active = true;
	protected Rectangle bounds;
	protected int dmg;
	protected String sender, name;
	
	
	// The Class Constructor takes in a handler, two floats and two ints
	// Sets the entity to have basic health.
	// Saves the passed values
	// Creates the bounding box for the entity
	// Sets an initial amount to damage for the entity
	// Creates an empy string for name
	public Entity(Handler handler, float x, float y,int width,int height){
		health = DEFAULT_HEALTH;
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(0, 0, width, height);
		dmg = 0;
		name = new String("");
	}
	
	// Defines a few methods every Entity needs.
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	public abstract float itemDropX(float x,int width);
	
	public abstract float itemDropY(float y,int height);
	
	public void hurt(int amt) {
		// subtracts the Entities health by a passed amount and checks if the health is < 0. 
		health -= amt;
		if (health <= 0) {
			active = false;
			die();
		}
	}
	
	public boolean checkEntityCollisions (float x_offset, float y_offset) {
		// Checks for Entity Collisions by ignoring itself and seeing if bounding boxes collide with another entity
		for (Entity e : handler.getWorld().getEntity_manager().getEntities()){
			if (e.equals(this)) {
				continue;
			}
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(x_offset,y_offset))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkEntityProjectileCollisions(float x_offset, float y_offset) {
		// Checks if any entity collides  with a projectile. If so it kills the projectile and hurts the entity. 
		for (Entity p: handler.getWorld().getEntity_manager().getProjectiles()) {
			if (p.equals(this)) {
				continue;
			}
			for (Entity e : handler.getWorld().getEntity_manager().getEntities()) {
				if (p.getCollisionBounds(0f, 0f).intersects(e.getCollisionBounds(x_offset, y_offset))) {
					if (p.sender.equals(e.name)) {
						continue;
					}
					e.hurt(p.getDmg());
					p.die();
					return true;
				}
			}
		}		
		return false;
	}
	
	// Getters and setters.
	public Rectangle getCollisionBounds(float x_offset, float y_offset) {
		return new Rectangle((int)(x + bounds.x + x_offset),(int)(y + bounds.y + y_offset), bounds.width, bounds.height);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	
}

