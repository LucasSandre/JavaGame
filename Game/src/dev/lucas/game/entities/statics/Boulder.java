package dev.lucas.game.entities.statics;

import java.awt.Graphics;


import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Assets;
import dev.lucas.game.items.Item;
import dev.lucas.game.tiles.Tile;

/** 
 * <i><b>Boulder</b></i>
 * <pre> public class Boulder extends StaticEntity</pre>
 * <p>This class defines all the necessary methods and variables for a Boulder</p>
 * @see {@link dev.game.lucas.entities.statics.StaticEntity StaticEntity}
 * **/
public class Boulder extends StaticEntity {

	// The Class Constructor takes in a handler and an x and y
		// Passes the handler, x, y, and size to the super constructor
		// Creates the bounding box's
	
	/**
	 * <i><b> Boulder</b></i>
	 * <pre>	public Boulder(Handler handler,
	 *                         float x,
	 *                         float y)</pre>
	 * <p>The class constructor takes in its passed values to the super constructor along with the TileHeight and TileWidth from the tile class. The constructor also creates the bounding box.</p>
	 * @param Handler handler,
	 * @param Float x,
	 * @param Float y
	 * @see {@link dev.lucas.game.entities.statics.StaticEntity StaticEntity}, {@link dev.lucas.game.tiles.Tile Tile}
	 * **/
	public Boulder(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		bounds.x = 0;
		bounds.y = (int) (height / 2.5f);
		bounds.width = width;
		bounds.height = (int) (height - height/2.5f);
	}

	
	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>The Boulder does not tick.</p>
	 * @param None
	 * @return None
	 * **/
	@Override
	public void tick() {
		// Dosent tick
	}

	
	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>This method renders the boulder.</p>
	 * @param 
	 * @return None
	 * @see {@link dev.lucas.game.gfx.Assets Assets}
	 * **/
	@Override
	public void render(Graphics g) {
		// renders the boulder on screen
		g.drawImage(Assets.boulder, (int) (x-handler.getGameCamera().getX_offset()), (int) (y-handler.getGameCamera().getY_offset()), width, height, null);
		
		if (handler.getGame().isDebug()) {
			g.drawRect((int)(x + bounds.x - handler.getGameCamera().getX_offset()), (int) (y + bounds.y - handler.getGameCamera().getY_offset()), bounds.width, bounds.height);
		}
	}

	/**
	 * <i><b>die</b></i>
	 * <pre>	public void die()</pre>
	 * <p>This method drops an item from the Boulder when it dies.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.items.Item}
	 * **/
	@Override
	public void die() {
		// creates a new item in the world and drops it in a random position inside itself.
		handler.getWorld().getItem_manager().addItem(Item.rock_item.createNew(
				(int) itemDropX(x, Tile.TILEWIDTH),
				(int) itemDropY(y, Tile.TILEHEIGHT)));
	}
}
