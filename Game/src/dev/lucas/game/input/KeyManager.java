package dev.lucas.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	// Implements KeyListener Methods to detect key input
	
	// Initializes boolean variables and Arrays to store if a key is pressed , it was already pressed, and if you can't press it.
	private boolean[] keys , just_pressed, cant_press;
	public boolean up,down,right,left;
	public boolean e_key;
	
	// The Class Constructor
		// creates the arrays
	public KeyManager() {
		keys = new boolean[256];
		just_pressed = new boolean[keys.length];
		cant_press = new boolean[keys.length];
	}
	
	
	public void tick(){
		// Updates variables
		
		// This is for toggle type keys, it checks thought the arrays
			// If a key is not being pressed and has already been pressed, allows to be able to press again
			// Or If a key has been just been pressed, it does not allow it to be pressed anymore and it is no longer just been pressed
			// if we can press that key and it it being pressed it sets the just pressed value at the key code to true.
		for (int i = 0; i < keys.length; i++) {
			if (cant_press[i] && !keys[i]){
				cant_press[i] = false;
			}
			else if (just_pressed[i]) {
				cant_press[i] = true;
				just_pressed[i] = false;
			}
			if (!cant_press[i] && keys[i]) {
				just_pressed[i] = true;
			}
		}
	
		// if the 'w','a','s','d' key are being pressed and sets if that it is true or false.
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() > keys.length) {
			return;
		}
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() > keys.length) {
			return;
		}
		keys[e.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length){
			return false;
		}
		return just_pressed[keyCode];
		}
}
