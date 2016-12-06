package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallTopRightThreeTile extends Tile{

	public BrickWallTopRightThreeTile(int id) {
		super(Assets.brick_top_right3, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
