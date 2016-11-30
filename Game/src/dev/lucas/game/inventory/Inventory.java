package dev.lucas.game.inventory;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.lucas.game.Handler;
import dev.lucas.game.items.Item;

public class Inventory {

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventory_items;
	
	public Inventory(Handler handler) {
		this.handler = handler;
		inventory_items = new ArrayList<Item>();
		
	}

	public void tick() {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
			active = !active;
		}
		if (!active){
			return;
		}
		
		System.out.println("INVENTORY");
		for (Item i : inventory_items) {
			System.out.println(i.getName() + "   " + i.getCount());
		}
	}
	
	public void render(Graphics g) {
		if (!active) {
			return;
		}
	}

	// Inventory Methods
	
	public void addItem(Item item) {
		for (Item i : inventory_items) {
			if (i.getID() == item.getID()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventory_items.add(item);
	}
	
	
	// Getters and Setters
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
