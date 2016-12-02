package dev.lucas.game.entities.creatures;


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
	
	private Animation anim_down, anim_up, anim_right, anim_left;
	private Animation attack_anim_down, attack_anim_up, attack_anim_right, attack_anim_left;

	
	// For Auto Animations
	private boolean att_down  = false,
			        att_up  = false,
			        att_left  = false,
			        att_right = false;
	
	// Attack Timer
	private long last_attack_timer, attack_cooldown = 800, attack_timer = attack_cooldown;
	
	// Inventory
	private Inventory inventory;
	
	public Player(Handler handler,float x, float y) {
		super(handler,x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		bounds.x = 18;
		bounds.y = 24;
		bounds.width = 27;
		bounds.height = 39;
		
		// Animations
		
		anim_down = new Animation(250,Assets.player_down);
		anim_up = new Animation(250,Assets.player_up);
		anim_right = new Animation(250,Assets.player_right);
		anim_left = new Animation(250,Assets.player_left);
		
		attack_anim_down = new Animation(20,Assets.attack_down);
		attack_anim_up = new Animation(20,Assets.attack_up);
		attack_anim_right = new Animation(20,Assets.attack_right);
		attack_anim_left = new Animation(20,Assets.attack_left);
		
		inventory = new Inventory(handler);
	}

	@Override
	public void tick() {
		
		// Animations
		anim_down.tick();
		anim_up.tick();
		anim_right.tick();
		anim_left.tick();
		// Attack Animations
		if (att_up) {
			attack_anim_up.tick();
			if (attack_anim_up.getIndex() == 0){
				att_up = false;
			}
				
		}
		else if (att_down) {
			attack_anim_down.tick();
			if (attack_anim_down.getIndex() == 0){
				att_down = false;
			}
		}
		else if (att_left) {
			attack_anim_left.tick();
			if (attack_anim_left.getIndex() == 0){
				att_left = false;
			}
		}
		else if (att_right) {
			attack_anim_right.tick();
			if (attack_anim_right.getIndex() == 0){
				att_right = false;
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
		g.drawImage(getCurrentPlayerAnimationFrame(), (int) (x - handler.getGameCamera().getX_offset()), (int) (y-handler.getGameCamera().getY_offset()), width ,height, null);
		if (att_up) {
			g.drawImage(getCurrentAttackAnimationFrame(),  (int) (x - handler.getGameCamera().getX_offset()+ (width-width/2.0f)/2.0f), (int) (y-handler.getGameCamera().getY_offset()-height/3.0f), (int) (width/2.0f) , (int)(height/2.0f), null);
		}
		else if (att_down){
			g.drawImage(getCurrentAttackAnimationFrame(),  (int) (x - handler.getGameCamera().getX_offset()+ (width-width/2.0f)/2.0f), (int) (y-handler.getGameCamera().getY_offset()+height*(7.0f/8.0f)), (int) (width/2.0f) , (int)(height/2.0f), null);
		}
		else if (att_left){
			g.drawImage(getCurrentAttackAnimationFrame(),  (int) (x - handler.getGameCamera().getX_offset()-5), (int) (y-handler.getGameCamera().getY_offset() + (height-height/2.0f)/2.0f), (int) (width/2.0f) , (int)(height/2.0f), null);
		}
		else if (att_right){
			g.drawImage(getCurrentAttackAnimationFrame(),  (int) (x - handler.getGameCamera().getX_offset()+bounds.width+10), (int) (y-handler.getGameCamera().getY_offset()+ (height-height/2.0f)/2.0f), (int) (width/2.0f) , (int)(height/2.0f), null);
		}
		
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
		
		int ar_size = 40;
		ar.width = ar_size;
		ar.height = ar_size;
		
		if (handler.getKeyManager().a_up){
			ar.x = cb.x + cb.width/2 - ar_size / 2;
			ar.y = cb.y - ar_size;
			att_up = true;
		}
		else if (handler.getKeyManager().a_down){
			ar.x = cb.x + cb.width/2 - ar_size / 2;
			ar.y = cb.y + cb.height;
			att_down = true;
		}
		else if (handler.getKeyManager().a_left){
			ar.x = cb.x - ar_size;
			ar.y = cb.y + cb.height/2 - ar_size/2 ;
			att_left = true;
		}
		else if (handler.getKeyManager().a_right){
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height/2 - ar_size/2 ;
			att_right = true;
		}else {
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
		if (handler.getKeyManager().up) 
			yMove = -speed;
		if (handler.getKeyManager().down)
			yMove = speed;
		if (handler.getKeyManager().left)
			xMove = -speed;
		if (handler.getKeyManager().right)
			xMove = speed;
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
		if (xMove < 0){
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
		return Assets.player_still;
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
		return null;
		
		
	}

}
