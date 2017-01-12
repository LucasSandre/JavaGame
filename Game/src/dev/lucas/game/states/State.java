package dev.lucas.game.states;

import java.awt.Graphics;

import dev.lucas.game.Handler;

public abstract class State {

	// Creates a private state variable and sets its value to null
	private static State current_state = null;
	
	
	public static void setState(State state){
		// sets the current state to the state that was passed into it.
		current_state = state;
	}
	
	public static State getState() {
		// gets the currnt state
		return current_state;
	}
	
	// Class
	protected Handler handler;
	
	// The Class Constructor takes in a handler and saves its value.
	public State(Handler handler){
		this.handler = handler;
	}
	
	// makes sure the classes than inherit this class they must have the following methods.
	public abstract void tick();
	
	public abstract void render(Graphics g);
}
