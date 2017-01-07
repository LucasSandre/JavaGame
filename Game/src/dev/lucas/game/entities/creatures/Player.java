package dev.lucas.game.entities.creatures;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import dev.lucas.game.Handler;
import dev.lucas.game.entities.Entity;
import dev.lucas.game.gfx.Animation;
import dev.lucas.game.gfx.Assets;
import dev.lucas.game.inventory.Inventory;

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
	
	public Player(Handler handler,float x, float y) {
		super(handler,x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
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
		
		
		inventory = new Inventory(handler);
	}

	@Override
	public void tick() {
		
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
	
	@Override
	public void render(Graphics g) {
		if (attacking == false) {
			g.drawImage(getCurrentPlayerAnimationFrame(), (int) (x - handler.getGameCamera().getX_offset()), (int) (y-handler.getGameCamera().getY_offset()), width ,height, null);
		}
		else if (attacking == true) {
			g.drawImage(getCurrentAttackAnimationFrame(),  (int) (x - handler.getGameCamera().getX_offset()), (int) (y-handler.getGameCamera().getY_offset()), (int) (width) , (int) (height), null);
		}
		g.setColor(Color.red);
		Rectangle cb = getCollisionBounds(0,0);
		g.drawRect((int)(cb.x - handler.getGameCamera().getX_offset()), (int)(cb.y - handler.getGameCamera().getY_offset()), cb.width, cb.height);
		inventory.render(g);
	}

	private void checkAttacks() {
		attack_timer += System.currentTimeMillis() - last_attack_timer;
		last_attack_timer = System.currentTimeMillis();
		
		if (attack_timer < attack_cooldown){
			return;
		}
		Rectangle ar = new Rectangle();
		Rectangle cb = getCollisionBounds(0,0);
		
		int ar_size = 60;
		ar.width = ar_size;
		ar.height = ar_size;
		//System.out.println(handler.getMouseManager().getMouseX() + "   " + (cb.x - handler.getGameCamera().getX_offset()));
		//System.out.println(handler.getMouseManager().getMouseY() + "   " + (cb.y - handler.getGameCamera().getY_offset())
		//		+ "   " + (cb.y + cb.height - handler.getGameCamera().getY_offset()));
		if (handler.getMouseManager().getMouseX()  <  (int)(cb.x - handler.getGameCamera().getX_offset()) &&
			handler.getMouseManager().getMouseY()  <= (int)(cb.y - handler.getGameCamera().getY_offset()) && 
			handler.getMouseManager().isLeftPressed()) {
			// Top Left
			attacking = true;
			ar.x = cb.x - cb.width - cb.width/2;
			ar.y = cb.y - cb.height;
			att_left_up = true;
		}
		else if (handler.getMouseManager().getMouseX()  > (int)(cb.x+ cb.width- handler.getGameCamera().getX_offset()) &&
				 handler.getMouseManager().getMouseY()  <= (int)(cb.y- handler.getGameCamera().getY_offset()) && 
				 handler.getMouseManager().isLeftPressed()) {
			// Top Right
			attacking = true;
			ar.x = cb.x + cb.width;
			ar.y = cb.y - cb.height;
			att_right_up = true;
		}
		else if (handler.getMouseManager().getMouseX()  > (int)(cb.x + cb.width - handler.getGameCamera().getX_offset()) &&
				 handler.getMouseManager().getMouseY()  >= (int)(cb.y + cb.height - handler.getGameCamera().getY_offset()) && 
				 handler.getMouseManager().isLeftPressed()) {
			// Bottom Right
			attacking = true;
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height;
			att_right_down = true;
		}
		else if (handler.getMouseManager().getMouseX()  < (int)(cb.x - handler.getGameCamera().getX_offset()) &&
				 handler.getMouseManager().getMouseY()  >= (int)(cb.y + cb.height - handler.getGameCamera().getY_offset()) && 
				 handler.getMouseManager().isLeftPressed()) {
			// Bottom Left
			attacking = true;
			ar.x = cb.x - cb.width - cb.width/2;
			ar.y = cb.y + cb.height;
			att_left_down = true;
		}
		else if (handler.getMouseManager().getMouseX() >= (cb.x - handler.getGameCamera().getX_offset()) &&
				 handler.getMouseManager().getMouseX() <= (int)(cb.x + cb.width- handler.getGameCamera().getX_offset()) &&
				 handler.getMouseManager().getMouseY() <= (int)(cb.y - handler.getGameCamera().getY_offset()) && 
				 handler.getMouseManager().isLeftPressed()) {
			// Up
			attacking = true;
			ar.x = cb.x + cb.width/2 - ar_size / 2;
			ar.y = cb.y - ar_size;
			att_up = true;
		}
		else if (handler.getMouseManager().getMouseX() >= (int)(cb.x - handler.getGameCamera().getX_offset()) &&
				 handler.getMouseManager().getMouseX() <= (int)(cb.x + cb.width - handler.getGameCamera().getX_offset()) &&
				 handler.getMouseManager().getMouseY() >= (int)(cb.y + cb.height - handler.getGameCamera().getY_offset()) && 
				 handler.getMouseManager().isLeftPressed()) {
			// Down
			attacking = true;
			ar.x = cb.x + cb.width/2 - ar_size / 2;
			ar.y = cb.y + cb.height;
			att_down = true;
		}
		
		else if (handler.getMouseManager().getMouseX() <= (int)(cb.x - handler.getGameCamera().getX_offset()) &&
				 handler.getMouseManager().getMouseY() >= (int)(cb.y - handler.getGameCamera().getY_offset()) &&
				 handler.getMouseManager().getMouseY() <= (int)(cb.y + cb.height - handler.getGameCamera().getY_offset()) &&
				 handler.getMouseManager().isLeftPressed()) {
			//Left
			attacking = true;
			ar.x = cb.x - ar_size;
			ar.y = cb.y + cb.height/2 - ar_size/2 ;
			att_left = true;
		}

		else if (handler.getMouseManager().getMouseX() >= (int)(cb.x + cb.width - handler.getGameCamera().getX_offset()) &&
				 handler.getMouseManager().getMouseY() >= (int)(cb.y - handler.getGameCamera().getY_offset()) &&
				 handler.getMouseManager().getMouseY() <= (int)(cb.y + cb.height - handler.getGameCamera().getY_offset()) &&
				 handler.getMouseManager().isLeftPressed()) {
			// Right
			attacking = true;
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height/2 - ar_size/2 ;
			att_right = true;
		}
		else {
			return;
		}
		attack_timer = 0;
		for (Entity e: handler.getWorld().getEntity_manager().getEntities()) {
			if (e.equals(this)){
				continue;
			}
			if (e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				return;
			}
		}
		
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
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
	
	@Override
	public float itemDropX(float x, int width) {
		Random rand = new Random();
		int x_offset = rand.nextInt(width);
		return x + x_offset;
		
	}

	@Override
	public float itemDropY(float y, int height) {
		Random rand = new Random();
		int y_offset = rand.nextInt(height);
		return y + y_offset;
	}
	
	@Override
	public void die() {
		
		
	}
	
	// Getters and Setters
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

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
		return null;
		
		
	}

}
