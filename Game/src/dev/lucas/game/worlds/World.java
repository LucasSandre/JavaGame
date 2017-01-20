package dev.lucas.game.worlds;

import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import dev.lucas.game.Handler;
import dev.lucas.game.entities.EntityManager;
import dev.lucas.game.entities.creatures.Player;
import dev.lucas.game.entities.statics.Boulder;
import dev.lucas.game.entities.statics.Tree;
import dev.lucas.game.items.ItemManager;
import dev.lucas.game.tiles.Tile;
import dev.lucas.game.ui.UIManager;
import dev.lucas.game.ui.UIStatusEnergy;
import dev.lucas.game.ui.UIStatusHeart;
import dev.lucas.game.utils.Utils;

/** 
 * <i><b>World</b></i>
 * <pre> public class World</pre>
 * <p>This class defines all the necessary methods and variables for a World</p>
 * **/
public class World {
	
	// Initializes some private int's and a private Handler
	private int width, height;
	private int [][] tiles;
	private Handler handler;
	private int spawn_x,spawn_y;

	// For Entities
	
	public EntityManager entity_manager;
	
	// For Items
	private ItemManager item_manager;
	
	// For UI
	
	private UIManager ui_manager;
	
	// Constructor for the World, needs a handler and a path.
	/**
	 * <i><b> World</b></i>
	 * <pre>	public World(Handler handler, 
	 *                      String path)</pre>
	 * <p>The class constructor saves the parsed handler, creates a new entity and Item manager as well as a new Player. It then loads the world and sets the Players x and y 
	 * coordinates.</p>
	 * @param Handler handler,
	 * @param String path
	 * @see {@link dev.lucas.game.Handler Handler} , {@link dev.lucas.game.entities.EntityManager EntityManager} , {@link dev.lucas.game.entities.creatures.Player Player} , {@link dev.lucas.game.items.ItemManager ItemManager}
	 * **/
	public World (Handler handler, String path) {
		this.handler = handler;
		
		// Creates the managers.
		ui_manager = new UIManager(handler);
		entity_manager = new EntityManager(handler, new Player(handler , 100, 100));
		item_manager = new ItemManager(handler);
		
		// Temporary entity code
		entity_manager.addEntity(new Tree(handler, 1500 * Tile.TILEHEIGHT, 1500 * Tile.TILEHEIGHT));
		entity_manager.addEntity(new Boulder(handler, 1550*Tile.TILEHEIGHT, 1500*Tile.TILEHEIGHT));
		entity_manager.addEntity(new Tree(handler, 1600*Tile.TILEHEIGHT, 1500*Tile.TILEHEIGHT));
		entity_manager.addEntity(new Boulder(handler, 1650*Tile.TILEHEIGHT, 1500*Tile.TILEHEIGHT));
		
		//generateWorld("res/World/world.txt");
		
		// loads the world and sets the players position.
		loadWorld(path);
		entity_manager.getPlayer().setX(spawn_x);
		entity_manager.getPlayer().setY(spawn_y);
		
		// creates player specific UI Objects
		ui_manager.addObject(new UIStatusHeart(handler, 0, 0, 64, 64));
		ui_manager.addObject(new UIStatusEnergy(handler, 0, 64, 64, 64));
	}
	
	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This method ticks the ItemManager and EntityManager.</p>
	 * @param None
	 * @return void
	 * @see {@link dev.lucas.game.entities.EntityManager EntityManager} , {@link dev.lucas.game.items.ItemManager ItemManager}
	 * **/
	public void tick() { // ticks the item and entity manager
		item_manager.tick();
		entity_manager.tick();
		ui_manager.tick();
	}
	
	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>This method renders the viewable part of the world as well as the ItemManager and the EntityManager.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.entities.EntityManager EntityManager} , {@link dev.lucas.game.items.ItemManager ItemManager} , {@link dev.lucas.game.gfx.GameCamera GameCamera} , {@link dev.lucas.game.tiles.Tile Tile}
	 * **/
	public void render(Graphics g) {
		
		// prevents the game loading every single tile at once.
		int x_start = (int) Math.max(0, handler.getGameCamera().getX_offset() / Tile.TILEWIDTH);
		int y_start = (int) Math.max(0, handler.getGameCamera().getY_offset() /Tile.TILEHEIGHT);
		
		int x_end = (int) Math.min(width, (handler.getGameCamera().getX_offset() + handler.getWidth()) / Tile.TILEWIDTH+1);
		int y_end = (int) Math.min(height, (handler.getGameCamera().getY_offset() + handler.getHeight()) / Tile.TILEHEIGHT+1);
		
		// renders each tile
		for (int y = y_start; y < y_end;y++){
			for (int x = x_start; x < x_end;x++) {
				getTile(x,y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getX_offset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getY_offset()));
			}
		}
		// Items
		
		item_manager.render(g);
		
		// Entities
		
		entity_manager.render(g);
		
		// For the UI
		
		ui_manager.render(g);
	}
	
	/**
	 * <i><b>getTile</b></i>
	 * <pre>	public Tile getTile(int x, 
	 *                            int y)</pre>
	 * <p>This method returns a tile at a specific x, y coordinate. It returns a Grass tile if the x and y coordinates are out of world bounds and returns a Missing tile if the tile
	 *  type does not exist.</p>
	 * @param
	 * @return Tile
	 * @see {@link dev.lucas.game.tiles.Tile}
	 * **/
	public Tile getTile(int x,int y) {
		// returns a tile at an x and y coordinate, and returna a grass tile if out of the game bounds and returns a missing texture tile of the tile grabed is null.
		if ( x < 0 || y < 0 || x >= width|| y>= height){
			return Tile.grass_tile;
		}
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null) {
			return Tile.missing_texture_tile;
		}
		return t;
	}
	

	/**
	 * <i><b>generateWorld</b></i>
	 * <pre>	public void generateWorld(String path)</pre>
	 * <p>This method generates a world by custom coordinates. This is a developer tool only (i.e. USE AT OWN RISK).</p>
	 * @param
	 * @return void
	 * **/
	@SuppressWarnings("unused")
	private void generateWorld(String path) { // generates the world (when i want to add changes), automatically!
		Random rand = new Random();
		String brick;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			int width = 3012,height = 3022;
			bw.write(width + " " + height + '\n');
			bw.write("1506 1511\n" );
			for (int y = 1; y <= height; y++) {
				for (int x = 1; x <= width; x++) {
					if ((y == 1 || y == height)&& (x >=1 && x<= width) ||
						(y >= 2 && y <=height && (x == 1 || x == width))){
						bw.write("254 ");
						continue;
					}
					if (y >= 2 && y < 502) {
						bw.write("5 ");
						continue;
					}
					if (y == 502 && (x == 502)) {
						bw.write("23 ");
						continue;
					}
					if (y == 502 && x >= 503 && x <= 2509){
						if (x == 1506 || x == 1507 ) {
							bw.write("5 ");
							continue;
						}
						bw.write("16 ");
						continue;
					}	
					if (y == 502 && x == 2510) {
						bw.write("29 ");
						continue;
					}
					if (y == 503 && (x >= 503 && x <= 2509)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("11 ");
							continue;
						}
						brick = Integer.toString(rand.nextInt(5)+37);
						bw.write(brick + " ");
						continue;
					}
					if (y > 502 && y < 2519 && ((x == 502 || x == 2510))) {
						bw.write("22 ");
						continue;
					}
					if (y > 502 && y <= 803 && (x > 502 && x < 2510)){
						bw.write("4 ");
						continue;
					}
					if (y == 804 && x == 804) {
						bw.write("24 ");
						continue;
					}
					if (y == 804 && (x > 804 && x < 2210)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("4 ");
							continue;
						}
						bw.write("17 ");
						continue;
					}
					if (y == 805 && (x > 804 && x < 2210)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("12 ");
							continue;
						}
						brick = Integer.toString(rand.nextInt(5)+37);
						bw.write(brick + " ");
						continue;
					}
					if (y == 804 && x == 2210) {
						bw.write("30 ");
						continue;
					}
					if (y > 804 && y < 2218 && (x == 804 || x == 2210)) {
						bw.write("22 ");
						continue;
					}
					if (y > 1055 && y <= 1255 && (x > 1054 && x < 1960)) {
						bw.write("2 ");
						continue;
					}
					if (y == 1256 && x == 1256) {
						bw.write("26 ");
						continue;
					}
					if (y == 1256 && x == 1758) {
						bw.write("32 ");
						continue;
					}
					if (y == 1256 & (x > 1256 && x < 1758)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("2 ");
							continue;
						}
						bw.write("19 ");
						continue;
					}
					if (y == 1257 && (x > 1256 && x < 1758)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("14 ");
							continue;
						}
						brick = Integer.toString(rand.nextInt(5)+37);
						bw.write(brick + " ");
						continue;
					}
					if (y > 1256 && y < 1766 && (x == 1256||x == 1758)) {
						bw.write("22 ");
						continue;
					}
					if (y > 1257 && y<= 1408 && (x > 1256 && x < 1758)) {
						bw.write("1 ");
						continue;
					}
					if (y > 1257 && y < 1766 && ((x > 1256 && x <= 1407) || (x >= 1607  && x < 1758))) {
						bw.write("1 ");
						continue;
					}
					if (y == 1409 && x == 1408) {
						bw.write("27 ");
						continue;
					}
					if (y == 1409 && x == 1606) {
						bw.write("33 ");
						continue;
					}
					if (y == 1409 && (x > 1408 && x < 1606)) {
						if (x == 1506 || x == 1507) {
							bw.write("1 ");
							continue;
						}
						bw.write("20 ");
						continue;
					}
					if (y == 1410 && (x > 1408 && x < 1606)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("15 ");
							continue;
						}
						brick = Integer.toString(rand.nextInt(5)+37);
						bw.write(brick + " ");
						continue;
					}
					if (y > 1409 && y < 1613 && (x == 1408 || x == 1606)) {
						bw.write("22 ");
						continue;
					}
					if (y == 1613 && x == 1408) {
						bw.write("35 ");
						continue;
					}
					if (y == 1613 && (x > 1408 && x < 1606)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("0 ");
							continue;
						}
						bw.write("21 ");
						continue;
					}
					if (y == 1614 && (x >= 1408 && x <= 1606)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("6 ");
							continue;
						}
						brick = Integer.toString(rand.nextInt(5)+37);
						bw.write(brick + " ");
						continue;
					}
					if (y == 1613 && x == 1606) {
						bw.write("36 ");
						continue;
					}
					if (y >= 1615 && y < 1766 & (x > 1256 && x < 1758)) {
						bw.write("1 ");
						continue;
					}
					if (y == 1766 && x == 1256) {
						bw.write("35 ");
						continue;
					}
					if (y == 1766 && x == 1758){
						bw.write("36 ");
						continue;
					}
					if (y == 1766 && (x > 1256 && x < 1758)) {
						if (x == 1506 || x == 1507) {
							bw.write("1 ");
							continue;
						}
						bw.write("20 ");
						continue;
					}
					if (y == 1767 && (x >= 1256 && x <= 1758)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("7 ");
							continue;
						}
						brick = Integer.toString(rand.nextInt(5)+37);
						bw.write(brick + " ");
						continue;
					}
					if (y > 1055 && y < 1967 && ((x > 1055 && x <= 1255) || (x > 1758 && x < 1960))) {
						bw.write("2 ");
						continue;
					}
					if (y > 1767 && y < 1967 && (x > 1054 && x < 1960)) {
						bw.write("2 ");
						continue;
					}
					if (y > 804 && y < 1054 && (x > 804 && x < 2210)) {
						bw.write("3 ");
						continue;
					}
					if (y == 1054 && x == 1054) {
						bw.write("25 ");
						continue;
					}
					if (y == 1054 && x == 1960) {
						bw.write("31 ");
						continue;
					}
					if (y == 1054 && (x > 1054 && x < 1960)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("3 ");
							continue;
						}
						bw.write("18 ");
						continue;
					}
					if (y == 1055 && (x > 1054 && x < 1960)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("13 ");
							continue;
						}
						brick = Integer.toString(rand.nextInt(5)+37);
						bw.write(brick + " ");
						continue;
					}
					if (y > 1054 && y < 1967 && (x == 1054 || x == 1960)) {
						bw.write("22 ");
						continue;
					}
					if (y == 1967 && x == 1054) {
						bw.write("35 ");
						continue;
					}
					if (y == 1967 && x == 1960) {
						bw.write("36 ");
						continue;
					}
					if (y == 1967 && (x > 1054 && x < 1960)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("2 ");
							continue;
						}
						bw.write("19 ");
						continue;
					}
					if (y == 1968 && (x >= 1054 && x <= 1960)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("8 ");
							continue;
						}
						brick = Integer.toString(rand.nextInt(5)+37);
						bw.write(brick + " ");
						continue;
					}
					if (y > 1968 && y < 2218 && (x > 804 && x < 2210)) {
						bw.write("3 ");
						continue;
					}
					if (y >= 1054 && y < 2218 && ((x > 804 && x < 1054) || (x > 1960 && x < 2210)) ) {
						bw.write("3 ");
						continue;
					}
					if (y == 2218 && x == 804) {
						bw.write("35 ");
						continue;
					}
					if (y == 2218 && x == 2210) {
						bw.write("36 ");
						continue;
					}
					if (y == 2218 && (x > 804 && x < 2210)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("3 ");
							continue;
						}
						bw.write("18 ");
						continue;
					}
					if (y == 2219 && (x >= 804 && x <= 2210)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("9 ");
							continue;
						}
						brick = Integer.toString(rand.nextInt(5)+37);
						bw.write(brick + " ");
						continue;
					}
					if (y > 502 && y < 2519 && ((x > 502 && x <= 803)||(x > 2210 && x < 2510))) {
						bw.write("4 ");
						continue;
					}
					if (y < 2519 && y > 2219 && (x > 502 && x < 2510)) {
						bw.write("4 ");
						continue;
					}
					if (y == 2519 && x == 502) {
						bw.write("35 ");
						continue;
					}
					if (y == 2519 && x == 2510) {
						bw.write("36 ");
						continue;
					}
					if (y == 2519 && (x >= 503 && x <= 2509)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("4 ");
							continue;
						}
						bw.write("17 ");
						continue;
					}
					if (y == 2520 && (x >= 502 && x <= 2510)) {
						if (x == 1506 || x == 1507 ) {
							bw.write("10 ");
							continue;
						}
						brick = Integer.toString(rand.nextInt(5)+37);
						bw.write(brick + " ");
						continue;
					}
					if (y >= 502 && ((x >=2 && x<=502)||(x >= 2510 && x <= width-1))) {
						bw.write("5 ");
						continue;
					}
					if (y > 2520 && y <= height-1){
						bw.write("5 ");
						continue;
					}
					bw.write("0 ");
					continue;
				}
				if (y != height-1)
					bw.write('\n');
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("World could not be generated");
			System.exit(1);
		}
		
		
	}
	
	/**
	 * <i><b>loadWorld</b></i>
	 * <pre>	public void loadWorld(String path)</pre>
	 * <p>This method load a file from the parsed String as a path. It then convert the file into a String and then into a String Array. Each index value is made when 
	 * ever there is a space in the file. It then enters each of the String array values into a multidimensional Array.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.utils.Utils Utils}, {@link dev.lucas.game.tiles.Tile Tile}
	 * **/
	private void loadWorld(String path){
		// takes a file converts it into a string, then takes the string splits it into an array and each entry is decided by spaces
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");

		// gets the width and height of the world by looking at the first two data entries
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		
		// gets the spawn_x y for the player. and multiplies them by the tile size
		spawn_x = Utils.parseInt(tokens[2]);
		spawn_y = Utils.parseInt(tokens[3]);
		spawn_x *= Tile.TILEWIDTH;
		spawn_y *= Tile.TILEHEIGHT;
		
		// creates a new multidimensional int array at the dimensions of the world 
		tiles = new int[width][height];
		
		// enters each tile code into the array from the tokens array
		for (int y = 0;y < height; y++) {
			for (int x = 0;x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x+y*width) + 4]);
			}
		}
	}

	
	// getters and setters
	/**
	 * <i><b>getWidth</b></i>
	 * <pre>	public int getWidth()</pre>
	 * <p>Gets the width of the world.</p>
	 * @param None
	 * @return int
	 * **/
	public int getWidth() {
		return width;
	}

	/**
	 * <i><b>getHeight</b></i>
	 * <pre>	public int getHeight()</pre>
	 * <p>Gets the height of the world.</p>
	 * @param None
	 * @return int
	 * **/
	public int getHeight() {
		return height;
	}

	/**
	 * <i><b>getEntity_manager</b></i>
	 * <pre>	public EntityManager getEntity_manager()</pre>
	 * <p>Gets the EntityManager.</p>
	 * @param None
	 * @return EntityManager
	 * @see {@link dev.lucas.game.entities.EntityManager EntityManager}
	 * **/
	public EntityManager getEntity_manager() {
		return entity_manager;
	}

	/**
	 * <i><b>getHandler</b></i>
	 * <pre>	public Handler getHandler()</pre>
	 * <p>Gets the handler.</p>
	 * @param None
	 * @return Handler
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public Handler getHandler() {
		return handler;
	}

	/**
	 * <i><b>setHandler</b></i>
	 * <pre>	public void setHandler(Handler handler)</pre>
	 * <p>Sets the Handler.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	/**
	 * <i><b>getItem_manager</b></i>
	 * <pre>	public ItemManager getItem_manager()</pre>
	 * <p>Gets the ItemManager.</p>
	 * @param None
	 * @return ItemManager
	 * @see {@link dev.lucas.game.items.ItemManager ItemManager}
	 * **/
	public ItemManager getItem_manager() {
		return item_manager;
	}

	/**
	 * <i><b>setItem_manager</b></i>
	 * <pre>	public void setItem_manager(ItemManager item_manager)</pre>
	 * <p>Sets the ItemManager.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.items.ItemManager ItemManager}
	 * **/
	public void setItem_manager(ItemManager item_manager) {
		this.item_manager = item_manager;
	}

	/**
	 * <i><b>getUi_manager</b></i>
	 * <pre>	public UIManager getUi_manager()</pre>
	 * <p>Gets the world's UIManager</p>
	 * @param None
	 * @return UIManager
	 * @see {@link dev.lucas.game.ui.UIManager UIManager}
	 * **/
	public UIManager getUi_manager() {
		return ui_manager;
	}
	
	
}
