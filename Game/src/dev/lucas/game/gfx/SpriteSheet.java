package dev.lucas.game.gfx;
import java.awt.image.BufferedImage;

public class SpriteSheet {
	// Initializes private BufferedImage variable
	private BufferedImage sheet;
	
	// The SpriteSheet constructor, it sets the buffered IMage passed to it to the variable sheet.
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width,int height){
		// When parsed an x,y,width,height it returns a cropped image from the original image.
		return sheet.getSubimage(x, y, width, height);
	}
}
