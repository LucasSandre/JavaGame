package dev.lucas.game;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/** 
 * <h1>Launcher</h1>
 * <p>The Launcher class launches the game to your screen height and width.</p>
 * **/
public class Launcher {
	
	public static void main(String[] args) {
	
		
		//Uncommenting the following code and adding width and height for the game constructor makes the game width default to your screen size.
		//GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		//int width = gd.getDisplayMode().getWidth();
		//int height = gd.getDisplayMode().getHeight();
		
		//System.out.println(width + "  " + height);
		
		Game game = new Game("Tile Game!",1366,768);
		game.start();
	}

}
