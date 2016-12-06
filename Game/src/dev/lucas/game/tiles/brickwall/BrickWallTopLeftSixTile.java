package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallTopLeftSixTile extends Tile{

	public BrickWallTopLeftSixTile(int id) {
		super(Assets.brick_top_left6, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
