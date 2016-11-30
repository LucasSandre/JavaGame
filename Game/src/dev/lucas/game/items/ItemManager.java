package dev.lucas.game.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import dev.lucas.game.Handler;

public class ItemManager {

	
	private ArrayList<Item> items;
	private Handler handler;
	
	public ItemManager(Handler handler) {
		this.handler = handler;
		items = new ArrayList<Item>();
	}
	
	public void tick() {
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {
			Item i = it.next();
			i.tick();
			if (i.isPicked_up()) {
				it.remove();
			}
		}
	}
	
	public void render(Graphics g) {
		for(Item i: items){
			i.render(g);
		}
	}
	public void addItem(Item i){
		i.setHandler(handler);
		items.add(i);
	}

	// Getters and Setters
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
