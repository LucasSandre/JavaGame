package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallTopLeftThreeTile extends Tile{

	public BrickWallTopLeftThreeTile(int id) {
		super(Assets.brick_top_left3, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
