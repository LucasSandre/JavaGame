package dev.lucas.game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import dev.lucas.game.Handler;

/** 
 * <i><b>Entity</b></i>
 * <pre>	public abstract class Entity</pre>
 * <p>This class loosely describes and implements the necessary variables and methods for any entity.</p>
 * **/
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
		
	/** 
	 * <i><b>Entity</b></i>
	 * <pre>	public Entity(Handler handler,
	 *                        float x, 
	 *                        float y,
	 *                        int width,
	 *                        int height</pre>
	 * <p>The Entity constructor takes in a Handler object, two floats, and two ints. It sets the Entities health to the value stored in variable 'DEFUALT_HEALTH'. Saves the parsed values 
	 * to variables. Creates a basic bounding box. Sets the Entities basic damage value to 0. Finally the constructor sets a name for the entity.</p>
	 * @param Handler handler
	 * @param Float x
	 * @param Float y
	 * @param Int width
	 * @param Int height
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
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
	/** 
	 * <i><b>Tick</b></i>
	 * <pre>	public abstract void tick();</pre>
	 * <p>A method that is required for every Entity.</p>
	 * @param None
	 * @return None
	 * **/
	public abstract void tick();
	
	/** 
	 * <i><b>Render</b></i>
	 * <pre>	public abstract void render(Graphics g);</pre>
	 * <p>A method that is required for every Entity.</p>
	 * @param Graphics g
	 * @return None
	 * **/
	public abstract void render(Graphics g);
	
	/** 
	 * <i><b>Die</b></i>
	 * <pre>public abstract void die();</pre>
	 * <p>A method that is required for every Entity.</p>
	 * @param None
	 * @return None
	 * **/
	public abstract void die();
		
	/** 
	 * <i><b>Hurt</b><i>
	 * <pre>	public void hurt(int amt)</pre>
	 * <p>This method takes in an amount and subtracts it from the health. It also checks if the health is less than or equal to zero. If true, it sets the variable 'active' 
	 * to false and runs the die method. </p>
	 * @param Int amt
	 * @return None
	 * **/
	public void hurt(int amt) {
		// subtracts the Entities health by a passed amount and checks if the health is < 0. 
		health -= amt;
		if (health <= 0) {
			active = false;
			die();
		}
	}
	
	/** 
	 * <i><b>CheckEntityCollisions</b></i>
	 * <pre>	public boolean checkEntityCollisions(float x_offset,
	 *                                             float y_offset)</pre>
	 * <p>This method checks over every Entity. When called by a specific Entity, it ignores itself and checks if another Entity intersects with the specific Entity. 
	 * If it does it returns true, if it does not, it returns false.</p>
	 * @param
	 * @return Boolean
	 * **/
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
	/** 
	 * <i><b>CheckEntityProjectielCollisions</b><i>
	 * <pre>	public boolean checkEntityProjectileCollisions(float x_offset,
	 *                                                       float y_offset)</pre>
	 * <p>This method checks over every Projectile and Entity and sees if any collide. If a Projectile collides with an Entity it damages the Entity, and kills the Projectile.</p>
	 * @param
	 * @return Boolean
	 * @see {@link dev.luacs.game.entities.projectiles.Projectile Projectile}
	 * **/
	public boolean checkEntityProjectileCollisions(float x_offset, float y_offset) {
		// Checks if any entity collides  with a projectile. If so it kills the projectile and hurts the entity. 
		for (Entity p: handler.getWorld().getEntity_manager().getProjectiles()) {
			if (p.equals(this)) {
				continue;
			}
			for (Entity e : handler.getWorld().getEntity_manager().getEntities()) {
				if (e.getCollisionBounds(0f, 0f).intersects(p.getCollisionBounds(x_offset, y_offset))) {
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
	
	// methods for random drop placement for 
	/**
	 * <i><b>itemDropX</b></i>
	 * <pre>	public float itemDropX()</pre>
	 * <p>A method that when given an x coordinate and a width. It calculates a random x offset for an item that is within the Entity.</p>
	 * @param 
	 * @return float
	 * **/
	public float itemDropX(float x, int width) {
		Random rand = new Random();
		int x_offset = rand.nextInt(width);
		return x + x_offset;
		
	}
	
	/**
	 * <i><b>itemDropY</b></i>
	 * <pre>	public float itemDropY()</pre>
	 * <p>A method that when given an y coordinate and a height. It calculates a random y offset for an item that is within the Entity.</p>
	 * @param 
	 * @return float
	 * **/
	public float itemDropY(float y, int height) {
		Random rand = new Random();
		int y_offset = rand.nextInt(height);
		return y + y_offset;
	}
		
	// Getters and setters.
	/** 
	 * <i><b>GetCollisionBounds</b></i>
	 * <pre>	public Rectangle getCollisionBounds(float x_offset, float y_offset)</pre>
	 * <p>Gets the collision bounds of an Entity. The bounds are affected by the passed values.</p>
	 * @param
	 * @return Rectangle
	 * **/
	public Rectangle getCollisionBounds(float x_offset, float y_offset) {
		return new Rectangle((int)(x + bounds.x + x_offset),(int)(y + bounds.y + y_offset), bounds.width, bounds.height);
	}

	/** 
	 * <i><b>GetX</b></i>
	 * <pre>	public float getX()</pre>
	 * <p>Gets the x value of the Entity.</p>
	 * @param None
	 * @return float
	 * **/
	public float getX() {
		return x;
	}

	/** 
	 * <i><b>SetX</b></i>
	 * <pre>	public void setX(float x)</pre>
	 * <p>Sets the x value of the Entity.</p>
	 * @param
	 * @return None
	 * **/
	public void setX(float x) {
		this.x = x;
	}

	/** 
	 * <i><b>GetY</b></i>
	 * <pre>	public float getY()</pre>
	 * <p>Gets the Y value of the Entity.</p>
	 * @param None
	 * @return float
	 * **/
	public float getY() {
		return y;
	}

	/** 
	 * <i><b>SetY</b></i>
	 * <pre>	public void setY(float y)</pre>
	 * <p>Sets the y value of the Entity.</p>
	 * @param
	 * @return None
	 * **/
	public void setY(float y) {
		this.y = y;
	}

	/** 
	 * <i><b>GetWidth</b></i>
	 * <pre>	public int getWidth()</pre>
	 * <p>Gets the width of the Entity.</p>
	 * @param None
	 * @return Int
	 * **/
	public int getWidth() {
		return width;
	}

	/** 
	 * <i><b>SetWidth</b></i>
	 * <pre>	public void setWidth(int width)</pre>
	 * <p>Sets the Entity's width value</p>
	 * @param
	 * @return None
	 * 	 * **/
	public void setWidth(int width) {
		this.width = width;
	}

	/** 
	 * <i><b>GetHeight</b></i>
	 * <pre>	public int getHeight()</pre>
	 * <p>Gets the height of the Entity.</p>
	 * @param None
	 * @return Int
	 * **/
	public int getHeight() {
		return height;
	}

	/** 
	 * <i><b>SetHeight</b></i>
	 * <pre>	public void setWidth(int width)</pre>
	 * <p>Sets the Entity's width value</p>
	 * @param
	 * @return None
	 * 	 * **/
	public void setHeight(int height) {
		this.height = height;
	}

	/** 
	 * <i><b>GetHealth</b></i>
	 * <pre>	public int getHealth()</pre>
	 * <p>Gets the Entity's health.</p>
	 * @param None
	 * @return Int
	 * **/
	public int getHealth() {
		return health;
	}

	/** 
	 * <i><b>SetHealth</b></i>
	 * <pre>	public void setHealth(int health)</pre>
	 * <p>Sets the Entity's health.</p>
	 * @param
	 * @return None
	 * **/
	public void setHealth(int health) {
		this.health = health;
	}

	/** 
	 * <i><b>IsActive</b></i>
	 * <pre>	public boolean isActive()</pre>
	 * <p>Returns the Entity's 'active' variable.</p>
	 * @param None
	 * @return Boolean
	 * **/
	public boolean isActive() {
		return active;
	}

	/** 
	 * <i><b>SetHealth</b></i>
	 * <pre>	public void setActive(boolean active)</pre>
	 * <p>Sets the Entity's 'active' variable.</p>
	 * @param
	 * @return None
	 * **/
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/** 
	 * <i><b>GetDmg()</b></i>
	 * <pre>	public int getDmg()</pre>
	 * <p>Gets the value stored in the Entity's variable 'dmg'.</p>
	 * @param None
	 * @return int
	 * **/
	public int getDmg() {
		return dmg;
	}

	/** 
	 * <i><b>SetDmg</b></i>
	 * <pre>	public void setDmg(int dmg) </pre>
	 * <p>Sets the Entity's value stored in variable 'dmg' to the passed value.</p>
	 * @param
	 * @return None
	 * @see {@link dev.lucas.game }
	 * **/
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	
}

