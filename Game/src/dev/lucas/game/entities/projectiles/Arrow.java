package dev.lucas.game.entities.projectiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Animation;
import dev.lucas.game.gfx.Assets;

public class Arrow extends Projectile {
	
	// Initializes timer variables
	private long timer = 0;
	private long last_time = System.currentTimeMillis();
	private long time_out = 5000;
	
	// Initializes movement and Animation texture variables
	private double delta_x, delta_y;
	private Animation texture;
	
	// The Class Constructor, when given a handler, the x and y of the sender the x and y of the mouse click, and a string of the sender it:
		// Passes the handler to the super constructor, the x and y of the sender, and the default height and width of a projective.
		// Creates the bounding box
		// sets a damage value
		// calculate how every tick it should move.
		// creates the animation
		// saves who sent the arrow.
	public Arrow(Handler handler, float x1, float y1, float x2, float y2, String sender) {
		super(handler, x1, y1, Projectile.DEFAULT_PROJECTILE_WIDTH, Projectile.DEFAULT_PROJECTILE_HEIGHT);
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 32;
		bounds.height = 32;
		dmg = 2;
		delta_x = (x2+ handler.getGameCamera().getX_offset())-x1;
		delta_y = (y2+ handler.getGameCamera().getY_offset())-y1;
		direction = (Math.atan2(delta_y,delta_x));
		
		// Animations
		texture = new Animation(100,Assets.rainbow_proj);
		this.sender = sender; 
	}
	
	@Override
	public void tick() {
		// updates the arrows variables
		
		// Death timer
			// after 5 seconds the projectile get killed.
		timer += System.currentTimeMillis() - last_time;
		last_time = System.currentTimeMillis();
		if (timer > time_out) {
			die();
		}		
		
		// ticks Animations
		texture.tick();
	
		// ticks Moving
		update();
		move();
	}

	@Override
	public void render(Graphics g) {
		// renders the projectile
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getX_offset()), (int) (y-handler.getGameCamera().getY_offset()), width, height, null);
		g.drawRect((int)(x + bounds.x - handler.getGameCamera().getX_offset()),(int)(y + bounds.y- handler.getGameCamera().getY_offset()), bounds.width, bounds.height);
	}
	@Override
	public void die() {
		// kills the active value
		setActive(false);
	}

	private void update() {
		// updates the projectiles movement variables.
		proj_xMove = (float) (projectile_speed * Math.cos(direction));
		proj_yMove = (float) (projectile_speed * Math.sin(direction));
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		// gets the current fram of the animation
		return texture.getCurrentFrame();
	}

	// Not needed for entities that will die with no items
	@Override
	public float itemDropX(float x, int width) {
		return 0;
	}

	@Override
	public float itemDropY(float y, int height) {
		return 0;
	}

}
