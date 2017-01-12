package dev.lucas.game.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;


public abstract class UIObject{

	// Initializes necessary variables for a UI Object
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean hovering = false;
	
	// The Class Constructor, takes in an x,y,width, and height
		// Stores the passed values
		// Creates bounding box
	public UIObject(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle((int) x,(int) y, width, height);
	}

	// Method all UI objects need
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void onClick();
	
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
	public void onMouseRelease(MouseEvent e) {
		// Checks to see if the objects hovering value is true
			// If so, it runs onClick(); method
		if (hovering) {
			onClick();
		}
	}
	
	
	// Getters and Setters
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
	

}
