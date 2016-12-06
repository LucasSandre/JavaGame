package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallBottomRightTile extends Tile{

	public BrickWallBottomRightTile(int id) {
		super(Assets.brick_bottom_right, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
