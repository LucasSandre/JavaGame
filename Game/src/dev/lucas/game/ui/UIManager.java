package dev.lucas.game.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import dev.lucas.game.Handler;

/** 
 * <i><b>UIManager</b></i>
 * <pre> public class UIManager</pre>
 * <p>This class defines all the necessary methods and variables for an UIManager</p>
 * **/
public class UIManager {

	// Initializes variables for a handler and an array that will hold all the Ui objects
	private Handler handler;
	private ArrayList<UIObject> objects;
	
	// The Class Constructor, takes in a handler
		// saves the handler
		// creates the array list for the objects.
	/**
	 * <i><b> UIManager</b></i>
	 * <pre>	public UIManager(Handler handler)</pre>
	 * <p>The class constructor saves the parsed Handler and creates a new UIObject ArrayList.</p>
	 * @param Handler handler
	 * @see {@link dev.lucas.game.ui.UIObject UIObject} , {@link dev.lucas.game.Handler Handler}
	 * **/
	public UIManager(Handler handler) {
		this.handler = handler;
		objects = new ArrayList<UIObject>();
	}

	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This method ticks all UIObjects in the UIObjects ArrayList.</p>
	 * @param None
	 * @return void
	 * @see {@link dev.lucas.game.ui.UIObject UIObject}
	 * **/
	public void tick() {
		// Ticks all UI objects in objects array
		for (UIObject o : objects) {
			o.tick();
		}
	}

	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>This method renders all UIObjects in the UIObjects ArrayList.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.ui.UIObject UIObject}
	 * **/
	public void render(Graphics g) {
		// Renders all UI Objects
		for (UIObject o : objects) {
			o.render(g);
		}
	}
	
	/**
	 * <i><b>onMouseMove</b></i>
	 * <pre>	public void onMouseMove(MouseEvent e)</pre>
	 * <p>When the mouse moves, it runs the onMouseMove method for all UIObjects.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.ui.UIObject UIObject}
	 * **/
	public void onMouseMove(MouseEvent e) {
		// When the mouse moves it runs the onMouseMove method for all UI objects
		for (UIObject o : objects) {
			o.onMouseMove(e);
		}
	}
	
	/**
	 * <i><b>onMouseRelease</b></i>
	 * <pre>	public void onMouseRelease(MouseEvent e)</pre>
	 * <p>When the mouse is released, it runs the onMouseRelease method for all UIObjects.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.ui.UIObject UIObject}
	 * **/
	public void onMouseRelease(MouseEvent e) {
		// When the mouse moves it runs the OnMouseRelease method for all UI Objects
		for (UIObject o : objects) {
			o.onMouseRelease(e);
		}
	}
	
	// Adder and Remover
	/**
	 * <i><b>addObject</b></i>
	 * <pre>	public void addObject(UIObject o)</pre>
	 * <p>Adds the parsed UIObject to the objects ArrayList.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.ui.UIObject UIObject}
	 * **/
	public void addObject(UIObject o) {
		objects.add(o);
	}

	/**
	 * <i><b>removeObject</b></i>
	 * <pre>	public void removeObject(UIObject o)</pre>
	 * <p>Removes the parsed UIObject from the objects ArrayList.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.ui.UIObject UIObject}
	 * **/
	public void removeObject(UIObject o) {
		objects.remove(o);
	}

	//Getters and Setters
	/**
	 * <i><b>getHandler</b></i>
	 * <pre>	public Handler getHandler()</pre>
	 * <p>This method returns the handler.</p>
	 * @param None
	 * @return Handler
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public Handler getHandler() {
		return handler;
	}

	/**
	 * <i><b>setHandler</b></i>
	 * <pre>	public void setHandler(Handler handler)</pre>
	 * <p>This method sets the handler.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	/**
	 * <i><b>getObjects</b></i>
	 * <pre>	public ArrayList<UIObject> getObjects()</pre>
	 * <p>This method gets the UIObject ArrayList.</p>
	 * @param None
	 * @return ArrayList<UIObject>
	 * @see {@link dev.lucas.game.ui.UIObject UIObject}
	 * **/
	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	/**
	 * <i><b>setObjects</b></i>
	 * <pre>	public void setObjects(ArrayList<UIObject> objects)</pre>
	 * <p>This method sets the UIObject ArrayList.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.ui.UIObject UIObject}
	 * **/
	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}
	
}
