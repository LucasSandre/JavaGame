package dev.lucas.game.tiles;

import dev.lucas.game.gfx.Assets;

public class MissingTile extends Tile{

	public MissingTile(int id) {
		super(Assets.missing, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
