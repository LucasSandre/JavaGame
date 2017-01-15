package dev.lucas.game.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/** 
 * <i><b>UIImageButton</b></i>
 * <pre> public class UIImageButton extends UIObject</pre>
 * <p>This class defines all the necessary methods and variables for an UIImageButton</p>
 * @see {@link dev.lucas.game.ui.UIObject UIObject}
 * **/
public class UIImageButton extends UIObject{

	// Initializes a image array and a ClickListener
	private BufferedImage[] images;
	private ClickListener clicker;
	
	// The Class Constructor takes in a x , y, width, height, a Buffered Image Array, and a ClickListener.
		// Passes the x, y, width and height variables to the super constructor.
		// Stores the Image array and ClickListener
	/**
	 * <i><b> UIImageButton</b></i>
	 * <pre>	public UIImageButton(float x, 
	 *                             float y, 
	 *                               int width, 
	 *                               int height, 
	 *                   BufferedImage[] images, 
	 *                     ClickListener clicker)</pre>
	 * <p>The class constructor passes the x, y, width, and height values to the super constructor, and saves the images and ClickListner.</p>
	 * @param float x
	 * @param float y,
	 * @param int width,
	 * @param int height,
	 * @param BufferedImage[] images,
	 * @param ClickListener clicker
	 * @see {@link dev.lucas.game.ui.ClickListner ClickListner}
	 * **/
	public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);
		this.images = images;
		this.clicker = clicker;
	}

	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This UIObject does not tick.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.ui.UIObject UIObject}
	 * **/
	@Override
	public void tick() {
		// Does not Tick
	}

	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>This method renders one of the two UIObject's states based on iff the mouse is hovering the UIObject.</p>
	 * @param
	 * @return None 
	 * @see {@link dev.lucas.game.ui.UIObject UIObject}
	 * **/
	@Override
	public void render(Graphics g) {
		// Checks if the image is being hovered over and changes the image displayed accordingly
		if (hovering) {
			g.drawImage(images[1], (int) x, (int) y, width, height, null);
		}
		else {
			g.drawImage(images[0], (int) x, (int) y, width, height, null);
		}
		
	}

	/**
	 * <i><b>onClick</b></i>
	 * <pre>	public void onClick()</pre>
	 * <p>This method runs the method called 'onClick()' from the parsed ClickListner.</p>
	 * @param
	 * @return 
	 * @see {@link dev.lucas.game.ui.ClickListner ClickListner}
	 * **/
	@Override
	public void onClick() {
		// Runs the ClickListeners onClick() method.
		clicker.onClick();
	}

}
