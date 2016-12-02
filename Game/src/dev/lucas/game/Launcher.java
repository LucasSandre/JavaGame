package dev.lucas.game;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class Launcher {
	public static void main(String[] args) {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();				
		int height = gd.getDisplayMode().getHeight();
		
		Game game = new Game("Tile Game!",width,height);
		game.start();
	}

}
