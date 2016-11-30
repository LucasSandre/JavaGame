package dev.lucas.game.states;

import java.awt.Graphics;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Assets;
import dev.lucas.game.ui.ClickListener;
import dev.lucas.game.ui.UIImageButton;
import dev.lucas.game.ui.UIManager;
import dev.lucas.game.ui.UIText;

public class MenuState extends State {
	
	private UIManager ui_manager;
	
	public MenuState(Handler handler) {
		super(handler);
		ui_manager = new UIManager(handler);
		handler.getMouseManager().setUIManager(ui_manager);
		ui_manager.addObject(new UIImageButton(200,200,128,64,Assets.start,new ClickListener(){

			@Override
			public void onClick() {
				State.setState(handler.getGame().game_state);
			}
		}));
		ui_manager.addObject(new UIText(400,100,128,64,Assets.font_32[0],"TEST",new ClickListener() {

			@Override
			public void onClick() {
				System.out.println("TEST");
			}
			
		}));
	}

	@Override
	public void tick() {
		ui_manager.tick();
		
		// Temporarily skip start button
		//handler.getMouseManager().setUIManager(null);
		//State.setState(handler.getGame().game_state);
	}
	

	@Override
	public void render(Graphics g) {
		ui_manager.render(g);
	}
}
