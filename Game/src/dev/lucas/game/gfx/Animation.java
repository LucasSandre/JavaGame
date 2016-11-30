package dev.lucas.game.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	private int speed, index;
	private long last_time;
	private long timer = 0;
	private BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		last_time = System.currentTimeMillis();
	}
	
	public void tick() {
		timer += System.currentTimeMillis() - last_time;
		last_time = System.currentTimeMillis();
	
		if (timer > speed) {
			index++;
			timer = 0;
			if (index >=frames.length){
				index = 0;
			}
		}
	}
	
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
