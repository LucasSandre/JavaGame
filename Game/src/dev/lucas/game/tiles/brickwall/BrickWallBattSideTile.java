package dev.lucas.game.tiles.brickwall;

import dev.lucas.game.gfx.Assets;
import dev.lucas.game.tiles.Tile;

public class BrickWallBattSideTile extends Tile{

	public BrickWallBattSideTile(int id) {
		super(Assets.brick_batt_side, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
