/**
 * 
 */
package dev.lucas.game.ui;

import java.awt.Graphics;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Assets;

/** 
 * <i><b>UIPlayerManaStatusBar</b></i>
 * <pre> public class UIPlayerManaStatusBar extends UIObject</pre>
 * <p>This class defines all the necessary methods and variables for (a/an) UIPlayerManaStatusBar</p>
 * @see {@link dev.lucas.game.UIObject UIObject}
 * **/
public class UIPlayerEnergyStatusBar extends UIObject{

	private Handler handler;
	private int player_energy;
	private int bar_index;
	private float factorx, factory;
	
	/**
	 * <i><b> UIPlayerManaStatusBar</b></i>
	 * <pre>	public UIPlayerManaStatusBar()</pre>
	 * <p>The class constructor passes the x, y, width, and height values to the super constructor and saves the max value and handler.</p>
	 * @param
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public UIPlayerEnergyStatusBar(Handler handler, float x, float y, int width, int height) {
		super(x, y, width, height);
		this.handler = handler;
		this.factorx = width/Assets.bar_holder.getWidth();
		this.factory = height/Assets.bar_holder.getHeight();
	}

	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This tick method updates the bars variables</p>
	 * @param
	 * @return
	 * **/
	@Override
	public void tick() {
		player_energy = handler.getWorld().getEntity_manager().getPlayer().energy;
		bar_index = (int) Math.round(Assets.mana_bar.length * ((float) (player_energy)/(float)(handler.getWorld().getEntity_manager().getPlayer().MAX_ENERGY)))-1;	
	}

	/**
	 * <i><b>render</b></i>
	 * <pre>	public render()</pre>
	 * <p>This method draws the bar holder and then the bar.</p>
	 * @param
	 * @return 
	 * **/
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bar_holder, (int)x, (int)y, width, height, null);
		g.drawImage(Assets.mana_bar[bar_index], (int)(x+(16*factorx)), (int)(y), (int)(Assets.health_bar[bar_index].getWidth()*factorx), (int)(height), null);
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
