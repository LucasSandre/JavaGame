package dev.lucas.game.states;

import java.awt.Graphics;

import dev.lucas.game.Handler;

public abstract class State {

	private static State current_state = null;
	
	public static void setState(State state){
		current_state = state;
	}
	
	public static State getState() {
		return current_state;
	}
	
	// Class
	protected Handler handler;
	
	public State(Handler handler){
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
}
