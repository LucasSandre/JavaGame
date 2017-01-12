package dev.lucas.game.display;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	// initializing private variables for the JFrame, Canvas, a String and two int's
	private JFrame frame;
	private Canvas canvas;

	private String title;
	private int width,height;
	
	// The Display constructor when given its requirements, Sets the parsed values to private ones in the class and create the display trough a method.
	public Display(String title, int width,int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	private void createDisplay() {
		
		// Creates a new JFrame Object. Sets the size equal to the value stored in variables width and height.
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Sets the window display flags so that is is not re-sizable, its sets it to visible and tries to center the window on the screen.
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// Creates a new Canvas Object, sets the preferred size and min and max sizes equal to variables width and height, and sets focusable to false.
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setMaximumSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width,height));
		canvas.setFocusable(false);

		// Adds the canvas to the frame and packs it.
		frame.add(canvas);
		frame.pack();
	}
	
	// Getters for canvas and JFrame.
	public Canvas getCanvas() {
		return canvas;
	}
	public JFrame getFrame(){
		return frame;
	}
}
