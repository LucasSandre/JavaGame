package dev.lucas.game.gfx;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.InputStream;


public class FontLoader {

	// Initializes private static arrays and Variables with types Font, InputStream, and Font metrics.
	private static Font[] font;
	private static InputStream is;
	private static FontMetrics[] fm ;

	
	public static Font[] loadFont(String path, int size) {
		// Creates a font array to size 3
		font = new Font[3];
		
		// Tries to load the font at the size parsed into the method, and at each one of the states (i.e. bold, basic, italic) and stored them in the font array.
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
			// Cathes any exeption prints the stacktrace and stores instead a serif font in the font array.
			e.printStackTrace();
			System.err.println(path + " not found");
			font[0] = new Font("serif", Font.PLAIN, size);
			font[1] = new Font("serif", Font.ITALIC, size);
			font[2] = new Font("serif", Font.BOLD, size);
		}
		return font;
	}
	public static FontMetrics[] loadFontMetrics(Graphics g, Font[] font) {
		// Creates the font metrics from a font parsed into the method and returns a FontMetrics Array.
		fm = new FontMetrics[3];
		fm[0] = g.getFontMetrics(font[0]);
		fm[1] = g.getFontMetrics(font[1]);
		fm[2] = g.getFontMetrics(font[2]);
		return fm;
		
	}
	
}
