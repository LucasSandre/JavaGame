package dev.lucas.game.utils;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/** 
 * <i><b>Utils</b></i>
 * <pre> public class Utils</pre>
 * <p>This class defines all the necessary methods and variables for a general utility class.</p>
 * **/
public class Utils {
	
	/**
	 * <i><b>loadFileAsString</b></i>
	 * <pre>	public static String loadFileAsString(String path)</pre>
	 * <p>This method grabs a file indicated from the parsed string and converts it into a large string. </p>
	 * @param
	 * @return String
	 * **/
	public static String loadFileAsString(String path) {
		// turns the content of a file into a large string that goes to a new line when the files does.
		StringBuilder builder = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line+"\n");
			}
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	/**
	 * <i><b>parseInt</b></i>
	 * <pre>	public int parseInt(String num)</pre>
	 * <p>This method returns an int of the parsed String. If the String contains non-numeric characters it reterns 0.</p>
	 * @param
	 * @return int
	 * **/
	public static int parseInt(String num) {
		// converts a string into a number and catches if the string given is not all numbers.
		try{
			return Integer.parseInt(num);
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * <i><b>copyImage</b></i>
	 * <pre>	public static BufferedImage copyImage(BufferedImage img)</pre>
	 * <p>This method makes a copy of the parsed BufferedImage.</p>
	 * @param
	 * @return BufferedImage
	 * **/
	public static BufferedImage copyImage(BufferedImage img) {
		ColorModel cm = img.getColorModel();
		boolean isAlphaPreMultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = img.copyData(img.getRaster().createCompatibleWritableRaster());
		return new BufferedImage(cm, raster, isAlphaPreMultiplied, null);
	}
	
	/**
	 * <i><b>copyImages</b></i>
	 * <pre>	public BufferedImage[] copyImages(BufferedImage[] imgs)</pre>
	 * <p>This method delegates to the copyImage(img) method for each of the images stored in the array.</p>
	 * @param
	 * @return BufferedImage[]
	 * **/
	public static BufferedImage[] copyImages(BufferedImage[] imgs) {
		BufferedImage[] copy = new BufferedImage[imgs.length];
		for (int i = 0; i < imgs.length; i++) {
			copy[i] = copyImage(imgs[i]);
		}
		return copy;
	}
	
	/**
	 * <i><b>rotateImages</b></i>
	 * <pre>	public static BufferedImage[] rotateImages(BufferedImage imgs[], 
	 *                                                          double angle)</pre>
	 * <p>This is a delegator method for the method 'rotateImage' but this method allows for a whole array to be rotated.</p>
	 * @param
	 * @return BufferedImage[]
	 * **/
	public static BufferedImage[] rotateImages(BufferedImage imgs[], double angle) {
		BufferedImage[] rotated_copy = copyImages(imgs);
		for (int i = 0; i < imgs.length; i++) {
			rotated_copy[i] = rotateImage(rotated_copy[i], angle);
		}
		return rotated_copy;
		
	}
	
	/**
	 * <i><b>rotateImage</b></i>
	 * <pre>	public static BufferedImage rotateImage(BufferedImage img, 
	 *                                                       double angle)</pre>
	 * <p>This method rotates the parsed image to the parsed angle.</p>
	 * @param
	 * @return BufferedImage
	 * @see {@link dev.lucas.game.utils}
	 * **/
	public static BufferedImage rotateImage(BufferedImage img, double angle) {
		int w = img.getWidth();
		int h = img.getHeight();
		AffineTransform tx = new AffineTransform();
		tx.translate(w/2, h/2);
		tx.rotate(angle);
		tx.scale(1, 1);
		tx.translate(-w/2, -h/2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(img, null);
		
	}
}
