package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallCrackOneTile extends Tile{

	public BrickWallCrackOneTile(int id) {
		super(Assets.brick_crack_1, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
