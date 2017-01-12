package dev.lucas.game.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	// Initializes nessasary variables for an animation.
	private int speed, index;
	private long last_time;
	private long timer = 0;
	private BufferedImage[] frames;
	
	// The Class Constructor takes in a int and a buffered image array.
		// Stores the array and speed
		// sets the index to 0
		// generates the tick last time
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		last_time = System.currentTimeMillis();
	}
	
	public void tick() {
		// updates the timer variables
		timer += System.currentTimeMillis() - last_time;
		last_time = System.currentTimeMillis();
	
		// checks if the timer is greater than speed to advance the tick index.
		if (timer > speed) {
			index++;
			timer = 0;
			if (index >=frames.length){ // If at the end of the array reset the index to 0
				index = 0;
			}
		}
	}
	
	// Getters
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}

	public int getSpeed() {
		return speed;
	}

	public int getIndex() {
		return index;
	}
}
