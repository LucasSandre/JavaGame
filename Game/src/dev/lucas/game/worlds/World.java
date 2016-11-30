package dev.lucas.game.worlds;

import java.awt.Graphics;

import dev.lucas.game.Handler;
import dev.lucas.game.entities.EntityManager;
import dev.lucas.game.entities.creatures.Player;
import dev.lucas.game.entities.statics.Boulder;
import dev.lucas.game.entities.statics.Tree;
import dev.lucas.game.items.ItemManager;
import dev.lucas.game.tiles.Tile;
import dev.lucas.game.utils.Utils;

public class World {
	
	private int width, height;
	private int [][] tiles;
	private Handler handler;
	private int spawn_x,spawn_y;

	// Entities
	
	public EntityManager entity_manager;
	
	// Items
	private ItemManager item_manager;
	
	public World (Handler handler, String path) {
		this.handler = handler;
		entity_manager = new EntityManager(handler, new Player(handler , 100, 100));
		item_manager = new ItemManager(handler);
		
		// Temporary entity code
		entity_manager.addEntity(new Tree(handler, 100, 250));
		entity_manager.addEntity(new Boulder(handler, 100, 550));
		entity_manager.addEntity(new Tree(handler, 500, 250));
		entity_manager.addEntity(new Boulder(handler, 500, 550));
		
		loadWorld(path);
		
		entity_manager.getPlayer().setX(spawn_x);
		entity_manager.getPlayer().setX(spawn_y);
	}
	
	public void tick() {
		item_manager.tick();
		entity_manager.tick();
	}
	
	public void render(Graphics g) {
		
		int x_start = (int) Math.max(0, handler.getGameCamera().getX_offset() / Tile.TILEWIDTH);
		int y_start = (int) Math.max(0, handler.getGameCamera().getY_offset() /Tile.TILEHEIGHT);
		
		int x_end = (int) Math.min(width, (handler.getGameCamera().getX_offset() + handler.getWidth()) / Tile.TILEWIDTH+1);
		int y_end = (int) Math.min(height, (handler.getGameCamera().getY_offset() + handler.getHeight()) / Tile.TILEHEIGHT+1);
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
	}
	
	public Tile getTile(int x,int y) {
		if ( x < 0 || y < 0 || x >= width|| y>= height){
			return Tile.grass_tile;
		}
		
		
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null) {
			return Tile.dirt_tile;
		}
		return t;
	}
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");

		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		
		spawn_x = Utils.parseInt(tokens[2]);
		spawn_y = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for (int y = 0;y < height; y++) {
			for (int x = 0;x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x+y*width) + 4]);
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public EntityManager getEntity_manager() {
		return entity_manager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItem_manager() {
		return item_manager;
	}

	public void setItem_manager(ItemManager item_manager) {
		this.item_manager = item_manager;
	}
}
