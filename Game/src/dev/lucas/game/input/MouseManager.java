package dev.lucas.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dev.lucas.game.ui.UIManager;

public class MouseManager implements MouseListener,  MouseMotionListener{

	private boolean left_pressed, middle_pressed, right_pressed;
	private int mouse_x, mouse_y;
	private UIManager ui_manager;
	
	public MouseManager() {
		
	}

	public void setUIManager(UIManager ui_manager){
		this.ui_manager = ui_manager;
	}
	
	// Getters
	
	public boolean isLeftPressed() {
		return left_pressed;
	}

	public boolean isMiddlePressed() {
		return middle_pressed;
	}

	public boolean isRightPressed() {
		return right_pressed;
	}

	public int getMouseX() {
		return mouse_x;
	}

	public int getMouseY() {
		return mouse_y;
	}
	
	
	// Implemented methods
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse_x	= e.getX();
		mouse_y = e.getY();
		if (ui_manager != null) {
			ui_manager.onMouseMove(e);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1){
			left_pressed = true;
		}
		if (e.getButton() == MouseEvent.BUTTON2){
			middle_pressed = true;
		}
		if (e.getButton() == MouseEvent.BUTTON3){
			right_pressed = true;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1){
			left_pressed = false;
		}
		if (e.getButton() == MouseEvent.BUTTON2){
			middle_pressed = false;
		}
		if (e.getButton() == MouseEvent.BUTTON3){
			right_pressed = false;
		}
		if (ui_manager != null) {
			ui_manager.onMouseRelease(e);
		}
	}

}
