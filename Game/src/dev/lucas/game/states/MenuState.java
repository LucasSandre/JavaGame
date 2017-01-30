package dev.lucas.game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Assets;
import dev.lucas.game.ui.ClickListener;
import dev.lucas.game.ui.UIImageButton;
import dev.lucas.game.ui.UIManager;
import dev.lucas.game.ui.UIText;
import dev.lucas.game.ui.UITextBox;

/** 
 * <i><b>MenuState</b></i>
 * <pre> public class MenuState extends State</pre>
 * <p>This class defines all the necessary methods and variables for a MenuState</p>
 * @see {@link dev.lucas.game.states State State}
 * **/
public class MenuState extends State {
	
	// Initializes a private ui_manager object
	private UIManager ui_manager;
	private Rectangle outer, char_window;
	private int overlay_x, overlay_y;
	
	// The MenuState constructor and takes in a handler object. pases the handler to the super constructor, creates a Ui manager, sets it in the handler, adds some UI objects that can change the stae
	/**
	 * <i><b> MenuState</b></i>
	 * <pre>	public MenuState(Handler handler)</pre>
	 * <p>The class constructor passes the handler to the super constructor, creates a new UIManager with the handler, and adds some UIObjects</p>
	 * @param Handler handler
	 * @see {@link dev.lucas.game.Handler Handler} , {@link dev.lucas.game.ui.UIManager UIManager} , {@link dev.lucas.game.states.State State} , {@link dev.lucas.game.ui.ClickListener ClickListner} , {@link dev.lucas.game.ui.UIObject UIObject}
	 * **/
	public MenuState(Handler handler) {
		super(handler);
		ui_manager = new UIManager(handler);
		handler.getMouseManager().setUIManager(ui_manager);
		overlay_x = (handler.getWidth()-Assets.character_select_overlay.getWidth())/2;
		overlay_y = (handler.getHeight()-Assets.character_select_overlay.getHeight())/2;
		init();
	}

	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This method ticks the UIManager.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.ui.UIManager UIManager}
	 * **/
	@Override
	public void tick() {
		if (!(handler.getMouseManager().getUi_manager().equals(ui_manager))) {
			handler.getMouseManager().setUIManager(ui_manager);
		}
		ui_manager.tick(); // ticks the Uimanager
		
	}
	

	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>This method renders the UIManager.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.ui.UIManager UIManager}
	 * **/
	@Override
	public void render(Graphics g) { // renders the Ui manager.
		g.setColor(Assets.blue);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		g.setColor(Assets.shadow_blue);
		g.fillRect((handler.getWidth()-Assets.character_select_overlay.getWidth()-20)/2,  (handler.getHeight()-Assets.character_select_overlay.getHeight()-20)/2, Assets.character_select_overlay.getWidth()+20, Assets.character_select_overlay.getHeight()+20);
		g.drawImage(Assets.character_select_overlay, overlay_x, overlay_y, null);
		ui_manager.render(g);
	}
	
	private void init() {
		ui_manager.addObject(new UITextBox(handler, (overlay_x + (1024*(5f/16f))), (overlay_y+(576*(10f/64f))), 400, 48, Assets.font_32[0], Assets.font_32_fm[0], "Enter Character Name"));
	}
}
