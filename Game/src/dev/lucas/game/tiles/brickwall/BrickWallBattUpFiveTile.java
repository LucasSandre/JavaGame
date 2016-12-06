package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallBattUpFiveTile extends Tile{

	public BrickWallBattUpFiveTile(int id) {
		super(Assets.brick_batt_up5, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
