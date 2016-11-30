package dev.lucas.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.lucas.game.display.Display;
import dev.lucas.game.gfx.Assets;
import dev.lucas.game.gfx.GameCamera;
import dev.lucas.game.input.KeyManager;
import dev.lucas.game.input.MouseManager;
import dev.lucas.game.states.GameState;
import dev.lucas.game.states.MenuState;
import dev.lucas.game.states.SettingState;
import dev.lucas.game.states.State;


public class Game implements Runnable{
	
	private Display display;
	private int width,height;
	public String title;
	
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	// States
	public State game_state;
	public State menu_state;
	public State setting_state;
	
	// Input
	private KeyManager key_manager;
	private MouseManager mouse_manager;
	
	// Camera
	private GameCamera game_camera;
	
	// Handler
	
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		key_manager = new KeyManager();
		mouse_manager = new MouseManager();
	}
	
	private void init() {
		display = new Display(title,width,height);
		display.getFrame().addKeyListener(key_manager);
		display.getFrame().addMouseListener(mouse_manager);
		display.getFrame().addMouseMotionListener(mouse_manager);
		display.getCanvas().addMouseListener(mouse_manager);
		display.getCanvas().addMouseMotionListener(mouse_manager);
		
		g = display.getCanvas().getGraphics();
		Assets.init(g);
		
		handler = new Handler(this);
		game_camera = new GameCamera(handler, 0,0);
		
		
	    game_state = new GameState(handler);
	    menu_state = new MenuState(handler);
	    setting_state = new SettingState(handler);
	    
		State.setState(menu_state);
	}
	
	private void tick(){
		key_manager.tick();
		
		if (State.getState() != null) 
			State.getState().tick();
	}
	
	private void render() {
		
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		// Clear Screen
		
		g.clearRect(0, 0, width, height);
		
		// Draw here
		
		if (State.getState() != null) 
			State.getState().render(g);
		
		// End drawing
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();
		
		int fps = 60;
		double time_per_tick = 1000000000 / fps;
		double delta = 0;
		long now;
		long last_time = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now-last_time) / time_per_tick;
			timer += now - last_time;
			
			last_time = now;
			if (delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			/*if (timer >= 1000000000){
				System.out.println("Ticks and Frames: " + ticks);
		     	ticks = 0;
				timer = 0;
			}*/
			
		}
	}
	
	public KeyManager getKeyManager() {
		return key_manager;
	}
	
	public MouseManager getMouseManager() {
		return mouse_manager;
	}

	public GameCamera getGameCamera() {
		return game_camera;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public synchronized void start() {
		if (running) {
			return;
		}
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running) {
			return;
		}
		
		try {
			thread.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
