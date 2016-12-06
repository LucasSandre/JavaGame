package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallBottomLeftTile extends Tile{

	public BrickWallBottomLeftTile(int id) {
		super(Assets.brick_bottom_left, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
