package dev.lucas.game.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/** 
 * <i><b>UIText</b></i>
 * <pre> public class UIText extends UIObject</pre>
 * <p>This class defines all the necessary methods and variables for an UIText</p>
 * @see {@link dev.lucas.game.ui.UIObject UIObject}
 * **/
public class UIText extends UIObject{

	// Initializes a Font, Click Listener and a String
	private Font font;
	private ClickListener clicker;
	private String str;
	private Color colour;
	
	// The Class Constructor takes in an x and y
	/**
	 * <i><b>UIText</b></i>
	 * <pre>	public UIText(float x, 
	 *                      float y, 
	 *                        int width, 
	 *                        int height, 
	 *                       Font font, 
	 *                      Color colour, 
	 *                     String str, 
	 *              ClickListener clicker)</pre>
	 * <p>The class constructor passed the parsed x, y, width, and height variables to the super constructor and saves the rest of the parsed values into variables.</p>
	 * @param Float x,
	 * @param Float y,
	 * @param Int width,
	 * @param Int height,
	 * @param Font font,
	 * @param Color colour,
	 * @param String str,
	 * @param ClickListner clicker
	 * @see {@link dev.lucas.game.ui.ClickListner ClickListner}
	 * **/
	public UIText(float x, float y, int width, int height, Font font, Color colour, String str, ClickListener clicker) {
		super(x,y,width,height);
		this.font = font;
		this.clicker = clicker;
		this.str = str;
		this.colour = colour;
	}

	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This object does not need to tick.</p>
	 * @param None
	 * @return None
	 * **/
	@Override
	public void tick() {
		// Does not tick
	}

	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render()</pre>
	 * <p>This method sets the font, colour, and draws the String from the respective parsed values.</p>
	 * @param
	 * @return None
	 * **/
	@Override
	public void render(Graphics g) {
		// Sets the current font, color, and draws the String
		g.setFont(font);
		g.setColor(colour);
		g.drawString(str, (int) x, (int) y);
	}

	/**
	 * <i><b>onClick</b></i>
	 * <pre>	public void onClick()</pre>
	 * <p>This method calls the parsed ClickListner's onClick method.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.ui.ClickListner ClickListner}
	 * **/
	@Override
	public void onClick() {
		// When called runs the clickers onClick() method
		clicker.onClick();
	}

}
