package dev.lucas.game.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.lucas.game.Handler;
import dev.lucas.game.gfx.Assets;

public class Item {
	
	// Initializes an Array called items that will store everytype of Item
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
	public Item(BufferedImage texture, String name, int ID) {
		this.texture = texture;
		this.name = name;
		this.ID = ID;
		count = 1;
		
		items[ID] = this;
		bounds = new Rectangle((int) x, (int) y,ITEMWIDTH,ITEMHEIGHT);
		
	}
	
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
	
	public void render(Graphics g) {
		// calls the render the item if handler is not empty.
		if (handler == null) {
			return;
		}
		render(g, (int) (x - handler.getGameCamera().getX_offset()), (int) (y - handler.getGameCamera().getY_offset()));
	}
	
	public void render(Graphics g, int x, int y) {
		// renders the item.
		g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
	}

	// creates a new Item at a position and returns the item.
	public Item createNew(int x, int y) {
		Item i = new Item(texture,name,ID);
		i.setPosition(x, y);
		return i;
	}
	
	// Sets the position of the item and changes the bounding boxes.
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}
	
	// Getters and Setters
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return (int) x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public int getY() {
		return (int) y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getID() {
		return ID;
	}

	public boolean isPicked_up() {
		return picked_up;
	}

	public void setPicked_up(boolean picked_up) {
		this.picked_up = picked_up;
	}

}
