package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallCrackThreeTile extends Tile {

	public BrickWallCrackThreeTile(int id) {
		super(Assets.brick_crack_3, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
