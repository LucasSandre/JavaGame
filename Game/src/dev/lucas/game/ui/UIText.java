package dev.lucas.game.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UIText extends UIObject{

	private Font font;
	private ClickListener clicker;
	private String str;
	
	public UIText(float x, float y, int width, int height, Font font, String str, ClickListener clicker) {
		super(x,y,width,height);
		this.font = font;
		this.clicker = clicker;
		this.str = str;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(Color.cyan);
		g.drawString(str, (int) x, (int) y);
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}

}
