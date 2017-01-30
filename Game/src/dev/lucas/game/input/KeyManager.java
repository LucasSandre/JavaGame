package dev.lucas.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** 
 * <i><b>KeyManager</b></i>
 * <pre> public class KeyManager implements KeyListner</pre>
 * <p>This KeyManager class manages all important keyboard input.</p>
 * **/
public class KeyManager implements KeyListener {
	// Implements KeyListener Methods to detect key input
	
	// Initializes boolean variables and Arrays to store if a key is pressed , it was already pressed, and if you can't press it.
	private char current_input;
	private boolean[] keys , just_pressed, cant_press;
	public boolean up,down,right,left,delete;
	public boolean e_key;
	public boolean inputing = false;
	
	// The Class Constructor
		// creates the arrays
	/**
	 * <i><b> KeyManager</b></i>
	 * <pre>	public KeyManager()</pre>
	 * <p>The class constructor creates three the three necessary arrays to allow toggle keys and regular input.</p>
	 * @param None
	 * **/
	public KeyManager() {
		keys = new boolean[256];
		just_pressed = new boolean[keys.length];
		cant_press = new boolean[keys.length];
	}
	
	
	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This method keeps track of all the keys that are being pressed, just been pressed and ones that can't be pressed.</p>
	 * @param None
	 * @return void
	 * **/
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
		delete = keys[KeyEvent.VK_BACK_SPACE];
	}
	
	/**
	 * <i><b>keyPressed</b></i>
	 * <pre>	public void keyPressed(KeyEvent e)</pre>
	 * <p>This method is called when a key is pressed. This method checks to see if the key code value of the key that was just pressed is in between the bounds of the keys 
	 * array and uses the key code as a index of the array and sets that index to true.</p>
	 * @param
	 * @return can return null if the key code of the key that is pressed does not follow the rule 0 > key code < 256. 
	 * **/
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() > keys.length) {
			return;
		}
		keys[e.getKeyCode()] = true;
	}

	/**
	 * <i><b>keyReleased</b></i>
	 * <pre>	public void keyReleased(KeyEvent e)</pre>
	 * <p>This method is called when a key is released. This method checks if the key code value of the key pressed is between the bounds of the keys array. and uses the key 
	 * code as a index of the array and sets that index to true.</p>
	 * @param
	 * @return can return null if the key code of the key that is released does not follow the rule 0 > key code < 256. 
	 * **/
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() > keys.length) {
			return;
		}
		keys[e.getKeyCode()] = false;
		
	}

	/**
	 * <i><b>keyTyped</b></i>
	 * <pre>	public void keyTyped(KeyEvent e)</pre>
	 * <p>This method is called if the key pressed generated a unicode value. This method is unused.</p>
	 * @param
	 * @return None 
	 * **/
	@Override
	public void keyTyped(KeyEvent e) {
		if (!inputing) {
			return;
		}
		current_input = e.getKeyChar();
	}
	
	/**
	 * <i><b>keyJustPressed</b></i>
	 * <pre>	public boolean keyJustPressed(int keyCode)</pre>
	 * <p>This method checks if the key code parsed in to the method is between 0 and the length of the keys array. Then it returns the value at the index of the key code 
	 * in the just_pressed array</p>
	 * @param
	 * @return boolean
	 * @see {@link dev.lucas.game.input}
	 * **/
	public boolean keyJustPressed(int keyCode) {
		if(keyCode < 0 || keyCode >= keys.length){
			return false;
		}
		return just_pressed[keyCode];
		}


	/**
	 * <i><b>getCurrent_input</b></i>
	 * <pre>	public char getCurrent_input()</pre>
	 * <p>Gets the current inputed character.</p>
	 * @param None
	 * @return char
	 * @see {@link dev.lucas.game.input }
	 * **/
	public char getCurrentInput() {
		return current_input;
	}
	
	
}
