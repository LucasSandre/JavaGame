package dev.lucas.game.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import dev.lucas.game.Handler;
import dev.lucas.game.entities.creatures.Player;

/** 
 * <i><b>EntityManager</b></i>
 * <pre>	public class EntityManager</pre>
 * <p>This class manages all the Entities of the game.</p>
 * @see {@link dev.lucas.game.Entity Entity}
 * **/
public class EntityManager {

	// Creates private variables and arrays 
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private ArrayList<Entity> waitlist;
	
	// Creates a comparator to render entities in the correct order.
	private Comparator<Entity> render_sorter = new Comparator<Entity>() {

		@Override
		public int compare(Entity a, Entity b) {
			if (a.getY() + a.getHeight() < b.getY()+ b.getHeight()){
				return -1;
			}
			else {
				return 1;
			}
		}
		
	};
	
	// Entity Manager constructor needs a handler and a player object. stores the handler and player, then creates the 
	//arrayLists for entities and projectile_entities. And adds the player to the entites list.
	/** 
	 * <i><b>EntityManager</b></i>
	 * <pre>	public EntityManager(Handler handler,
	 *                              Player player)</pre>
	 * <p>The EntityManager constructor takes in a Handler object and a Player object and it saves these two objects in variables. Then it creates two ArrayLists for Entities.
	 *  Finally it adds the Player to the 'entities' ArrayList</p>
	 * @param Handler handler,
	 * @param PLayer player
	 * @return None
	 * @see {@link dev.lucas.game.Handler Handler} , {@link dev.lucas.game.entites.Entity Entity} , {@link dev.lucas.game.entities.creatures.Player Player}
	 * **/
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		waitlist = new ArrayList<Entity>();
		addEntity(player);
		
	}
	
	// Updates all the the entities in the world, checks if the entity is still 'active', and if not it removes it. then it resorts the ArrayList.
	/** 
	 * <i><b>Tick</b></i>
	 * <pre>	public void tick()</pre>
	 * <p>This method updates all Entities and in the world, checks if the Entity or Projectile is still 'active' and if not it removes it. After it resorts the ArrayLists.</p>
	 * @param None
	 * @return None
	 * @see {@link dev.lucas.game.entities Entity} , {@link dev.lucas.gamme.entities.projectile.Projectile Projectile}
	 * **/
	public void tick() {
		ArrayList<Entity> entities_clone = (ArrayList<Entity>) entities.clone();
		Iterator<Entity> it = entities_clone.iterator();
		while(it.hasNext()) {
			Entity e = it.next();
			e.tick();
			if (!e.isActive()){
				it.remove();
			}
		}
		entities = (ArrayList<Entity>) entities_clone.clone();
		Iterator<Entity> add_it = waitlist.iterator();
		while (add_it.hasNext()) {
			entities.add(add_it.next());
		}
		waitlist.clear();
		entities.sort(render_sorter);
	}

	/** 
	 * <i><b>Render</b></i>
	 * <pre>	public void render(Graphics g)</pre>
	 * <p>This method renders all Entities.</p>
	 * @param
	 * @return None
	 * @see {@link dev.lucas.game.entities Entity} , {@link dev.lucas.gamme.entities.projectile.Projectile Projectile}
	 * **/
	public void render(Graphics g) {
		for (Entity e : entities){
			e.render(g);
		}
	}

	// Adders for each arrayList
	/** 
	 * <i><b>AddEntity</b></i>
	 * <pre>	public void addEntity(Entity e)</pre>
	 * <p>An adder method for the 'entities' ArrayList.</p>
	 * @param
	 * @return None
	 * @see {@link dev.lucas.game.entities Entity}
	 * **/
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void addEntityWaitlist(Entity e) {
		waitlist.add(e);
	}
	
	// Getters and Setters
	
	/** 
	 * <i><b>getHandler</b></i>
	 * <pre>	public Handler getHandler()</pre>
	 * <p>Gets the Handler object.</p>
	 * @param None
	 * @return Handler
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public Handler getHandler() {
		return handler;
	}

	/** 
	 * <i><b>SetHandler</b></i>
	 * <pre>	public Handler setHandler(Handler handler)</pre>
	 * <p>Sets the Handler object.</p>
	 * @param None
	 * @return Handler
	 * @see {@link dev.lucas.game.Handler Handler}
	 * **/
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	/** 
	 * <i><b>GetPlayer</b></i>
	 * <pre>	public Player getPlayer()</pre>
	 * <p>Gets the Player object.</p>
	 * @param None
	 * @return Player
	 * @see {@link dev.lucas.game.entities.creatures.Player Player}
	 * **/
	public Player getPlayer() {
		return player;
	}

	/** 
	 * <i><b>SetPlayer</b></i>
	 * <pre>	public Player setPlayer()</pre>
	 * <p>Sets the Player object.</p>
	 * @param None
	 * @return Player
	 * @see {@link dev.lucas.game.entities.creatures.Player Player}
	 * **/
	public void setPlayer(Player player) {
		this.player = player;
	}

	/** 
	 * <i><b>GetEntities</b></i>
	 * <pre>	public ArrayList(Entity) getEntities()</pre>
	 * <p>Gets the Entities ArrayList.</p>
	 * @param None
	 * @return ArrayList(Entity)
	 * @see {@link dev.lucas.game.entities.Entity Entity}
	 * **/
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	/** 
	 * <i><b>SetEntities</b></i>
	 * <pre>	public void setEntities(ArrayList(Entity) entities)</pre>
	 * <p>Sets the entities Array to the value parsed into the method.</p>
	 * @param
	 * @return None
	 * @see {@link dev.lucas.game.entities.Entity Entity}
	 * **/
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
