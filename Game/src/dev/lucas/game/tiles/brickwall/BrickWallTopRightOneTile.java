package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallTopRightOneTile extends Tile {

	public BrickWallTopRightOneTile(int id) {
		super(Assets.brick_top_right1, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
