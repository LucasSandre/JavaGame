/**
 * 
 */
package dev.lucas.game.ui;

import java.awt.Color;
import java.awt.Graphics;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Assets;

/** 
 * <i><b>UIStatusHealthText</b></i>
 * <pre> public class UIStatusHealthText extends UIObject</pre>
 * <p>This class defines all the necessary methods and variables for an UIStatusHealthText</p>
 * @see {@link }
 * **/
public class UIStatusHealthText extends UIObject {

	private Handler handler;
	private int player_health;
	private int player_max_health;
	private String str;
	
	/**
	 * <i><b> UIStatusHealthText</b></i>
	 * <pre>	public UIStatusHealthText(Handler handler, 
	 *                                    float x, 
	 *                                    float y)</pre>
	 * <p>This class constructor passes the x and y to the super constructor and saves the handler.</p>
	 * @param Handler handler,
	 * @param Float x,
	 * @param Float y
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public UIStatusHealthText(Handler handler, float x, float y) {
		super(x, y, 0, 0);
		this.handler = handler;
	}

	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This method updates the text.</p>
	 * @param None
	 * @return  None
	 * **/
	@Override
	public void tick() {
		player_health = handler.getWorld().getEntity_manager().getPlayer().getHealth();
		player_max_health = handler.getWorld().getEntity_manager().getPlayer().getMaxHealth();
		str = new String(Integer.toString(player_health)+" / "+ Integer.toString(player_max_health));
	}

	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>This method draws the string.</p>
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
	 * <p>This method is unused.</p>
	 * @param
	 * @return 
	 * @see {@link dev.lucas.game.ui}
	 * **/
	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}

}
