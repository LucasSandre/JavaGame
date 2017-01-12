package dev.lucas.game.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject{

	// Initializes a image array and a ClickListener
	private BufferedImage[] images;
	private ClickListener clicker;
	
	// The Class Constructor takes in a x , y, width, height, a Buffered Image Array, and a ClickListener.
		// Passes the x, y, width and height variables to the super constructor.
		// Stores the Image array and ClickListener
	public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);
		this.images = images;
		this.clicker = clicker;
	}

	@Override
	public void tick() {
		// Does not Tick
	}

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

	@Override
	public void onClick() {
		// Runs the ClickListeners onClick() method.
		clicker.onClick();
	}

}
