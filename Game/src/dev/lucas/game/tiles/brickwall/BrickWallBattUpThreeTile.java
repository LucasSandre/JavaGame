package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallBattUpThreeTile extends Tile{

	public BrickWallBattUpThreeTile(int id) {
		super(Assets.brick_batt_up3, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
