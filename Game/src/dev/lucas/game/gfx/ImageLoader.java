package dev.lucas.game.gfx;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
		public static BufferedImage loadImage(String path){
			// Tries to get an image at a location passed through the method and returs it
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
