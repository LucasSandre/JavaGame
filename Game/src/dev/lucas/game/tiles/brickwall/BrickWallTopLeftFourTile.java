package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallTopLeftFourTile extends Tile{

	public BrickWallTopLeftFourTile(int id) {
		super(Assets.brick_top_left4, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
