package dev.lucas.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys , just_pressed, cant_press;
	public boolean up,down,right,left;
	public boolean a_up,a_down,a_right,a_left;
	public boolean e_key;
	
	public KeyManager() {
		keys = new boolean[256];
		just_pressed = new boolean[keys.length];
		cant_press = new boolean[keys.length];
	}
	public void tick(){
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
	
		
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
		a_up = keys[KeyEvent.VK_UP];
		a_down = keys[KeyEvent.VK_DOWN];
		a_left = keys[KeyEvent.VK_LEFT];
		a_right = keys[KeyEvent.VK_RIGHT];
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
