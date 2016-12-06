package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallBattUpFourTile extends Tile{

	public BrickWallBattUpFourTile(int id) {
		super(Assets.brick_batt_up4, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}

}
