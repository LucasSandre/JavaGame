package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallCleanTile extends Tile {

	public BrickWallCleanTile(int id) {
		super(Assets.brick_clean, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
