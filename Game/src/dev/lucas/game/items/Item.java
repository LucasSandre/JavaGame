package dev.lucas.game.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Assets;

public class Item {
	
	// Initializes an Array called items that will store every type of Item
	public static Item[] items = new Item[256];
	public static Item wood_item = new Item(Assets.wood,"Wood",0);
	public static Item rock_item = new Item(Assets.boulder,"Rock",1);
	public static Item tree_item = new Item(Assets.tree,"Sapling",2);
	
	// Class
	public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;
	
	// Initializes necessary functional qualities for items
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int ID;
	protected float x ,y ;
	protected int count;
	protected boolean picked_up = false;
	protected Rectangle bounds;
	
	// Initializes timer variables for item 'floating' and a state variable
	private long timer = 0,
			     last_time = System.currentTimeMillis(),
			     speed = 500;
	private boolean UpDown = false;
	
	// The class Constructor takes in a texture, a string and an id, saves these variables and makes the item count 1, makes the class instance saved in an Array stored at index 'id'. sets the bounding box for the item.
	/**
	 * <i><b>Item</b></i>
	 * <pre>	public Item(BufferedImage texture, 
	 *                           String name,
	 *                              int ID)</pre>
	 * <p>The class constructor saves the values into variables. makes the item count 1, makes the class instance saved in an Array stored at index id and 
	 * creates a bounding box.</p>
	 * @param BufferedImage texture,
	 * @param String name,
	 * @param Int ID
	 * **/
	public Item(BufferedImage texture, String name, int ID) {
		this.texture = texture;
		this.name = name;
		this.ID = ID;
		count = 1;
		
		items[ID] = this;
		bounds = new Rectangle((int) x, (int) y,ITEMWIDTH,ITEMHEIGHT);
		
	}
	
	/**
	 * <i><b>tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This method ticks all of the items. This method also checks if the player has pick up the item, and makes the item appear as its floating.</p>
	 * @param None
	 * @return void
	 * @see {@link dev.lucas.game.entities.creatures.Player Player} , {@link dev.lucas.game.inventory.Inventory Inventory}
	 * **/
	public void tick() {
		
		// Picking up check and adds the item into the players inventory.
		if (handler.getWorld().getEntity_manager().getPlayer().getCollisionBounds(0,0).intersects(bounds)) {
			picked_up = true;
			handler.getWorld().getEntity_manager().getPlayer().getInventory().addItem(this);
		}
		
		
		// Item Animation, increases and decreases the y value based on the value of 'UpDown' and sets the speed that this occurs at to 1/2 a second. and when the timer equals the speed variable it flips the state of 'UpDown'
		if (count >=1) {
			timer += System.currentTimeMillis() - last_time;
			last_time = System.currentTimeMillis();
			if (UpDown){
				setY(y - 0.35f);}
			else{
				setY(y + 0.35f);}
			if (timer >= speed){
				timer = 0;
				if (UpDown){
					UpDown = false;}
				else {
					UpDown = true;}
			}	
		}
	}
	
	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>This method renders the item. This method delegates to a separate render method.</p>
	 * @param
	 * @return void
	 * **/
	public void render(Graphics g) {
		// calls the render the item if handler is not empty.
		if (handler == null) {
			return;
		}
		render(g, (int) (x - handler.getGameCamera().getX_offset()), (int) (y - handler.getGameCamera().getY_offset()));
	}
	
	/**
	 * <i><b>render</b></i>
	 * <pre>	public void render(Graphics g, 
	 *                                int x, 
	 *                                int y)</pre>
	 * <p>This method renders the item.</p>
	 * @param
	 * @return void
	 * **/
	public void render(Graphics g, int x, int y) {
		// renders the item.
		g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
		if (handler.getGame().isDebug()) {
			g.drawRect(x, y, bounds.width, bounds.height);
		}
	}

	// creates a new Item at a position and returns the item.
	/**
	 * <i><b>createNew</b></i>
	 * <pre>	public Item createNew(int x, 
	 *                              int y)</pre>
	 * <p>This method creates a new item from the one that called it.</p>
	 * @param
	 * @return Item
	 * @see {@link dev.lucas.game.items}
	 * **/
	public Item createNew(int x, int y) {
		Item i = new Item(texture,name,ID);
		i.setPosition(x, y);
		return i;
	}
	
	// Sets the position of the item and changes the bounding boxes.
	/**
	 * <i><b>setPosition</b></i>
	 * <pre>	public void setPosition(int x, 
	 *                                int y)</pre>
	 * <p>This method sets the position of the item and its bounds.</p>
	 * @param None
	 * @return void
	 * **/
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
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
	 * <i><b>setHandler</b></i>
	 * <pre>	public void setHandler(Handler handler)</pre>
	 * <p>Sets the handler.</p>
	 * @param
	 * @return void
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	/**
	 * <i><b>getTexture</b></i>
	 * <pre>	public BufferedImage getTexture()</pre>
	 * <p>Gets the Item's texture.</p>
	 * @param
	 * @return BufferedImage
	 * **/
	public BufferedImage getTexture() {
		return texture;
	}

	/**
	 * <i><b>setTexture</b></i>
	 * <pre>	public void setTexture(BufferedImage texture)</pre>
	 * <p>Sets the Item's texture.</p>
	 * @param
	 * @return void
	 * **/
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	/**
	 * <i><b>getName</b></i>
	 * <pre>	public String getName()</pre>
	 * <p>Gets the items name.</p>
	 * @param None.
	 * @return String
	 * **/
	public String getName() {
		return name;
	}

	/**
	 * <i><b>setName</b></i>
	 * <pre>	public void setName(String name)</pre>
	 * <p>Sets the Item's name.</p>
	 * @param
	 * @return void
	 * **/
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <i><b>getX</b></i>
	 * <pre>	public int getX()</pre>
	 * <p>Gets the x value.</p>
	 * @param None
	 * @return int
	 * **/
	public int getX() {
		return (int) x;
	}

	/**
	 * <i><b>setX</b></i>
	 * <pre>	public void setX(float x)</pre>
	 * <p>Sets the x value.</p>
	 * @param
	 * @return void
	 * **/
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * <i><b>getY</b></i>
	 * <pre>	public int getY()</pre>
	 * <p>Gets the y value.</p>
	 * @param None
	 * @return int
	 * **/
	public int getY() {
		return (int) y;
	}

	/**
	 * <i><b>setY</b></i>
	 * <pre>	public void setY(float y)</pre>
	 * <p>Sets the y value.</p>
	 * @param
	 * @return void
	 * **/
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * <i><b>getCount</b></i>
	 * <pre>	public int getCount()</pre>
	 * <p>Gets the count of the item.</p>
	 * @param None.
	 * @return int
	 * **/
	public int getCount() {
		return count;
	}

	/**
	 * <i><b>setCount</b></i>
	 * <pre>	public void setCount(int count)</pre>
	 * <p>Sets the count of the item.</p>
	 * @param None
	 * @return void
	 * **/
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * <i><b>getID</b></i>
	 * <pre>	public int getID()</pre>
	 * <p>Gets the items ID.</p>
	 * @param None
	 * @return int
	 * **/
	public int getID() {
		return ID;
	}

	/**
	 * <i><b>isPicked_up</b></i>
	 * <pre>	public boolean isPicked_up()</pre>
	 * <p>Gets the Item's picked up state.</p>
	 * @param None
	 * @return boolean
	 * **/
	public boolean isPicked_up() {
		return picked_up;
	}

	/**
	 * <i><b>setPicked_up</b></i>
	 * <pre>	public void setPicked_up(boolean picked_up)</pre>
	 * <p>Sets the Items picked up value.</p>
	 * @param
	 * @return void
	 * **/
	public void setPicked_up(boolean picked_up) {
		this.picked_up = picked_up;
	}

}
