package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallTopRightFourTile extends Tile{

	public BrickWallTopRightFourTile(int id) {
		super(Assets.brick_top_right4, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
