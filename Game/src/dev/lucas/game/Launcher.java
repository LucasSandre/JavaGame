package dev.lucas.game;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/** 
 * <h1>Launcher</h1>
 * <p>The Launcher class launches the game to your screen height and width.</p>
 * **/
public class Launcher {
	
	public static void main(String[] args) {
	
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		System.out.println(width + "  " + height);
		
		Game game = new Game("Tile Game!",width-2,height-20);
		game.start();
	}

}
