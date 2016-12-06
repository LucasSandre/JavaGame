package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallTopRightSixTile extends Tile{

	public BrickWallTopRightSixTile(int id) {
		super(Assets.brick_top_right6, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
