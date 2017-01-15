package dev.lucas.game.states;

import java.awt.Graphics;

import dev.lucas.game.Handler;

/** 
 * <i><b>State</b></i>
 * <pre> public abstract class State</pre>
 * <p>This class defines all the necessary methods and variables for a State</p>
 * **/
public abstract class State {

	// Creates a private state variable and sets its value to null
	private static State current_state = null;
	
	
	/**
	 * <i><b>setState</b></i>
	 * <pre>	public static void setState(State state)</pre>
	 * <p>This method sets the current state.</p>
	 * @param
	 * @return void
	 * **/
	public static void setState(State state){
		// sets the current state to the state that was passed into it.
		current_state = state;
	}
	
	/**
	 * <i><b>getState</b></i>
	 * <pre>	public State getState()</pre>
	 * <p>Gets the current state.</p>
	 * @param None
	 * @return State
	 * **/
	public static State getState() {
		// gets the currnt state
		return current_state;
	}
	
	// Class
	protected Handler handler;
	
	// The Class Constructor takes in a handler and saves its value.
	/**
	 * <i><b>State</b></i>
	 * <pre>	public State(Handler handler)</pre>
	 * <p>The class constructor takes saves the handler passed into it.</p>
	 * @param
	 * @see {@link dev.lucas.game.states}
	 * **/
	public State(Handler handler){
		this.handler = handler;
	}
	
	// makes sure the classes than inherit this class they must have the following methods.
	/**
	 * <i><b>tick</b></i>
	 * <pre>	public abstract void tick()</pre>
	 * <p>This method is required for any State.</p>
	 * @param None
	 * @return void
	 * 	 * **/
	public abstract void tick();
	
	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>This method is required for any State.</p>
	 * @param
	 * @return void
	 * **/
	public abstract void render(Graphics g);
}
