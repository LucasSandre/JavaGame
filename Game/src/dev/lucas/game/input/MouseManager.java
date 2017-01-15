package dev.lucas.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import dev.lucas.game.ui.UIManager;

/** 
 * <i><b>MouseManager</b></i>
 * <pre> public class MouseManager implements MouseListner, MouseMotionListner</pre>
 * <p>This class defines all the necessary methods and variables for a MouseManager.</p>
 * **/
public class MouseManager implements MouseListener,  MouseMotionListener{

	private boolean left_pressed, middle_pressed, right_pressed;
	private int mouse_x, mouse_y;
	private UIManager ui_manager;
	
	/**
	 * <i><b> MouseManager</b></i>
	 * <pre>	public MouseManager()</pre>
	 * <p>This constructor does nothing.</p>
	 * @param None
	 * **/
	public MouseManager() {
		
	}
	
	// Implemented methods
	
	/**
	 * <i><b>mouseDragged</b></i>
	 * <pre>	public void mouseDragged(MouseEvent e)</pre>
	 * <p>This method is called when a mouse button is down and the mouse is being moved. This method is not used.</p>
	 * @param
	 * @return None
	 * **/
	@Override
	public void mouseDragged(MouseEvent e) {
		
		
	}

	/**
	 * <i><b>mouseMoved</b></i>
	 * <pre>	public void mouseMoved(MouseEvent e)</pre>
	 * <p>This method is called when the mouse is moved. This method gets the x and y position of the mouse. It also checks to see if a UI manager exists. If so it calls the UI 
	 * onMouseMove.</p>
	 * @param
	 * @return None 
	 * @see {@link dev.lucas.game.ui.UIManager UIManager}
	 * **/
	@Override
	public void mouseMoved(MouseEvent e) {
		mouse_x	= e.getX();
		mouse_y = e.getY();
		if (ui_manager != null) {
			ui_manager.onMouseMove(e);
		}
	}

	/**
	 * <i><b>mouseClicked</b></i>
	 * <pre>	public void mouseClicked(MouseEvent e)</pre>
	 * <p>This method is called when the mouse is pressed and released. This method is not used.</p>
	 * @param
	 * @return None
	 * **/
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	/**
	 * <i><b>mouseEntered</b></i>
	 * <pre>	public void mouseEntered(MouseEvent)</pre>
	 * <p>This method is called when the mouse enters the Dimension of an unobstructed Java Component. This method is not used.</p>
	 * @param
	 * @return None 
	 * **/
	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	/**
	 * <i><b>mouseExited</b></i>
	 * <pre>	public void mouseExited(MouseEvent e)</pre>
	 * <p>This method is called when the mouse exits the Dimension of an unobstructed Java Component. This method is not used.</p>
	 * @param
	 * @return None
	 * **/
	@Override
	public void	mouseExited(MouseEvent e) {
		
		
	}

	/**
	 * <i><b>mousePressed</b></i>
	 * <pre>	public void mousePressed(MouseEvent e)</pre>
	 * <p>This method is called when a mouse is being pressed. This method gets the button that was pressed and sets a respective variable to true.</p>
	 * @param
	 * @return None
	 * **/
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

	/**
	 * <i><b>mouseReleased</b></i>
	 * <pre>	public void mouseReleased(MouseEvent e)</pre>
	 * <p>This method is called when a mouse has been pressed, and it is released. This method gets the button that was released and sets a respective variable to false. After 
	 * it calls a method from the UIManager named OnMouseRelease(e) if the UIManagaer exists.</p>
	 * @param
	 * @return None 
	 * @see {@link dev.lucas.game.ui.UIManager UIManager}
	 * **/
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

	// Getters
	
	/**
	 * <i><b>isLeftPressed</b></i>
	 * <pre>	public boolean isLeftPressed()</pre>
	 * <p>Gets the state of the left mouse button.</p>
	 * @param None
	 * @return boolean
	 * **/
	public boolean isLeftPressed() {
		return left_pressed;
	}

	/**
	 * <i><b>isMiddlePressed</b></i>
	 * <pre>	public boolean isMiddlePressed()</pre>
	 * <p>Gets the state of the middle mouse button.</p>
	 * @param None
	 * @return boolean
	 * **/
	public boolean isMiddlePressed() {
		return middle_pressed;
	}

	/**
	 * <i><b>isRightPressed</b></i>
	 * <pre>	public boolean isRightPressed()</pre>
	 * <p>Gets the state of the right mouse button.</p>
	 * @param None
	 * @return boolean
	 * **/
	public boolean isRightPressed() {
		return right_pressed;
	}

	/**
	 * <i><b>getMouseX</b></i>
	 * <pre>	public int getMouseX()</pre>
	 * <p>Gets the mouses' x position.</p>
	 * @param None
	 * @return int
	 * **/
	public int getMouseX() {
		return mouse_x;
	}

	/**
	 * <i><b>getMouseY</b></i>
	 * <pre>	public int getMouseY()</pre>
	 * <p>Gets the mouses' y position.</p>
	 * @param
	 * @return int
	 * **/
	public int getMouseY() {
		return mouse_y;
	}	
	
	// Setters
	/**
	 * <i><b>setUIManager</b></i>
	 * <pre>	public void setUIManager(UIManager ui_manager)</pre>
	 * <p>Sets the MouseManager's UIManager.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.ui.UIManager UIManager}
	 * **/
	public void setUIManager(UIManager ui_manager){
		this.ui_manager = ui_manager;
	}
}
