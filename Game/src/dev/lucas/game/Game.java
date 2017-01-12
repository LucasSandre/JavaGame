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
import dev.lucas.game.states.State;

/** 
 * <h1>Game</h1>
 * <p>This class runs the entirety of the Game.</p>
 * <h2>Controls:</h2>
 * <pre>
 *  <h3>Keyboard:</h3>
 *  	W = up
 *  	A = left
 *  	S = down
 *  	D = right
 *  	E = Inventory Toggle
 *  <h3>Mouse:</h3>
 *  	Left Click = Attack | Select
 *  	Right Click = Interact
 *  </pre>
 *  @author Lucas Sandre, Ethan Coad, Marcus Blauner
 *  @version Beta 1.0
 *  @see {@link dev.lucas.game.Launcher}
 * **/
public class Game implements Runnable{
	
	// Initializes game variables
	private Display display;
	private int width,height;
	public String title;
	
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	// Initializing States
	public State game_state;
	public State menu_state;
	public State setting_state;
	
	// Initializing Input
	private KeyManager key_manager;
	private MouseManager mouse_manager;
	
	//  Initializing game Camera
	private GameCamera game_camera;
	
	// Initializing Handler
	private Handler handler;
	
	// Game constructor sets the width and height and title variables to what it is given and activates the key and mouse managers
	/**
	 * <i><b>Game</b></i>
	 * <pre>	public Game(String title, int width, int height)</pre>
	 * <p>The game constructor saves the values passed into it and creates the key and mouse managers.</p>
	 * @param String title 	- Sets the title of the window.
	 * @param Int width		- Sets the width of the window.
	 * @param Int height	- Sets the height of the window.
	 * @see {@link dev.lucas.game.input.KeyManager} , {@link dev.lucas.game.input.MouseManager}
	 * **/
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		key_manager = new KeyManager();
		mouse_manager = new MouseManager();
	}
	/**
	 * <i><b>Init</b></i>
	 * <pre>	private void init()</pre>
	 * <p>The init method is called after the start method. This method creates a new Display object, adds the KeyListener, adds the MouseListeners,
	 * creates a graphics object from the display object, runs the init method from the Assets class, creates the handler and passes graphics variable g,
	 * creates the Game Camera and passes the handler and two 0's, creates the states, and finally runs the setState method from the State Class.</p>
	 * @param None
	 * @see {@link dev.lucas.game.input.KeyManager} , {@link dev.lucas.game.input.MouseManager}, {@link dev.lucas.game.states.State} , {@link dev.lucas.game.states.GameState} , {@link dev.lucas.game.states.MenuState} , 
	 * @see {@link dev.lucas.game.Display} , {@link dev.lucas.game.gfx.Assets} , {@link dev.lucas.game.gfx.GameCamera} , {@link dev.lucas.game.Handler}
	 * **/
	private void init() {
		// called after start method
		
		// Creates a display object and adds keyboard and Mouse listeners.
		display = new Display(title,width,height);
		display.getFrame().addKeyListener(key_manager);
		display.getFrame().addMouseListener(mouse_manager);
		display.getFrame().addMouseMotionListener(mouse_manager);
		display.getCanvas().addMouseListener(mouse_manager);
		display.getCanvas().addMouseMotionListener(mouse_manager);
		
		// Creates a graphics object from the Display object.
		g = display.getCanvas().getGraphics();
		
		// Runs the Assets class init method and passes the graphics object along with it.
		Assets.init(g);
		
		// creates a new handler object and passes this instance of the class to it.
		handler = new Handler(this);
		
		// creates the game camera and parses the handler and two 0's
		game_camera = new GameCamera(handler, 0,0);
		
		// creates the states for the game.
	    game_state = new GameState(handler);
	    menu_state = new MenuState(handler);

	    
	    // sets the current state to the menu state
		State.setState(menu_state);
	}
	/** 
	 * <i><b>Tick</b></i>
	 * <pre>	private void tick()</pre>
	 * <p>The tick method calls the tick method of the key_manager. It also checks if the current_state variable from the State class has a value.
	 *  If it does it ticks the current state </p>
	 *  @param None
	 *  @see {@link dev.lucas.game.input.KeyManager}, {@link dev.lucas.game.states.State}
	 * **/
	private void tick(){
		// ticks the key manager and if the state is not null, it ticks the current state.
		key_manager.tick();
		
		if (State.getState() != null) 
			State.getState().tick();
	}
	
	/**
	 * <i><b>Render</b></i>
	 * <pre>	private void render()</pre>
	 * <p>The render method gets the buffer strategy stored in the Display class and checks if it's value is null. If it is it creates a buffer
	 *  strategy consisting of 3 buffers then it returns null. If there is a buffer strategy, it gets the drawing graphics from the strategy. After, 
	 *  it clears the screen. Then, it checks if the current state is not null, if so it renders the state stored in 'current_state' which itself is 
	 *  stored in the State class. Finally it makes the next buffer visible then it disposes of the Graphics content. </p>
	 *  @param None
	 *  @return Null if there is no buffer strategy.
	 *  @see {@link dev.lucas.game.display.Display}
	 *  **/
	private void render() {
		// gets the bufferstrategy and may create one if one does not already exist.
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		// gets the draw graphics.
		g = bs.getDrawGraphics();
		// Clear Screen
		
		g.clearRect(0, 0, width, height);
		
		// Draws the state
		
		if (State.getState() != null) 
			State.getState().render(g);
		
		// End drawing
		bs.show();
		g.dispose();
	}
	
	/** 
	 * <i><b>Run</b></i>
	 * <pre>	public void run()</pre>
	 * <p>The run method is called when the thread starts. The run method immediately calls the init method. The run method tries to calls the tick 
	 * and render methods 60 times a second. the run method also currently displays the amount of ticks that occurred in the console.</p>
	 * @param None
	 *  **/
	public void run() {
		// runs init method
		init();
		
		// makes sure the game only runs at a maximum of 60 frames/s
		int fps = 60;
		double time_per_tick = 1000000000 / fps;
		double delta = 0;
		long now;
		long last_time = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) { // while running variable is true.
			// updates time variables
			now = System.nanoTime();
			delta += (now-last_time) / time_per_tick;
			timer += now - last_time;
			last_time = now;

			// checks if delta is greater than or equal to 1, If it is it runs the tick and render methods, adds 1 to variable ticks and minus 1 from variable delta.
			if (delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			// prints out how many ticks happened in the last second and sets timer and ticks to 0
			if (timer >= 1000000000){
				System.out.println("Ticks and Frames: " + ticks);
		     	ticks = 0;
				timer = 0;
			}
			
		}
	}
	
	/** 
	 * <i><b>Start</b></i>
	 * <pre>	public synchronized void start()</pre>
	 * <p>The start method checks if the game is already running, if so it returns null. Next it creates a thread for the Game and starts the thread.</p>
	 * @param None
	 * @return Null if the game has already started.
	 * **/
	public synchronized void start() {
		// prevents the game being started twice.
		if (running) {
			return;
		}
		// creates & starts the game tread
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/** 
	 * <i><b>Stop</b></i>
	 * <pre>	public synchronized void stop()</pre>
	 * <pThe stop method checks if the game is already not running. Then it attempts to try and kill the thread. If it fails at killing the thread, 
	 * it catches the InterruptedExeption and prints its stack trace.></p>
	 * @param None
	 * @return Null - If the game is not running and stop() is called.
	 * @exception InterruptedExeption if the thread does not end.
	 * **/
	public synchronized void stop() {
		// prevents the game being stopped more than once
		if (!running) {
			return;
		}
		
		// Attempts to end the game tread, If it fails it catches the IntertuptedExeption and prints its stack trace.
		try {
			thread.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	// getters and setters.
	
	/** 
	 * <i><b>getKeyManager</b></i>
	 * <pre>	public KeyManager getKeyManager()</pre>
	 * <p>Returns the KeyManager.</p>
	 * @param none
	 * @return KeyManager
	 * @see {@link dev.lucas.game.input.KeyManager}
	 * **/
	public KeyManager getKeyManager() {
		return key_manager;
	}
	
	/** 
	 * <i><b>getMouseManager</b></i>
	 * <pre>	public MouseManager getMouseManager()</pre>
	 * <p>Returns the MouseManager.</p>
	 * @param none
	 * @return MouseManager
	 * @see {@link dev.lucas.game.input.MouseManager}
	 * **/
	public MouseManager getMouseManager() {
		return mouse_manager;
	}

	/** 
	 * <i><b>getGameCamera</b></i>
	 * <pre>	public GameCamera getGameCamera()</pre>
	 * <p>Returns the GameCamera.</p>
	 * @param none
	 * @return GameCamera
	 * @see {@link dev.lucas.game.gfx.GameCamera}
	 * **/
	public GameCamera getGameCamera() {
		return game_camera;
	}
	
	/** 
	 * <i><b>getWidth</b></i>
	 * <pre>	public int getWidth()</pre>
	 * <p>Returns the Width.</p>
	 * @param none
	 * @return int
	 * **/
	public int getWidth() {
		return width;
	}

	/** 
	 * <i><b>getHeight</b></i>
	 * <pre>	public int getHeight()</pre>
	 * <p>Returns the Height.</p>
	 * @param none
	 * @return int
	 * **/
	public int getHeight() {
		return height;
	}

	
	
}
