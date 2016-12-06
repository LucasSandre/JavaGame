package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallTopLeftTwoTile extends Tile{

	public BrickWallTopLeftTwoTile(int id) {
		super(Assets.brick_top_left2, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
