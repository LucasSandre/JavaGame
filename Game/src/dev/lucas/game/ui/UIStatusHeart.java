/**
 * 
 */
package dev.lucas.game.ui;

import java.awt.Graphics;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Animation;
import dev.lucas.game.gfx.Assets;

/** 
 * <i><b>UIHeart</b></i>
 * <pre> public class UIHeart</pre>
 * <p>This class defines all the necessary methods and variables for an UIStatusHeart</p>
 * @see {@link dev.lucas.game.ui.UIObject UIObject}
 * **/
public class UIStatusHeart extends UIObject {

	private Animation anim_near_death;
	private Handler handler;
	private int speed = 500;
	
	private int player_health;
	private int health_condition1 = 0;
	private int health_condition2 = 0;
	private int health_condition3 = 0;
	
	/**
	 * <i><b>UIHeart</b></i>
	 * <pre>	public UIHeart(float x, 
	 * 						 float y, 
	 *                         int width, 
	 *                         int height</pre>
	 * <p>The class constructor passes the parsed values to the super constructor and creates the Animation.</p>
	 * @param Float x,
	 * @param Float y,
	 * @param Int width,
	 * @param Int height
	 * @see {@link dev.lucas.game.gfx.Animation Animation}
	 * **/
	public UIStatusHeart(Handler handler, float x, float y, int width, int height) {
		super(x, y , width, height);
		this.handler = handler;	
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 0;
		bounds.height = 0;
		
		anim_near_death = new Animation(speed, Assets.heart);
	}

	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This method ticks the animation based on the players health.</p>
	 * @param None
	 * @return None
	 * **/
	@Override
	public void tick() {
		player_health = handler.getWorld().getEntity_manager().getPlayer().getHealth();
		if (health_condition1 == 0 || health_condition2 == 0 || health_condition3 == 0) {
			health_condition1 = (handler.getWorld().getEntity_manager().getPlayer().getMaxHealth()/2);
			health_condition2 = (handler.getWorld().getEntity_manager().getPlayer().getMaxHealth()/4);
			health_condition3 = (handler.getWorld().getEntity_manager().getPlayer().getMaxHealth()/10);
		}
		if (player_health <= health_condition1 && player_health > health_condition2) {
			anim_near_death.setSpeed(500);
			anim_near_death.tick();
		}
		else if (player_health <= health_condition2 && player_health > health_condition3) {
			anim_near_death.setSpeed(250);
			anim_near_death.tick();
		}
		else if (player_health <= health_condition3 && player_health > 0) {
			anim_near_death.setSpeed(125);
			anim_near_death.tick();
		}
		else {
			anim_near_death.tick();
			anim_near_death.setIndex(0);
		}
		
	}

	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p></p>
	 * @param
	 * @return None 
	 * **/
	@Override
	public void render(Graphics g) {
		g.drawImage(anim_near_death.getCurrentFrame(), (int)(x), (int)(y), width, height, null);
		
	}

	/**
	 * <i><b>onClick</b></i>
	 * <pre>	public onClick()</pre>
	 * <p></p>
	 * @param
	 * @return 
	 * @see {@link dev.lucas.game.ui}
	 * **/
	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}
	
}
