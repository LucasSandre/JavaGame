/**
 * 
 */
package dev.lucas.game.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Assets;

/** 
 * <i><b>UITextBox</b></i>
 * <pre> public class UITextBox extends UIObject</pre>
 * <p>This class defines all the necessary methods and variables for an UITextBox</p>
 * @see {@link dev.lucas.game.ui.UIObject UIObject}
 * **/
public class UITextBox extends UIObject {
	
	private boolean active = false;
	private Handler handler;
	private String text;
	private Font font;
	private FontMetrics fm;
	private long flash_timer, flash_speed, last_flash_timer;
	
	/**
	 * <i><b> UITextBox</b></i>
	 * <pre>	public UITextBox(Handler handler, 
	 * 						     float x, 
	 *                           float y, 
	 *                             int width, 
	 *                             int height, 
	 *                          String init_string)</pre>
	 * <p></p>
	 * @param Handler handler,
	 * @param Float x,
	 * @param Float y,
	 * @param Int width,
	 * @param Int height,
	 * @param String init_string
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public UITextBox(Handler handler, float x, float y, int width, int height, Font font, FontMetrics fm, String init_string) {
		super(x, y, width, height);
		this.handler = handler;
		this.text = init_string;
		this.font = font;
		this.fm = fm;
	}

	/**
	 * <i><b>tick</b></i>
	 * <pre>	public tick()</pre>
	 * <p></p>
	 * @param
	 * @return 
	 * @see {@link dev.lucas.game.ui}
	 * **/
	@Override
	public void tick() {
		System.out.println(active + "   " + handler.getKeyManager().inputing + "   " + text +"   " + text.length());
		if (handler.getMouseManager().isLeftPressed()) { 
			if (bounds.contains(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY())) {
				active = true;
				handler.getKeyManager().inputing = true;
				text = new String();
			}
			else {
				active = false;
				handler.getKeyManager().inputing = false;
			}
		}
		if (!active) {
			return;
		}
		if (text.length() > 0) {
			if (handler.getKeyManager().delete) {
				text = text.substring(0, text.length()-1);
			}
		}
		if (text.length() <=30) {
			text.concat(String.valueOf(handler.getKeyManager().getCurrentInput()));
		}
	}

	/**
	 * <i><b>render</b></i>
	 * <pre>	public render()</pre>
	 * <p></p>
	 * @param
	 * @return 
	 * @see {@link dev.lucas.game.ui}
	 * **/
	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(font);
		g.drawImage(Assets.input_box, (int) x, (int) y, width, height, null);
		g.drawString(text, (int) (x + fm.getHeight()-fm.getAscent()), (int) (y + (height-fm.getAscent())/2));
		
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
