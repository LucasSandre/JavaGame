package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallGrateTile extends Tile{

	public BrickWallGrateTile(int id) {
		super(Assets.brick_grate, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
