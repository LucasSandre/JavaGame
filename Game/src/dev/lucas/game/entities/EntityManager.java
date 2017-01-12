package dev.lucas.game.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import dev.lucas.game.Handler;
import dev.lucas.game.entities.creatures.Player;

public class EntityManager {

	// Creates private variables and arrays 
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private ArrayList<Entity> projectiles;
	
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
	
	// Entity Manager constructor needs a handler and a player object. stores the handler and player, then creates the arrayLists for entities and projectile_entities. And adds the player to the entites list.
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		projectiles = new ArrayList<Entity>();
		addEntity(player);
		
	}
	
	// Updates all the the entities in the world, checks if the entity is still 'active', and if not it removes it. then it resorts the ArrayList. the same for projectiles.
	public void tick() {
		Iterator<Entity> ite = entities.iterator();
		while(ite.hasNext()) {
			Entity e = ite.next();
			e.tick();
			if (!e.isActive()){
				ite.remove();
			}
		}
		entities.sort(render_sorter);
		Iterator<Entity> itp = projectiles.iterator();
		while (itp.hasNext()) {
			Entity p = itp.next();
			p.tick();
			if (!p.isActive()) {
				itp.remove();
			}
		}
		projectiles.sort(render_sorter);
	}

	// Calles the render method for every entity in the entities and projective ArrayLists.
	public void render(Graphics g) {
		for (Entity e : entities){
			e.render(g);
		}
		for (Entity p : projectiles) {
			p.render(g);
		}
	}

	// Adders for each arrayList
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void addProjectile(Entity p) {
		projectiles.add(p);
	}
	
	// Getters and Setters
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

	public ArrayList<Entity> getProjectiles() {
		return projectiles;
	}

	public void setProjectiles(ArrayList<Entity> projectiles) {
		this.projectiles = projectiles;
	}
}
