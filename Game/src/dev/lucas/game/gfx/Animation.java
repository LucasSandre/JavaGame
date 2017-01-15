package dev.lucas.game.gfx;

import java.awt.image.BufferedImage;

/** 
 * <i><b>Animation</b></i>
 * <pre> public class Animation</pre>
 * <p>This class defines all the necessary methods and variables for an Animation.</p>
 * **/
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
	
	/**
	 * <i><b>Animation</b></i>
	 * <pre>	public Animation(int speed, 
	 *             BufferedImage[] frames)</pre>
	 * <p>The class constructor saves its parsed values, sets the frame index to 0, and initializes the timer.</p>
	 * @param Int speed,
	 * @param BufferedImage[] frames
	 * **/
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		last_time = System.currentTimeMillis();
	}
	
	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This method makes the animation go to the next frame when the timer reaches the speed set.</p>
	 * @param None
	 * @return void
	 * **/
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
	
	/**
	 * <i><b>getCurrentFrame</b></i>
	 * <pre>	public BufferedImage getCurrentFrame()</pre>
	 * <p>This method gets the current frame the Animation is in.</p>
	 * @param None
	 * @return BufferedImage
	 * **/
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}

	/**
	 * <i><b>getSpeed</b></i>
	 * <pre>	public int getSpeed()</pre>
	 * <p>Gets the Animation's speed.</p>
	 * @param None
	 * @return int
	 * **/
	public int getSpeed() {
		return speed;
	}

	/**
	 * <i><b>getIndex</b></i>
	 * <pre>	public int getIndex()</pre>
	 * <p>Gets the index of the Animation.</p>
	 * @param None
	 * @return int
	 * **/
	public int getIndex() {
		return index;
	}
}
