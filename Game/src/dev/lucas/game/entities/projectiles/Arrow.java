package dev.lucas.game.entities.projectiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Animation;
import dev.lucas.game.gfx.Assets;
import dev.lucas.game.utils.Utils;

/** 
 * <i><b>Arrow</b></i>
 * <pre>	public class Arrow extends Projectile</pre>
 * <p>This class defines the necessary methods and variables for an Arrow.</p>
 * @see {@link dev.lucas.game.entities.projectiles.Projectile Projectile**/
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
	/**
	 * <i><b>Arrow</b></i>
	 * <pre>	public Arrow(Handler handler,
	 *                       float x1,
	 *                       float y1,
	 *                       float x2,
	 *                       float y2,
	 *                      String sender)</pre>
	 * <p>The class constructor takes in the handler, x1, y1, and the width, and height for the projectile and passes those values to the super constructor. 
	 * It also creates the bounding box of the Arrow. It sets the damage of what the arrow will do, calculates the direction the arrow fires in, gets the animation
	 * and saves what sent the projectile.</p>
	 * @param Handler handler,
	 * @param Float x1,
	 * @param Float y1,
	 * @param Float x2,
	 * @param Float y2,
	 * @param String sender
	 * @return None
	 * @see {@link dev.lucas.game.Handler Handler} , {@link dev.lucas.game.entities.projectiles.Projectile Projectile} , {@link dev.lucas.game.gfx.Animation Animation} , 
	 * {@link dev.lucas.game.gfx.GameCamera GameCamera} , {@link dev.lucas.game.gfx.Assets Assets}
	 *  **/
	public Arrow(Handler handler, float x1, float y1, float x2, float y2, String sender) {
		super(handler, x1, y1, Projectile.DEFAULT_PROJECTILE_WIDTH *2, Projectile.DEFAULT_PROJECTILE_HEIGHT*2);
		bounds.x = (int)(width*0.14);
		bounds.y = (int)(height*0.2);
		bounds.width = (int)(width/1.4);
		bounds.height = (int)(height/2.0);
		dmg = 2;
		delta_x = (x2+ handler.getGameCamera().getX_offset())-x1;
		delta_y = (y2+ handler.getGameCamera().getY_offset())-y1;
		direction = (Math.atan2(delta_y,delta_x));

		// Animations
		texture = new Animation(100, Utils.rotateImages(Assets.arrow, direction));
		this.sender = sender; 
	}

	/**
	 * <i><b>Tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This method ticks the time out timer and checks to see if the Arrow needs to die. Then it ticks the Animation, updates the xMove and yMove variables for
	 *  the next direction, then moves the Arrow.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.gfx.Animation}
	 * **/
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

	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>This method renders the Arrow.</p>
	 * @param 
	 * @return None 
	 * @see {@link dev.lucas.game.Handler Handler}, {@link dev.lucas.game.gfx.GameCamera}
	 * **/
	@Override
	public void render(Graphics g) {
		// renders the projectile
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getX_offset()), (int) (y-handler.getGameCamera().getY_offset()), width, height, null);
		
		if (handler.getGame().isDebug()) {
			g.drawRect((int)(x + bounds.x - handler.getGameCamera().getX_offset()), (int) (y + bounds.y - handler.getGameCamera().getY_offset()), bounds.width, bounds.height);
		}
	}
	
	/**
	 * <i><b>die</b></i>
	 * <pre>	public die()</pre>
	 * <p>Sets the active variable to false</p>
	 * @param None
	 * @return None
	 * **/
	@Override
	public void die() {
		// kills the active value
		setActive(false);
	}
	
	/**
	 * <i><b>getCurrentAnimationFrame</b></i>
	 * <pre>	public BufferedImage getCurrentAnimationFrame()</pre>
	 * <p>Gets from the Animation 'texture' the current frame.</p>
	 * @param None
	 * @return BufferedImage
	 * @see {@link dev.lucas.game.gfx.Animation Animation}
	 * **/
	private BufferedImage getCurrentAnimationFrame() {
		// gets the current frame of the animation
		return texture.getCurrentFrame();
	}

}
