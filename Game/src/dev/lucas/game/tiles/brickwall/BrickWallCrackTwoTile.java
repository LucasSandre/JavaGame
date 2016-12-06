package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallCrackTwoTile extends Tile{

	public BrickWallCrackTwoTile(int id) {
		super(Assets.brick_crack_2, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
