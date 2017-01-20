/**
 * 
 */
package dev.lucas.game.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Animation;
import dev.lucas.game.gfx.Assets;

/** 
 * <i><b>UIStatusEnergy</b></i>
 * <pre> public class UIStatusEnergy extends UIObject</pre>
 * <p>This class defines all the necessary methods and variables for an UIStatusEnergy object.</p>
 * @see {@link dev.lucas.game.ui.UIObject UIObject}
 * **/
public class UIStatusEnergy extends UIObject {

	private Animation anim_near_out;
	private Handler handler;
	private int speed = 500;
	
	private int player_energy;
	private int energy_condition1 = 0;
	private int energy_condition2 = 0;
	private int energy_condition3 = 0;
	
	/**
	 * <i><b> UIStatusEnergy</b></i>
	 * <pre>	public UIStatusEnergy(Handler handler, 
	 *                                float x, 
	 *                                float y, 
	 *                                  int width, 
	 *                                  int height</pre>
	 * <p>The class constructor takes the x, y, width, and height to the super constructor, saves the handler, creates the bounding box and
	 *  animation.</p>
	 * @param
	 * @see {@link dev.lucas.game.Handler handler}
	 * **/
	public UIStatusEnergy(Handler handler, float x, float y, int width, int height) {
		super(x, y, width, height);
		this.handler = handler;
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 0;
		bounds.height = 0;
		
		anim_near_out = new Animation(speed, Assets.energy);
	}

	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This method makes lets the energy symbol animate if the player's energy gets low.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.entities.creatures.Player Player]
	 * **/
	@Override
	public void tick() {
		player_energy = handler.getWorld().getEntity_manager().getPlayer().energy;
		if (energy_condition1 == 0 || energy_condition2 == 0 || energy_condition3 == 0) {
			energy_condition1 = (handler.getWorld().getEntity_manager().getPlayer().MAX_ENERGY/2);
			energy_condition2 = (handler.getWorld().getEntity_manager().getPlayer().MAX_ENERGY/4);
			energy_condition3 = (handler.getWorld().getEntity_manager().getPlayer().MAX_ENERGY/10);
		}
		if (player_energy <= energy_condition1 && player_energy > energy_condition2) {
			anim_near_out.setSpeed(speed);
			anim_near_out.tick();
		}
		else if (player_energy <= energy_condition2 && player_energy > energy_condition3) {
			anim_near_out.setSpeed(speed/2);
			anim_near_out.tick();
		}
		else if (player_energy <= energy_condition3 && player_energy > 0) {
			anim_near_out.setSpeed(speed/4);
			anim_near_out.tick();
		}
		else{
			anim_near_out.tick();
			anim_near_out.setIndex(0);
		}
		
	}

	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>This method renders the UIObject on screen.</p>
	 * @param
	 * @return None
	 * @see {@link dev.lucas.game.ui.UIObject UIObject}
	 * **/
	@Override
	public void render(Graphics g) {
		g.drawImage(anim_near_out.getCurrentFrame(), (int) x, (int) y, width, height, null);
	}

	/**
	 * <i><b>onClick</b></i>
	 * <pre>	public void onClick()</pre>
	 * <p>this method is unused.</p>
	 * @param None
	 * @return None
	 * **/
	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}

}
