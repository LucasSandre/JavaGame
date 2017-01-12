package dev.lucas.game.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import dev.lucas.game.Handler;

public class ItemManager {
	// FOR IN WORLD ITEMS
	
	// Initializes private variables	
	private ArrayList<Item> items;
	private Handler handler;
	
	// The Class Constructor takes in a handler. It saves the handler and creates an ArrayList
	public ItemManager(Handler handler) {
		this.handler = handler;
		items = new ArrayList<Item>();
	}
	
	public void tick() {
		// Updates every item, and removes it if it is 'picked up'
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
		// renders every item
		for(Item i: items){
			i.render(g);
		}
	}
	
	// An Adder
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
