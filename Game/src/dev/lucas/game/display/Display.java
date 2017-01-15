package dev.lucas.game.display;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/** 
 * <i><b>Display</b></i>
 * <pre>	public class Display</pre>
 * <p>This class creates and stores the JFrame and Canvas. It also sets the Windows title, size, and other configurations.</p>

 * **/
public class Display {
	// initializing private variables for the JFrame, Canvas, a String and two int's
	private JFrame frame;
	private Canvas canvas;

	private String title;
	private int width,height;
	
	/** 
	 * <i><b>Display</b></i>
	 * <pre>	public Display(String title,
	 *                          int width,
	 *                          int height)</pre>
	 * <p>The Display constructor takes in a String and two int's. The constructor saves these passed values and creates the display with these values.</p>
	 * @param String For the title of the window.
	 * @param Int width for the size of the window.
	 * @param Int height for the size of the window.
	 * @return None
	 * **/
	public Display(String title, int width,int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	/** 
	 * <i><b>createDisplay</b></i>
	 * <pre>	private void createDisplay()</pre>
	 * <p>This method creates a new JFrame object and sets the size of the JFrame to the values in the 'width' and 'height' variables. This method also makes the window 
	 * not resizable, centers the window on the screen, and makes the frame visible. Additionally this method creates a new Canvas object, sets the preferred, minimum, 
	 * and maximum size equal to the values in the 'width' and 'height' variables, and also sets the window not to be focusable. Finally, it adds the canvas to the frame 
	 *  and packs the frame.</p>
	 * @param None
	 * @return None
	 * **/
	private void createDisplay() {
		
		// Creates a new JFrame Object. Sets the size equal to the value stored in variables width and height. Sets the operation when the 'X' button on a window is used.
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
	/** 
	 * <i><b>getCanvas</b></i>
	 * <pre>	public Canvas getCanvas()</pre>
	 * <p>Gets the Canvas object stored in the 'canvas' variable.</p>
	 * @param None
	 * @return Canvas
	 * **/
	public Canvas getCanvas() {
		return canvas;
	}
	
	/** 
	 * <i><b>getFrame</b></i>
	 * <pre>	public JFrame getFrame()</pre>
	 * <p>Gets the JFrame object stored in the 'frame' variable.</p>
	 * @param None
	 * @return JFrame
	 * **/
	public JFrame getFrame(){
		return frame;
	}

}
