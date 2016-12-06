package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallTopLeftFiveTile extends Tile{

	public BrickWallTopLeftFiveTile(int id) {
		super(Assets.brick_top_left5, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
