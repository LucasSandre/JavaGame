/**
 * 
 */
package dev.lucas.game.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.lucas.game.Handler;

/** 
 * <i><b>UIPlayerManaStatusBar</b></i>
 * <pre> public class UIPlayerManaStatusBar extends UIObject</pre>
 * <p>This class defines all the necessary methods and variables for (a/an) UIPlayerManaStatusBar</p>
 * @see {@link dev.lucas.game.UIObject UIObject}
 * **/
public class UIPlayerEnergyStatusBar extends UIObject{

	private BufferedImage[] texture;
	private Handler handler;
	private int player_energy;
	private int bar_index;
	
	/**
	 * <i><b> UIPlayerManaStatusBar</b></i>
	 * <pre>	public UIPlayerManaStatusBar()</pre>
	 * <p></p>
	 * @param
	 * @see {@link dev.lucas.game.ui}
	 * **/
	public UIPlayerEnergyStatusBar(Handler handler, float x, float y, int width, int height, BufferedImage[] texture) {
		super(x, y, width, height);
		this.handler = handler;
		this.texture = texture;
	}

	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This tick method updates the bars variables</p>
	 * @param
	 * @return 
	 * @see {@link dev.lucas.game.ui}
	 * **/
	@Override
	public void tick() {
		player_energy = handler.getWorld().getEntity_manager().getPlayer().energy;
		bar_index = (int) (texture.length * ((float) (player_energy)/(float)(handler.getWorld().getEntity_manager().getPlayer().MAX_ENERGY)));	
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
		g.drawImage(Assets.bar_holder, x, y, width, height, null);
		g.drawImage(Assets.mana_bar[bar_index], x, y, width, height, null);
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
