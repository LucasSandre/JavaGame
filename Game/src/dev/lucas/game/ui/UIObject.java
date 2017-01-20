package dev.lucas.game.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;


/** 
 * <i><b>UIObject</b></i>
 * <pre> public abstract class UIObject</pre>
 * <p>This class defines all the necessary methods and variables for (an UIObject</p>
 * **/
public abstract class UIObject{

	// Initializes necessary variables for a UI Object
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean hovering = false;
	
	// The Class Constructor, takes in an x,y,width, and height
		// Stores the passed values
		// Creates bounding box
	/**
	 * <i><b> UIObject</b></i>
	 * <pre>	public UIObject(float x, 
	 *                        float y, 
	 *                          int width, 
	 *                          int height)</pre>
	 * <p>The class constructor saves the parsed values into variables and creates the bounding box of the UIObject.</p>
	 * @param Float x,
	 * @param Float y,
	 * @param Int width,
	 * @param Int height
	 * **/
	public UIObject(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle((int) x,(int) y, width, height);
	}

	// Method all UI objects need
	/**
	 * <i><b>tick</b></i>
	 * <pre>	public abstract void tick()</pre>
	 * <p>This method is a required method for all UIObjects.</p>
	 * @param None
	 * @return void
	 * **/
	public abstract void tick();
	
	/**
	 * <i><b>render</b></i>
	 * <pre>	public abstract void render(Graphics g)</pre>
	 * <p>This method is a required method for all UIObjects.</p>
	 * @param
	 * @return void
	 * **/
	public abstract void render(Graphics g);
	
	/**
	 * <i><b>onClick</b></i>
	 * <pre>	public abstract void onClick()</pre>
	 * <p>This method is a required method for all UIObjects.</p>
	 * @param None
	 * @return void
	 * **/
	public abstract void onClick();
	
	/**
	 * <i><b>onMouseMove</b></i>
	 * <pre>	public void onMouseMove(MouseEvent e)</pre>
	 * <p>This method checks to see if the mouse is currently over the UIObject and if so it sets the hovering variable to true. Else it sets the hovering variable to false</p>
	 * @param
	 * @return void
	 * **/
	public void onMouseMove(MouseEvent e) {
		// Checks if the mouse is currently over the object
			// If the mouse is in the objects bounds it sets the hovering value to true
			// Else sets the value to false
		if (bounds.contains(e.getX(),e.getY())){
			hovering = true;
		}
		else {
			hovering = false;
		}
	}
	
	/**
	 * <i><b>onMouseRelease</b></i>
	 * <pre>	public void onMouseRelease(MouseEvent e)</pre>
	 * <p>This method is called when the mouse is released. This method checks to see if the mouse was over a UIObject and if so it runs the onClick() method. 
	 * Else does nothing.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.ui}
	 * **/
	public void onMouseRelease(MouseEvent e) {
		// Checks to see if the objects hovering value is true
			// If so, it runs onClick(); method
		if (hovering) {
			onClick();
		}
	}
	
	
	// Getters and Setters
	/**
	 * <i><b>getX</b></i>
	 * <pre>	public float getX()</pre>
	 * <p>Gets the x value of the UIObject.</p>
	 * @param None
	 * @return float
	 * **/
	public float getX() {
		return x;
	}

	/**
	 * <i><b>setX</b></i>
	 * <pre>	public void setX(float x)</pre>
	 * <p>Sets the x value of the UIObject.</p>
	 * @param
	 * @return void
	 * **/
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * <i><b>getY</b></i>
	 * <pre>	public float getY()</pre>
	 * <p>Gets the y value of the UIObject.</p>
	 * @param None
	 * @return float
	 * **/
	public float getY() {
		return y;
	}

	/**
	 * <i><b>setY</b></i>
	 * <pre>	public void setY(float y)</pre>
	 * <p>Sets the y value of the UIObject.</p>
	 * @param
	 * @return void
	 * **/
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * <i><b>getWidth</b></i>
	 * <pre>	public int getWidth()</pre>
	 * <p>Gets the width of the UIObject.</p>
	 * @param None
	 * @return int
	 * **/
	public int getWidth() {
		return width;
	}

	/**
	 * <i><b>setWidth</b></i>
	 * <pre>	public void setWidth(int width)</pre>
	 * <p>Sets the width of the UIObject.</p>
	 * @param
	 * @return void
	 * **/
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * <i><b>getHeight</b></i>
	 * <pre>	public int getHeight()</pre>
	 * <p>Gets the height of the UIObject.</p>
	 * @param None
	 * @return int
	 * **/
	public int getHeight() {
		return height;
	}

	/**
	 * <i><b>setHeight</b></i>
	 * <pre>	public void setHeight(int height)</pre>
	 * <p>Sets the height of the UIObject.</p>
	 * @param
	 * @return void
	 * **/
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * <i><b>isHovering</b></i>
	 * <pre>	public boolean isHovering()</pre>
	 * <p>Gets the state of the hovering variable of the UIObject.</p>
	 * @param None
	 * @return boolean
	 * **/
	public boolean isHovering() {
		return hovering;
	}

	/**
	 * <i><b>setHovering</b></i>
	 * <pre>	public void setHovering()</pre>
	 * <p>Sets the state of the hovering variable of the UIObject.</p>
	 * @param
	 * @return void
	 * **/
	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
	

}
