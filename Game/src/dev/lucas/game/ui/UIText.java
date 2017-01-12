package dev.lucas.game.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UIText extends UIObject{

	// Initializes a Font, Click Listener and a String
	private Font font;
	private ClickListener clicker;
	private String str;
	private Color colour;
	
	// The Class Constructor takes in an x and y
	public UIText(float x, float y, int width, int height, Font font, Color colour, String str, ClickListener clicker) {
		super(x,y,width,height);
		this.font = font;
		this.clicker = clicker;
		this.str = str;
		this.colour = colour;
	}

	@Override
	public void tick() {
		// Does not tick
	}

	@Override
	public void render(Graphics g) {
		// Sets the current font, color, and draws the String
		g.setFont(font);
		g.setColor(colour);
		g.drawString(str, (int) x, (int) y);
	}

	@Override
	public void onClick() {
		// When called runs the clickers onClick() method
		clicker.onClick();
	}

}
