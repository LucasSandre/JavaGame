package dev.lucas.game.tiles;

import dev.lucas.game.gfx.Assets;

public class EdgeTile extends Tile{

	public EdgeTile(int id) {
		super(Assets.edge, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
