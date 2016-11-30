package dev.lucas.game.entities.statics;

import dev.lucas.game.Handler;
import dev.lucas.game.entities.Entity;

public abstract class StaticEntity extends Entity{

	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler,x,y,width,height);
	}
	

}
