package dev.lucas.game.entities.statics;

import java.awt.Graphics;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Assets;
import dev.lucas.game.items.Item;
import dev.lucas.game.tiles.Tile;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y) {
		super(handler,x,y,Tile.TILEWIDTH*2, Tile.TILEHEIGHT*2);
		bounds.x = 40;
		bounds.y = (int) (height/1.5f);
		bounds.width = width - 90;
		bounds.height = (int) (height - height/1.2f);
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x-handler.getGameCamera().getX_offset()), (int) (y-handler.getGameCamera().getY_offset()), width, height, null);
		
	}

	@Override
	public void die() {
		handler.getWorld().getItem_manager().addItem(Item.wood_item.createNew((int)x, (int) y));
		handler.getWorld().getItem_manager().addItem(Item.tree_item.createNew((int)(x+64),(int)(y+64) ));
	}

}
