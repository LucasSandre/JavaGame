package dev.lucas.game.entities.statics;

import java.awt.Graphics;
import java.util.Random;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Assets;
import dev.lucas.game.items.Item;
import dev.lucas.game.tiles.Tile;

public class Boulder extends StaticEntity {

	// The Class Constructor takes in a handler and an x and y
		// Passes the handler, x, y, and size to the super constructor
		// Creates the bounding box's
	public Boulder(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		bounds.x = 0;
		bounds.y = (int) (height / 1.5f);
		bounds.width = width;
		bounds.height = (int) (height - height/1.02f);
	}

	@Override
	public void tick() {
		// Dosent tick
	}

	@Override
	public void render(Graphics g) {
		// renders the boulder on screen
		g.drawImage(Assets.boulder, (int) (x-handler.getGameCamera().getX_offset()), (int) (y-handler.getGameCamera().getY_offset()), width, height, null);
	}

	@Override
	public void die() {
		// creates a new item in the world and drops it in a random position inside itself.
		handler.getWorld().getItem_manager().addItem(Item.rock_item.createNew(
				(int) itemDropX(x, Tile.TILEWIDTH),
				(int) itemDropY(y, Tile.TILEHEIGHT)));
	}

	// Methods for the random placement
	@Override
	public float itemDropX(float x, int width) {
		Random rand = new Random();
		int x_offset = rand.nextInt(width);
		return x + x_offset;
		
	}

	@Override
	public float itemDropY(float y, int height) {
		Random rand = new Random();
		int y_offset = rand.nextInt(height);
		return y + y_offset;
	}

}
