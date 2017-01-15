package dev.lucas.game.entities.statics;

import java.awt.Graphics;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Assets;
import dev.lucas.game.items.Item;
import dev.lucas.game.tiles.Tile;

/** 
 * <i><b>Tree</b></i>
 * <pre> public class Tree extends StaticEntity</pre>
 * <p>This class defines all the necessary methods and variables for a Tree.</p>
 * @see {@link dev.lucas.game.entities.statics.StaticEntity StaticEntity}
 * **/
public class Tree extends StaticEntity{

	// The Class Constructor, takes in a handler and an x and y
		// passes the handler, x , y, and size variables to the super constructor
		// creates the bounding box
	
	/**
	 * <i><b>Tree</b></i>
	 * <pre>	public Tree(Handler handler, 
	 *                      float x,
	 *                      float y)</pre>
	 * <p>The class constructor takes its parsed values and sends them to the super constructor along with 2 times the TileHeight and TileWidth from the Tile class. 
	 * It also creates the bounding box.</p>
	 * @param Handler handler,
	 * @param Float x,
	 * @param Float y
	 * @see {@link dev.lucas.game.tiles.Tile Tile}
	 * **/
	public Tree(Handler handler, float x, float y) {
		super(handler,x,y,Tile.TILEWIDTH*2, Tile.TILEHEIGHT*2);
		bounds.x = 40;
		bounds.y = (int) (height/1.4f);
		bounds.width = width - 90;
		bounds.height = (int) (height - height/1.2f);
	}

	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>The Tree class does not tick.</p>
	 * @param None
	 * @return None
	 * **/
	@Override
	public void tick() {
		// does not tick
		
	}

	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>This method renders the tree.</p>
	 * @param
	 * @return None 
	 * @see {@link dev.lucas.game.gfx.Assets Assets}
	 * **/
	@Override
	public void render(Graphics g) {
		// draws the tree in the world
		g.drawImage(Assets.tree, (int) (x-handler.getGameCamera().getX_offset()), (int) (y-handler.getGameCamera().getY_offset()), width, height, null);
		
		if (handler.getGame().isDebug()) {
			g.drawRect((int)(x + bounds.x - handler.getGameCamera().getX_offset()), (int) (y + bounds.y - handler.getGameCamera().getY_offset()), bounds.width, bounds.height);
		}
	}

	/**
	 * <i><b>die</b></i>
	 * <pre>	public void die()</pre>
	 * <p>This method makes the tree drop a sapling and a log.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.entities.statics}
	 * **/
	@Override
	public void die() {
		// drops a sapling and a log when the tree dies.
		handler.getWorld().getItem_manager().addItem(Item.wood_item.createNew(
				(int) itemDropX(x, Tile.TILEWIDTH*2),
				(int) itemDropY(y, Tile.TILEHEIGHT*2)));
		
		handler.getWorld().getItem_manager().addItem(Item.tree_item.createNew(
				(int) itemDropX(x, Tile.TILEWIDTH*2) ,
				(int) itemDropY(y, Tile.TILEHEIGHT*2) ));
	}



}
