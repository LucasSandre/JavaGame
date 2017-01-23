/**
 * 
 */
package dev.lucas.game.ui;

import java.awt.Graphics;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Assets;

/** 
 * <i><b>UIStatusBar</b></i>
 * <pre> public class UIStatusBar extends UIObject</pre>
 * <p>This class defines all the necessary methods and variables for (a/an) UIStatusBar</p>
 * @see {@link }
 * **/
public class UIPlayerHealthStatusBar extends UIObject {

	private Handler handler;
	private int player_health;
	private int bar_index;
	private float factorx, factory;
	
	/**
	 * <i><b> UIStatusBar</b></i>
	 * <pre>	public UIStatusBar(Handler handler, 
	 *                             float x, 
	 *                             float y, 
	 *                               int width, 
	 *                               int height)</pre>
	 * <p>The class constructor passes the x, y, width, and height values to the super constructor and saves the max value and handler.</p>
	 * @param
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public UIPlayerHealthStatusBar(Handler handler, float x, float y, int width, int height) {
		super(x, y, width, height);
		this.handler = handler;
		this.factorx = width/Assets.bar_holder.getWidth();
		this.factory = height/Assets.bar_holder.getHeight();
	}

	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>The tick method updates the variables for the bar.</p>
	 * @param
	 * @return None
	 * **/
	@Override
	public void tick() {
		player_health = handler.getWorld().getEntity_manager().getPlayer().getHealth();
		bar_index = (int) Math.ceil((Assets.health_bar.length * ((float)(player_health)/(float)(handler.getWorld().getEntity_manager().getPlayer().getMaxHealth())))) - 1;		
		if (bar_index < 0) {
			bar_index = 0;
		}
		if (bar_index > Assets.health_bar.length-1) {
			bar_index = Assets.health_bar.length-1;
		}
	}

	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>The render method draws the bar holder and then the bar.</p>
	 * @param
	 * @return None
	 * **/
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bar_holder, (int)x, (int)y, width, height, null);
		g.drawImage(Assets.health_bar[bar_index], (int)(x+(16*factorx)), (int)(y), (int)(Assets.health_bar[bar_index].getWidth()*factorx), (int)(height), null);
		g.drawImage(Assets.bar_overlay, (int)x, (int)y, width, height, null);
		
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
