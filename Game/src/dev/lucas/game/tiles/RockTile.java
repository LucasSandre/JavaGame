package dev.lucas.game.tiles;



import dev.lucas.game.gfx.Assets;

public class RockTile extends Tile {

	public RockTile(int id) {
		super(Assets.stone, id);
		
	}
	@Override
	public boolean isSolid() {
		return true;
	}
}
