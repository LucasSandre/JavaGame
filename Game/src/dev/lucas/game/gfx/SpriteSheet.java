package dev.lucas.game.gfx;
import java.awt.image.BufferedImage;

/** 
 * <i><b>SpriteSheet</b></i>
 * <pre> public class SpriteSheet</pre>
 * <p>This class defines all the necessary methods and variables for a SpriteSheet</p>
 * **/
public class SpriteSheet {
	// Initializes private BufferedImage variable
	private BufferedImage sheet;
	
	// The SpriteSheet constructor, it sets the buffered IMage passed to it to the variable sheet.
	/**
	 * <i><b> SpriteSheet</b></i>
	 * <pre>	public SpriteSheet()</pre>
	 * <p>The class constructor saves the passed image.</p>
	 * @param BufferedImage sheet
	 * **/
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	/**
	 * <i><b>crop</b></i>
	 * <pre>	public BufferedImage crop(int x, 
	 *                                  int y, 
	 *                                  int width, 
	 *                                  int height)</pre>
	 * <p>This method takes in an x and y position a width and height and returns a sub image of those dimensions.</p>
	 * @param
	 * @return BufferedImage
	 * @see {@link dev.lucas.game.gfx}
	 * **/
	public BufferedImage crop(int x, int y, int width, int height){
		// When parsed an x,y,width,height it returns a cropped image from the original image.
		return sheet.getSubimage(x, y, width, height);
	}
}
