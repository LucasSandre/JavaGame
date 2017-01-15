package dev.lucas.game.inventory;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import dev.lucas.game.Handler;
import dev.lucas.game.items.Item;

/** 
 * <i><b>Inventory</b></i>
 * <pre> public class Inventory</pre>
 * <p>This class defines all the necessary methods and variables for (a/an) Inventory</p>
 * **/
public class Inventory {

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventory_items;
	
	/**
	 * <i><b>Inventory</b></i>
	 * <pre>	public Inventory(Handler handler)</pre>
	 * <p>The class constructor saves the parsed value in a variable and creates an ItemsArrayList</p>
	 * @param Handler handler
	 * @see {@link dev.lucas.game.Handler Handler} , {@link dev.lucas.game.items.Item Item}
	 * **/
	public Inventory(Handler handler) {
		this.handler = handler;
		inventory_items = new ArrayList<Item>();
		
	}

	public void tick() {
		// If the e key was just pressed it reverses the active variable
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
			active = !active;
		}
		// If active is false it returns.
		if (!active){
			return;
		}
		 // Temp code
		System.out.println("INVENTORY");
		for (Item i : inventory_items) {
			System.out.println(i.getName() + "   " + i.getCount());
		}
	}
	
	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render()</pre>
	 * <p>This method checks the active variable is true to render the Inventory.</p>
	 * @param
	 * @return void
	 * 	 * **/
	public void render(Graphics g) {
		if (!active) {
			return;
		}
	}

	// Inventory Methods
	
	/**
	 * <i><b>addItem</b></i>
	 * <pre>	public void addItem(Item item)</pre>
	 * <p>This method checks if the to be added items id is equal to any in the Inventory. If one does it increases the item count by the count of the Item added. If it 
	 * does not it adds the item to the Inventory.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.inventory}
	 * **/
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
	
	/**
	 * <i><b>getHandler</b></i>
	 * <pre>	public Handler getHandler()</pre>
	 * <p>Gets the handler.</p>
	 * @param None
	 * @return Handler
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public Handler getHandler() {
		return handler;
	}

	/**
	 * <i><b>isActive</b></i>
	 * <pre>	public boolean isActive()</pre>
	 * <p>Gets the active value.</p>
	 * @param None
	 * @return boolean
	 * **/
	public boolean isActive() {
		return active;
	}

	/**
	 * <i><b>setHandler</b></i>
	 * <pre>	public void setHandler(Handler handler)</pre>
	 * <p>SSets the Inventories handler to the parsed value.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
