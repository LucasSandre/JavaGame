/**
 * 
 */
package dev.lucas.game.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Animation;
import dev.lucas.game.gfx.Assets;
import dev.lucas.game.ui.ClickListener;
import dev.lucas.game.ui.UIImageButton;
import dev.lucas.game.ui.UIManager;
import dev.lucas.game.ui.UIText;

/** 
 * <i><b>StartState</b></i>
 * <pre> public class StartState extends State</pre>
 * <p>This class defines all the necessary methods and variables for a StartState.</p>
 * @see {@link dev.lucas.game.states.State State}
 * **/
public class StartState extends State {
	
	private Animation anim_red_fire;
	private Animation anim_orange_fire;
	private Animation anim_green_fire;
	private Animation anim_blue_fire;
	
	private UIManager ui_manager;
	
	private int speed = 50;
	private int name_width;
	private int name_height;
	
	/**
	 * <i><b> StartState</b></i>
	 * <pre>	public StartState(Handler handler)</pre>
	 * <p>The class constructor passes the handler to the super constructor and creates the animations and UIObjects.</p>
	 * @param
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public StartState(Handler handler) {
		super(handler);
		
		name_width = Assets.font_72_fm[0].stringWidth("Syvithia");
		name_height = Assets.font_72_fm[0].getAscent();
		
		anim_green_fire = new Animation(speed,Assets.green_fire);
		anim_red_fire = new Animation(speed,Assets.red_fire);
		anim_orange_fire = new Animation(speed,Assets.orange_fire);
		anim_blue_fire = new Animation(speed,Assets.mana_fire);
		
		ui_manager = new UIManager(handler);
		handler.getMouseManager().setUIManager(ui_manager);
		ui_manager.addObject(new UIImageButton((handler.getWidth() - 128)/2, ((handler.getHeight()- 64)/2)+handler.getHeight()/16, 128, 64, Assets.start, new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().menu_state);
			}}));
		
		ui_manager.addObject(new UIText((handler.getWidth()- name_width)/2f, (handler.getHeight()-name_height)/2f, 0, 0, Assets.font_72[0], Color.RED, "Syvithia", new ClickListener() {
			@Override
			public void onClick() {}}));
		}

	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This method ticks the animations and the ui manager.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.gfx.Animation Animation} , {@link dev.lucas.game.ui.UIManager UIManager}
	 * **/
	@Override
	public void tick() {
		ui_manager.tick();
		anim_green_fire.tick();
		anim_blue_fire.tick();
		anim_red_fire.tick();
		anim_orange_fire.tick();
		
	}

	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render()</pre>
	 * <p>This method renders the animations and ui manager.</p>
	 * @param
	 * @return None
	 * @see {@link dev.lucas.game.gfx.Animation Animation} , {@link dev.lucas.game.ui.UIManager UIManager}
	 * **/
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		ui_manager.render(g);
		g.drawImage(anim_green_fire.getCurrentFrame(), (handler.getWidth() - 128)/2, (handler.getHeight() - 128)/4, 128, 128, null);
		g.drawImage(anim_blue_fire.getCurrentFrame(), (handler.getWidth() - 128)/4, (handler.getHeight() - 128)/2, 128, 128, null);
		g.drawImage(anim_red_fire.getCurrentFrame(), ((handler.getWidth() - 128)/4)*3, (handler.getHeight() - 128)/2, 128, 128, null);
		g.drawImage(anim_orange_fire.getCurrentFrame(), (handler.getWidth() - 128)/2, ((handler.getHeight() - 128)/4)*3, 128, 128, null);
	}

}
