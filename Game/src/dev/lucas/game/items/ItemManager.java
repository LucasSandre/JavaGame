package dev.lucas.game.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import dev.lucas.game.Handler;

/** 
 * <i><b>ItemManager</b></i>
 * <pre> public class ItemManager</pre>
 * <p>This class defines all the necessary methods and variables for an ItemManager</p>
 * **/
public class ItemManager {
	// FOR IN WORLD ITEMS
	
	// Initializes private variables	
	private ArrayList<Item> items;
	private Handler handler;
	
	// The Class Constructor takes in a handler. It saves the handler and creates an ArrayList
	/**
	 * <i><b>ItemManager</b></i>
	 * <pre>	public ItemManager(Handler handler)</pre>
	 * <p>The class constructor saves the parsed object and saves it in a variable and creates an ArrayList.</p>
	 * @param Handler handler
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public ItemManager(Handler handler) {
		this.handler = handler;
		items = new ArrayList<Item>();
	}
	
	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This method updates all the items in the world and removes ones that were picked up.</p>
	 * @param None
	 * @return void
	 * @see {@link dev.lucas.game.items.Item Item} , {@link dev.lucas.game.item.Inventory Inventory}
	 * **/
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
	
	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>This method renders all the items in the world.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.items.Item Item}
	 * **/
	public void render(Graphics g) {
		// renders every item
		for(Item i: items){
			i.render(g);
		}
	}
	
	// An Adder
	/**
	 * <i><b>addItem</b></i>
	 * <pre>	public void addItem(Item i)</pre>
	 * <p>This method adds the parsed Item into the ItemsArrayList.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.items.Item Item}
	 * **/
	public void addItem(Item i){
		i.setHandler(handler);
		items.add(i);
	}

	// Getters and Setters
	
	/**
	 * <i><b>getHandler</b></i>
	 * <pre>	public Handler getHandler()</pre>
	 * <p>Gets the Handler.</p>
	 * @param
	 * @return Handler
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public Handler getHandler() {
		return handler;
	}

	/**
	 * <i><b>setHandler</b></i>
	 * <pre>	public void setHandler(Handler handler)</pre>
	 * <p>Sets the ItemManagaers Handler.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
