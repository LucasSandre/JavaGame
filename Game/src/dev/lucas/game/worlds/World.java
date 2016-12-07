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
		generateWorld("res/World/world.txt");
		loadWorld(path);
		entity_manager.getPlayer().setX(spawn_x);
		entity_manager.getPlayer().setY(spawn_y);
	}
	
	public void tick() {
		item_manager.tick();
		entity_manager.tick();
		System.out.println(handler.getWorld().getEntity_manager().getPlayer().getX()/ Tile.TILEWIDTH + "  " +
		handler.getWorld().getEntity_manager().getPlayer().getY()/ Tile.TILEHEIGHT);
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
	

	private void generateWorld(String path) {
		Random rand = new Random();
		String brick;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			int width = 3012,height = 3022;
			bw.write(width + " " + height + '\n');
			bw.write("1506 1511\n" );
			for (int y = 1; y <= height; y++) {
				for (int x = 1; x <= width; x++) {
					if (y==1||x==1||y==height||x==width){
						bw.write("255 ");
						continue;
					}
					if (y >= 2 && y <= 1002) {
						bw.write("5 ");
						continue;
					}
					if (y == 1003 && (x == 1003)) {
						bw.write("13 ");
						continue;
					}
					if (y == 1003 && x > 1003 && x < 2010){
						bw.write("6 ");
						continue;
					}	
					if (y == 1003 && x == 2010) {
						bw.write("19 ");
						continue;
					}
					if (y == 1004 && (x > 1003 && x < 2010)) {
						brick = Integer.toString(rand.nextInt(5)+27);
						bw.write(brick + " ");
						continue;
					}
					if (y > 1003 && y < 2019 && ((x == 1003 || x == 2010))) {
						bw.write("12 ");
						continue;
					}
					if (y == 2019 && x == 1003) {
						bw.write("25 ");
						continue;
					}
					if (y == 2019 && x == 2010) {
						bw.write("26 ");
						continue;
					}
					if (y == 2019 && (x > 1003 && x < 2010)) {
						bw.write("7 ");
						continue;
					}
					if (y == 2020 && (x >= 1003 && x <=2010)) {
						brick = Integer.toString(rand.nextInt(5)+27);
						bw.write(brick + " ");
						continue;
					}
					if (y > 1002 && ((x >=2 && x<=1002)||(x >= 2011 && x <= width-1))) {
						bw.write("5 ");
						continue;
					}
					if (y > 2020 && y <= height-1){
						bw.write("5 ");
						continue;
					}
					bw.write("0 ");
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
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");

		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		
		spawn_x = Utils.parseInt(tokens[2]);
		spawn_y = Utils.parseInt(tokens[3]);
		
		spawn_x *= Tile.TILEWIDTH;
		spawn_y *= Tile.TILEHEIGHT;
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
