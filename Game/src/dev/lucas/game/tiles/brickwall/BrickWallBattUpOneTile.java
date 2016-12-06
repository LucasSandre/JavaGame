package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallBattUpOneTile extends Tile{

	public BrickWallBattUpOneTile(int id) {
		super(Assets.brick_batt_up1, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
