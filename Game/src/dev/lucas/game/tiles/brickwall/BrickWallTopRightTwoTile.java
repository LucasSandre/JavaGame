package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallTopRightTwoTile extends Tile{

	public BrickWallTopRightTwoTile(int id) {
		super(Assets.brick_top_right2, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
