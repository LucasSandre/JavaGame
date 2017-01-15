package dev.lucas.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/** 
 * <i><b>ImageLoader</b></i>
 * <pre> public class ImageLoader</pre>
 * <p>The ImageLoader class is a utility class for getting images.</p>
 * **/
public class ImageLoader {
	
		/**
		 * <i><b>loadImage</b></i>
		 * <pre>	public static BufferedImage loadImage(String path)</pre>
		 * <p>This method takes in a path, tries to find the image and returns it.</p>
		 * @param
		 * @return BufferedImage
		 * **/
		public static BufferedImage loadImage(String path) {
			// Tries to get an image at a location passed through the method and returns it
				// If an IOException is raised exits the game.
			try {
				return ImageIO.read(ImageLoader.class.getResource(path));
			}
			catch (IOException e) {
				System.exit(1);
			}
			return null;
		}
}
