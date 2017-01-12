package dev.lucas.game.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import dev.lucas.game.Handler;

public class UIManager {

	// Initializes variables for a handler and an array that will hold all the Ui objects
	private Handler handler;
	private ArrayList<UIObject> objects;
	
	// The Class Constructor, takes in a handler
		// saves the handler
		// creates the array list for the objects.
	public UIManager(Handler handler) {
		this.handler = handler;
		objects = new ArrayList<UIObject>();
	}

	public void tick() {
		// Ticks all UI objects in objects array
		for (UIObject o : objects) {
			o.tick();
		}
	}

	public void render(Graphics g) {
		// Renders all UI Objects
		for (UIObject o : objects) {
			o.render(g);
		}
	}
	
	public void onMouseMove(MouseEvent e) {
		// When the mouse moves it runs the onMouseMove method for all UI objects
		for (UIObject o : objects) {
			o.onMouseMove(e);
		}
	}
	
	public void onMouseRelease(MouseEvent e) {
		// When the mouse moves it runs the OnMouseRelease method for all UI Objects
		for (UIObject o : objects) {
			o.onMouseRelease(e);
		}
	}
	
	// Adder and Remover
	public void addObject(UIObject o) {
		objects.add(o);
	}

	public void removeObject(UIObject o) {
		objects.remove(o);
	}

	//Getters and Setters
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}
	
}
