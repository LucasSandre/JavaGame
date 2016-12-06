package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallBattUpTwoTile extends Tile{

	public BrickWallBattUpTwoTile(int id) {
		super(Assets.brick_batt_up2, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
