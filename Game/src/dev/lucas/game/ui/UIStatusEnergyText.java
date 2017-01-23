/**
 * 
 */
package dev.lucas.game.ui;

import java.awt.Color;
import java.awt.Graphics;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Assets;

/** 
 * <i><b>UIStatusManaText</b></i>
 * <pre> public class UIStatusManaText</pre>
 * <p>This class defines all the necessary methods and variables for (a/an) UIStatusManaText</p>
 * @see {@link }
 * **/
public class UIStatusEnergyText extends UIObject {

	private Handler handler;
	private int player_energy;
	private String str;
	
	/**
	 * <i><b> UIStatusManaText</b></i>
	 * <pre>	public UIStatusManaText(Handler handler, 
	 *                                  float x, 
	 *                                  float y)</pre>
	 * <p>This class constructor takes the passed x and y and passes them to the super constructor and saves the handler.</p>
	 * @param Handler handler,
	 * @param Float x,
	 * @param Float y
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public UIStatusEnergyText(Handler handler, float x, float y) {
		super(x, y, 0, 0);
		this.handler = handler;
	}

	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This method updates the player text.</p>
	 * @param None
	 * @return  None
	 * **/
	@Override
	public void tick() {
		player_energy = handler.getWorld().getEntity_manager().getPlayer().energy;
		str = new String(Integer.toString(player_energy)+" / 100");
	}

	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>this method renders the string.</p>
	 * @param 
	 * @return None 
	 * **/
	@Override
	public void render(Graphics g) {
		g.setFont(Assets.font_32[0]);
		g.setColor(Color.WHITE);
		g.drawString(str, (int) x, (int) y);
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
