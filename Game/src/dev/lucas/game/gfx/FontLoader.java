package dev.lucas.game.gfx;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.InputStream;


public class FontLoader {

	private static Font[] font;
	private static InputStream is;
	private static FontMetrics[] fm ;
	private static Graphics g;
	
	public static Font[] loadFont(String path, int size) {
		font = new Font[3];
		try {
			is = FontLoader.class.getResourceAsStream(path);
			font[0] = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, size);
			is.close();
			
			is = FontLoader.class.getResourceAsStream(path);
			font[1] = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.ITALIC, size);
			is.close();
			
			is = FontLoader.class.getResourceAsStream(path);
			font[2] = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.BOLD, size);
			is.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println(path + " not found");
			font[0] = new Font("serif", Font.PLAIN, size);
			font[1] = new Font("serif", Font.ITALIC, size);
			font[2] = new Font("serif", Font.BOLD, size);
		}
		return font;
	}
	public static FontMetrics[] loadFontMetrics(Graphics g, Font[] font) {
		fm = new FontMetrics[3];
		fm[0] = g.getFontMetrics(font[0]);
		fm[1] = g.getFontMetrics(font[1]);
		fm[2] = g.getFontMetrics(font[2]);
		return fm;
		
	}
	
}
