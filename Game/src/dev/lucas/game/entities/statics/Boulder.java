package dev.lucas.game.entities.statics;

import java.awt.Graphics;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Assets;
import dev.lucas.game.items.Item;
import dev.lucas.game.tiles.Tile;

public class Boulder extends StaticEntity {

	public Boulder(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		bounds.x = 0;
		bounds.y = (int) (height / 1.5f);
		bounds.width = width;
		bounds.height = (int) (height - height/1.02f);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.boulder, (int) (x-handler.getGameCamera().getX_offset()), (int) (y-handler.getGameCamera().getY_offset()), width, height, null);
	}

	@Override
	public void die() {
		handler.getWorld().getItem_manager().addItem(Item.rock_item.createNew((int) x, (int) y));
		
	}

}
