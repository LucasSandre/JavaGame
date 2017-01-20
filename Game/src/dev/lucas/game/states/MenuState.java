package dev.lucas.game.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Assets;
import dev.lucas.game.ui.ClickListener;
import dev.lucas.game.ui.UIImageButton;
import dev.lucas.game.ui.UIManager;
import dev.lucas.game.ui.UIText;

/** 
 * <i><b>MenuState</b></i>
 * <pre> public class MenuState extends State</pre>
 * <p>This class defines all the necessary methods and variables for a MenuState</p>
 * @see {@link dev.lucas.game.states State State}
 * **/
public class MenuState extends State {
	
	// Initializes a private ui_manager object
	private UIManager ui_manager;
	
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
		ui_manager.tick(); // ticks the Uimanager
		
		// Temporarily skip start button
		//handler.getMouseManager().setUIManager(null);
		//State.setState(handler.getGame().game_state);
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
	public void render(Graphics g) { // renders the Uimanager.
		ui_manager.render(g);
	}
	
	private void init() {
		ui_manager.addObject(new UIImageButton(200,200,128,64,Assets.start,new ClickListener(){

			@Override
			public void onClick() {
				State.setState(handler.getGame().game_state);
			}
		}));
		ui_manager.addObject(new UIText(400,100,128,64,Assets.font_32[0], Color.cyan, "TEST", new ClickListener() {

			
			// this method gets called when the object is clicked on.
			@Override
			public void onClick() {
				System.out.println("TEST");
			}
			
		}));
	}
}
