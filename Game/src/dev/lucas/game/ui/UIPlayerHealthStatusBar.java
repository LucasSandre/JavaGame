/**
 * 
 */
package dev.lucas.game.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.lucas.game.Handler;
import dev.lucas.game.entities.Entity;

/** 
 * <i><b>UIStatusBar</b></i>
 * <pre> public class UIStatusBar extends UIObject</pre>
 * <p>This class defines all the necessary methods and variables for (a/an) UIStatusBar</p>
 * @see {@link }
 * **/
public class UIPlayerHealthStatusBar extends UIObject {

	private Handler handler;
	private BufferedImage[] texture;
	private int player_health;
	private int bar_index;
	
	/**
	 * <i><b> UIStatusBar</b></i>
	 * <pre>	public UIStatusBar(Handler handler, 
	 *                             float x, 
	 *                             float y, 
	 *                               int width, 
	 *                               int height, 
	 *                     BufferedImage texture)</pre>
	 * <p>The class constructor passes the x, y, width, and height values to the super constructor and saves the max value and texture and
	 *  handler.</p>
	 * @param
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public UIPlayerHealthStatusBar(Handler handler, float x, float y, int width, int height, BufferedImage[] texture) {
		super(x, y, width, height);
		this.handler = handler;
		this.texture = texture;
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
		bar_index = (int) (texture.length * ((float)(player_health)/(float)(handler.getWorld().getEntity_manager().getPlayer().MAX_HEALTH)));
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
		g.drawImage(Assets.bar_holder, x, y, width, height, null);
		g.drawImage(Assets.health_bar[bar_index], x, y, width, height, null);
		
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
