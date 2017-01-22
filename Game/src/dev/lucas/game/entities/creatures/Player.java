package dev.lucas.game.entities.creatures;



import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import dev.lucas.game.Handler;
import dev.lucas.game.entities.projectiles.Arrow;
import dev.lucas.game.gfx.Animation;
import dev.lucas.game.gfx.Assets;
import dev.lucas.game.inventory.Inventory;

/** 
 * <i><b>Player</b></i>
 * <pre>	public class Player extends Creature</pre>
 * <p>This class defines the necessary variables and methods for a Player.</p>
 * @see {@link dev.lucas.game.entitis.creatures.Creature Creature}
 * **/
public class Player extends Creature {
	
	// Animations
	
	private Animation anim_down, anim_up, anim_right, anim_left, anim_right_down, anim_right_up,
	                  anim_left_down, anim_left_up;
	private Animation attack_anim_down, attack_anim_up, attack_anim_right, attack_anim_left, attack_anim_left_up,
	 				  attack_anim_left_down, attack_anim_right_up, attack_anim_right_down;
	
	// For Attack Animations
	private boolean att_down  = false,
			        att_up  = false,
			        att_left  = false,
			        att_right = false,
			        att_right_up = false,
			        att_right_down = false,
			        att_left_up = false,
					att_left_down = false,
			        attacking = false;
	
	// For movement slow down
	private boolean up = false,
			        down = false,
			        left = false,
			        right = false,
			        up_left = false,
			        up_right = false,
			        down_left = false,
			        down_right = false;
	// For no movement
	private int player_i = 0;
	
	// Attack Timer
	private long last_attack_timer, attack_cooldown = 800, attack_timer = attack_cooldown;
	
	// Inventory
	private Inventory inventory;
	
	// for health/energy access
	public final int MAX_HEALTH = 100;
	public final int MAX_ENERGY = 100;
	
	// For the players Energy/Mana
	
	public int energy = 100;
	
	
	/** 
	 * <i><b>Player</b></i>
	 * <pre>	public Player(Handler handler,
	 *                        float x,
	 *                        float y)</pre>
	 * <p>The class constructor takes in a Handler, and an x and y value. It then passes these to the super constructor along with default width and height from the Creature class 
	 * and sets the bounding box for the player. It also creates all the animations, a name, and a inventory.</p>
	 * @param Handler handler
	 * @param Float x
	 * @param Float y
	 * @return None
	 * @see {@link dev.lucas.game.Handler Handler} , {@link dev.lucas.game.entities.creatures.Creature Creature} , {@link dev.lucas.game.inventory.Inventory Inventory} , 
	 * {@link dev.lucas.game.gfx.Aninmaton Animation}
	 * **/
	public Player(Handler handler,float x, float y) {
		super(handler,x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		health = MAX_HEALTH;
		bounds.x = 16;
		bounds.y = 24;
		bounds.width = 28;
		bounds.height = 38;
		
		// Animations
		
		anim_down = new Animation(200,Assets.player_down);
		anim_up = new Animation(200,Assets.player_up);
		anim_right = new Animation(200,Assets.player_right);
		anim_right_up = new Animation(200,Assets.player_right_up);
		anim_right_down = new Animation(200,Assets.player_right_down);
		anim_left = new Animation(200,Assets.player_left);
		anim_left_up = new Animation(200,Assets.player_left_up);
		anim_left_down = new Animation(200,Assets.player_left_down);
		
		attack_anim_down = new Animation(20,Assets.attack_down);
		attack_anim_up = new Animation(20,Assets.attack_up);
		attack_anim_right = new Animation(20,Assets.attack_right);
		attack_anim_left = new Animation(20,Assets.attack_left);
		attack_anim_right_up = new Animation(20,Assets.attack_right_up);
		attack_anim_left_up = new Animation(20,Assets.attack_left_up);
		attack_anim_right_down = new Animation(20,Assets.attack_right_down);
		attack_anim_left_down = new Animation(20,Assets.attack_left_down);
		
		// Sets the name of the entity
		name = new String("PLAYER");
		
		// Creates the players inventory
		inventory = new Inventory(handler);
	
		
	}

	/** 
	 * <i><b>Tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>The tick method updates all the variables for this class.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.inventory.Inventory Inventory} , {@link dev.lucas.game.gfx.Aninmaton Animation} , {@link dev.lucas.game.gfx.GameCamera GameCamera}
	 * **/
	@Override
	public void tick() {
		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_MINUS)) {
			health -= 2;
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_0)) {
			health += 2;
		}		
		
		// Animations
		anim_down.tick();
		anim_up.tick();
		anim_right.tick();
		anim_left.tick();
		anim_right_up.tick();
		anim_right_down.tick();
		anim_left_up.tick();
		anim_left_down.tick();
		
		// Attack Animations
		// All of these check for if the player is attacking and in which direction, ticks the animation and checks if the animation was reset back to the first frame.
		if (att_up) {
			attack_anim_up.tick();
			if (attack_anim_up.getIndex() == 0){
				att_up = false;
				attacking = false;
			}
				
		}
		else if (att_left_up) {
			attack_anim_left_up.tick();
			if (attack_anim_left_up.getIndex() == 0){
				att_left_up = false;
				attacking = false;
			}
		}
		else if (att_right_up) {
			attack_anim_right_up.tick();
			if (attack_anim_right_up.getIndex() == 0){
				att_right_up = false;
				attacking = false;
			}
		}
		else if (att_left_down) {
			attack_anim_left_down.tick();
			if (attack_anim_left_down.getIndex() == 0){
				att_left_down = false;
				attacking = false;
			}
		}
		else if (att_right_down) {
			attack_anim_right_down.tick();
			if (attack_anim_right_down.getIndex() == 0){
				att_right_down = false;
				attacking = false;
			}
		}
		else if (att_down) {
			attack_anim_down.tick();
			if (attack_anim_down.getIndex() == 0){
				att_down = false;
				attacking = false;
			}
		}
		else if (att_left) {
			attack_anim_left.tick();
			if (attack_anim_left.getIndex() == 0){
				att_left = false;
				attacking = false;
			}
		}
		else if (att_right) {
			attack_anim_right.tick();
			if (attack_anim_right.getIndex() == 0){
				att_right = false;
				attacking = false;
			}
		}
		
		//Movement	
		
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		
		// Attack
		
		checkAttacks();
		// Inventory
	
		inventory.tick();
		
	}
	
	/** 
	 * <i><b>Render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>This method renders the Player and switches the rending if the player if attacking and also renders the inventory.</p>
	 * @param
	 * @return None
	 * @see {@link dev.lucas.game.Handler Handler} , {@link dev.lucas.game.gfx.GameCamera GameCamera} , {@link dev.lucas.game.inventory.Inventory Inventory}
	 * **/
	@Override
	public void render(Graphics g) {
		// If the player is not attacking use walking animations
		if (attacking == false) {
			g.drawImage(getCurrentPlayerAnimationFrame(), (int) (x - handler.getGameCamera().getX_offset()), (int) (y-handler.getGameCamera().getY_offset()), width ,height, null);
		}
		// If the player is attacking use attacking animation
		else if (attacking == true) {
			g.drawImage(getCurrentAttackAnimationFrame(),  (int) (x - handler.getGameCamera().getX_offset()), (int) (y-handler.getGameCamera().getY_offset()), (int) (width) , (int) (height), null);
		}
		if (handler.getGame().isDebug()) {
			Rectangle cb = getCollisionBounds(0,0);
			g.drawRect((int)(cb.x - handler.getGameCamera().getX_offset()), (int)(cb.y - handler.getGameCamera().getY_offset()), cb.width, cb.height);
		}
		inventory.render(g);
	}
	
	/** 
	 * <i><b>CheckAttacks</b></i>
	 * <pre>	private void checkAttacks()</pre>
	 * <p>This method checks to see if the attack cooldown is ended and finds where the player clicked and launches a projectile based on the players class in the direction of the mouse click. </p>
	 * @param None
	 * @return null
	 * @see {@link dev.lucas.game.Handler Handler} , {@link dev.lucas.game.input.MouseManager MouseManager} , {@link dev.lucas.game.gfx.GameCamera GameCamera} , {@link dev.lucas.game.entities.EntityManager EntityManager}
	 * **/
	private void checkAttacks() {
		// Increases the attack timer and checks if the cooldown has worn down.
		attack_timer += System.currentTimeMillis() - last_attack_timer;
		last_attack_timer = System.currentTimeMillis();
		if (attack_timer < attack_cooldown) {
			return;
		}
		// gets the collision bounds of the player.
		Rectangle cb = getCollisionBounds(0,0);
		
		
		// Checks where the mouse was left clicked and where it was clicked and updates animation variables
		if (handler.getMouseManager().isLeftPressed()) {			
			if (handler.getMouseManager().getMouseX()  <  (int)(cb.x - handler.getGameCamera().getX_offset()) &&
				handler.getMouseManager().getMouseY()  <= (int)(cb.y - handler.getGameCamera().getY_offset())) {
				// Animation trigger code for top left
				attacking = true;
				att_left_up = true;
			}
			else if (handler.getMouseManager().getMouseX()  > (int)(cb.x+ cb.width- handler.getGameCamera().getX_offset()) &&
				     handler.getMouseManager().getMouseY()  <= (int)(cb.y- handler.getGameCamera().getY_offset())) {
				// Animation trigger code for Top Right
				attacking = true;
				att_right_up = true;
			}
			else if (handler.getMouseManager().getMouseX()  > (int)(cb.x + cb.width - handler.getGameCamera().getX_offset()) &&
					handler.getMouseManager().getMouseY()  >= (int)(cb.y + cb.height - handler.getGameCamera().getY_offset())) {
				// Animation trigger code for Bottom Right
				attacking = true;
				att_right_down = true;
			}
			else if (handler.getMouseManager().getMouseX()  < (int)(cb.x - handler.getGameCamera().getX_offset()) &&
					 handler.getMouseManager().getMouseY()  >= (int)(cb.y + cb.height - handler.getGameCamera().getY_offset())) {
				// Animation trigger code for Bottom Left
				attacking = true;
				att_left_down = true;
			}
			else if (handler.getMouseManager().getMouseX() >= (cb.x - handler.getGameCamera().getX_offset()) &&
					 handler.getMouseManager().getMouseX() <= (int)(cb.x + cb.width- handler.getGameCamera().getX_offset()) &&
					 handler.getMouseManager().getMouseY() <= (int)(cb.y - handler.getGameCamera().getY_offset())) {
				// Animation trigger code for Up
				attacking = true;
				att_up = true;
			}
			else if (handler.getMouseManager().getMouseX() >= (int)(cb.x - handler.getGameCamera().getX_offset()) &&
					 handler.getMouseManager().getMouseX() <= (int)(cb.x + cb.width - handler.getGameCamera().getX_offset()) &&
					 handler.getMouseManager().getMouseY() >= (int)(cb.y + cb.height - handler.getGameCamera().getY_offset())) {
				// Animation trigger code for Down
				attacking = true;
				att_down = true;
			}
			else if (handler.getMouseManager().getMouseX() <= (int)(cb.x - handler.getGameCamera().getX_offset()) &&
					 handler.getMouseManager().getMouseY() >= (int)(cb.y - handler.getGameCamera().getY_offset()) &&
					 handler.getMouseManager().getMouseY() <= (int)(cb.y + cb.height - handler.getGameCamera().getY_offset())) {
				// Animation trigger code for Left
				attacking = true;
				att_left = true;
			}
			else if (handler.getMouseManager().getMouseX() >= (int)(cb.x + cb.width - handler.getGameCamera().getX_offset()) &&
					 handler.getMouseManager().getMouseY() >= (int)(cb.y - handler.getGameCamera().getY_offset()) &&
					 handler.getMouseManager().getMouseY() <= (int)(cb.y + cb.height - handler.getGameCamera().getY_offset())) {
				// Animation trigger code for Right
				attacking = true;
				att_right = true;
			}
			else {
				return;
			}
			// Adds the projectile to the world and resets the attack timer.
			handler.getWorld().getEntity_manager().addProjectile(new Arrow(handler, x, y, handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), name));
			attack_timer = 0;
		}
		
	}

	/** 
	 * <i><b>GetInput</b></i>
	 * <pre>	private void getInput()</pre>
	 * <p>This method checks for keys directional keys that are being pressed, and changes boolean variables which change what animation is played. Also this method handles momentum.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.Handler Handler} , {@link dev.lucas.game.gfx.GameCamera GameCamera} , {@link dev.lucas.game.input.KeyManage KeyManager} , {@link dev.lucas.game.entities.creatures.Creature Creature
	 * **/
	private void getInput() {
		// Resets xMove and yMove variables
		xMove = 0;
		yMove = 0;
		
		// For the Following Checks for which directional buttons are pressed and increases the speed.
		// Changes what still should be used when the player stops
		// Changes boolean variables to identify what button is pressed
		// Changes xMove and yMove
		// End Result = gradually increases speed until max speed it acheived
		if (handler.getKeyManager().up && handler.getKeyManager().left) {
			speed += SPEED_CHANGE;
			if (speed > MAX_SPEED) {
				speed = MAX_SPEED;
			}
			player_i = 5;
			up_left = true;
			down_left = false;
			up_right = false;
			down_right = false;
			up = false;
			down = false;
			left = false;
			right = false;
			yMove = -speed;
			xMove = -speed;
		}
		else if (handler.getKeyManager().up && handler.getKeyManager().right) {
			speed += SPEED_CHANGE;
			if (speed > MAX_SPEED) {
				speed = MAX_SPEED;
			}
			player_i = 3;
			up_left = false;
			down_left = false;
			up_right = true;
			down_right = false;
			up = false;
			down = false;
			left = false;
			right = false;
			yMove = -speed;
			xMove = speed;
		}
		else if (handler.getKeyManager().down && handler.getKeyManager().right) {
			speed += SPEED_CHANGE;
			if (speed > MAX_SPEED) {
				speed = MAX_SPEED;
			}
			player_i = 1;
			up_left = false;
			down_left = false;
			up_right = false;
			down_right = true;
			up = false;
			down = false;
			left = false;
			right = false;
			yMove = speed;
			xMove = speed;
		}
		else if (handler.getKeyManager().down && handler.getKeyManager().left) {
			speed += SPEED_CHANGE;
			if (speed > MAX_SPEED) {
				speed = MAX_SPEED;
			}
			player_i = 7;
			up_left = false;
			down_left = true;
			up_right = false;
			down_right = false;
			up = false;
			down = false;
			left = false;
			right = false;
			yMove = speed;
			xMove = -speed;
		}
		else if (handler.getKeyManager().up) {
			speed += SPEED_CHANGE;
			if (speed > MAX_SPEED) {
				speed = MAX_SPEED;
			}
			player_i = 4;
			up_left = false;
			down_left = false;
			up_right = false;
			down_right = false;
			up = true;
			down = false;
			left = false;
			right = false;
			yMove = -speed;
		}
		else if (handler.getKeyManager().down) {
			speed += SPEED_CHANGE;
			if (speed > MAX_SPEED) {
				speed = MAX_SPEED;
			}
			player_i = 0;
			up_left = false;
			down_left = false;
			up_right = false;
			down_right = false;
			up = false;
			down = true;
			left = false;
			right = false;
			yMove = speed;
		}
		else if (handler.getKeyManager().left) {
			speed += SPEED_CHANGE;
			if (speed > MAX_SPEED) {
				speed = MAX_SPEED;
			}
			player_i = 6;
			up_left = false;
			down_left = false;
			up_right = false;
			down_right = false;
			up = false;
			down = false;
			left = true;
			right = false;
			xMove = -speed;
		}
		else if (handler.getKeyManager().right) {
			speed += SPEED_CHANGE;
			if (speed > MAX_SPEED) {
				speed = MAX_SPEED;
			}
			player_i = 2;
			up_left = false;
			down_left = false;
			up_right = false;
			down_right = false;
			up = false;
			down = false;
			left = false;
			right = true;
			xMove = speed;
		}
		
		// Checks if no directional buttons are pressed
		// decreases Speed
		// If Speed returns to 0 or - beyond
			// sets all directional booleans to false
		// depending on the directional boolean variable it changes xMove and yMove
		// End result = gradual slow down of player to a stop.
		if (!(handler.getKeyManager().right) && !(handler.getKeyManager().left) &&
			!(handler.getKeyManager().up)    && !(handler.getKeyManager().down)){
			speed -= SPEED_CHANGE;
			if (speed < 0) {
				speed = 0;
				up = false;
				up_left = false;
				up_right = false;
				down = false;
				down_left = false;
				down_right = false;
				left = false;
				right = false;
			}
			if (up_left) {
				yMove = -speed;
				xMove = -speed;
			}
			else if (up_right) {
				yMove = -speed;
				xMove = speed;
			}
			else if (down_left) {
				yMove = speed;
				xMove = -speed;
			}
			else if (down_right) {
				yMove = speed;
				xMove = speed;
			}
			else if (up) {
				yMove = -speed;
			}
			else if (down) {
				yMove = speed;
			}
			else if (left) {
				xMove = -speed;
			}
			else if (right) {
				xMove = speed;
			}
		}
	}
	
	// methods allow items to be added to the world after the player dies in a random location based on the inputed values
	/** 
	 * <i><b>ItemDropX</b></i>
	 * <pre>	public float itemDropX(float x,
	 *                                 int width)</pre>
	 * <p>This method generates a random x-offset to set an item drop to that is within the Entities dimensions.</p>
	 * @param
	 * @return Float
	 * **/
	@Override
	public float itemDropX(float x, int width) {
		Random rand = new Random();
		int x_offset = rand.nextInt(width);
		return x + x_offset;
		
	}

	/** 
	 * <i><b>ItemDropY</b></i>
	 * <pre>	public float itemDropY(float y,
	 *                                 int width)</pre>
	 * <p>This method generates a random y-offset to set an item drop to that is within the Entities dimensions.</p>
	 * @param
	 * @return Float
	 * **/
	@Override
	public float itemDropY(float y, int height) {
		Random rand = new Random();
		int y_offset = rand.nextInt(height);
		return y + y_offset;
	}

	/** 
	 * <i><b>Die</b></i>
	 * <pre>	public void die()</pre>
	 * <p>Kills the Player.</p>
	 * @param none
	 * @return None
	 * **/
	@Override
	public void die() {
		active = false;
		
	}
	
	// Getters and Setters
	/** 
	 * <i><b>GetInventory</b></i>
	 * <pre>	public Inventory getInventory()</pre>
	 * <p>Gets the Player's Inventory.</p>
	 * @param None
	 * @return Inventory
	 * @see {@link dev.lucas.game.inventory.Inventory Inventory}
	 * **/
	public Inventory getInventory() {
		return inventory;
	}

	/** 
	 * <i><b>SetInventory</b></i>
	 * <pre>	public void setInventory(Inventory inventory)</pre>
	 * <p>Sets the Player's Inventory.</p>
	 * @param 
	 * @return None
	 * @see {@link dev.lucas.game.inventory.Inventory Inventory}
	 * **/
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	// Based on the xMove and yMove variables, returns the corresponding animation frame for the direction the player is traveling
	/** 
	 * <i><b>GetCurrentPlayerAnimationFrame</b></i>
	 * <pre>	private BufferedImage getCurrentAttackAnimationFrame()</pre>
	 * <p>Based on the xMove and yMove variables edited by other methods, this method returns the current frame of an animation. If both variables are equal to 0 variables  
	 * it returns a still of the last animation played.</p>
	 * @param None
	 * @return BufferedImage
	 * @see {@link dev.lucas.game.gfx.Animation Animation}
	 * **/	
	private BufferedImage getCurrentPlayerAnimationFrame() {
		if (xMove < 0 && yMove < 0) {
			return anim_left_up.getCurrentFrame();
		}
		else if (xMove > 0 && yMove < 0) {
			return anim_right_up.getCurrentFrame();
		}
		else if (xMove < 0 && yMove > 0) {
			return anim_left_down.getCurrentFrame();
		}
		else if (xMove > 0 && yMove > 0) {
			return anim_right_down.getCurrentFrame();
		}
		else if (xMove < 0){
			return anim_left.getCurrentFrame();
		}
		else if (xMove > 0) {
			return anim_right.getCurrentFrame();
		}
		else if (yMove < 0) {
			return anim_up.getCurrentFrame();
		}
		else if (yMove > 0) {
			return anim_down.getCurrentFrame();
		}
		return Assets.player_still[player_i];
	}
	
	//Based on the boolean variables, returns Attack animation frame for the player
	/** 
	 * <i><b>GetCurrentAttackAnimationFrame</b></i>
	 * <pre>	private BufferedImage getCurrentAttackAnimationFrame()</pre>
	 * <p>Based on boolean variables edited by other methods, this method returns the current frame of an animation. If all the boolean variables are false 
	 * it returns a still of the last animation played.</p>
	 * @param None
	 * @return BufferedImage
	 * @see {@link dev.lucas.game.gfx.Animation Animation}
	 * **/	
	private BufferedImage getCurrentAttackAnimationFrame() {
		if (att_up) {
			return attack_anim_up.getCurrentFrame();
		}
		else if (att_down) {
			return attack_anim_down.getCurrentFrame();
		}
		else if (att_left) {
			return attack_anim_left.getCurrentFrame();
		}
		else if (att_right) {
			return attack_anim_right.getCurrentFrame();
		}
		else if (att_right_up) {
			return attack_anim_right_up.getCurrentFrame();
		}
		else if (att_right_down) {
			return attack_anim_right_down.getCurrentFrame();
		}
		else if (att_left_up) {
			return attack_anim_left_up.getCurrentFrame();
		}
		else if (att_left_down) {
			return attack_anim_left_down.getCurrentFrame();
		}
		return Assets.player_still[player_i];
		
		
	}

}
