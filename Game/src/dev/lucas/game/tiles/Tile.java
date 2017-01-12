package dev.lucas.game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.lucas.game.tiles.brickwall.*;
import dev.lucas.game.tiles.grass.*;

public class Tile {
	
	// Static Variables here, creates Tile variables. tiles array holds every type of tile.
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grass_tile = new GrassTile(0);
	public static Tile grass_noob_tile = new GrassNoobTile(1);
	public static Tile grass_easy_tile = new GrassEasyTile(2);
	public static Tile grass_med_tile = new GrassMedTile(3);
	public static Tile grass_diff_tile = new GrassDiffTile(4);
	public static Tile grass_hard_tile = new GrassHardTile(5);
	public static Tile grass_s2n_tile = new GrassSafeToNoobTile(6);
	public static Tile grass_n2e_tile = new GrassNoobToEasyTile(7);
	public static Tile grass_e2m_tile = new GrassEasyToMedTile(8);
	public static Tile grass_m2d_tile = new GrassMedToDiffTile(9);
	public static Tile grass_d2h_tile = new GrassDiffToHardTile(10);
	public static Tile grass_h2d_tile = new GrassHardToDiffTile(11);
	public static Tile grass_d2m_tile = new GrassDiffToMedTile(12);
	public static Tile grass_m2e_tile = new GrassMedToEasyTile(13);
	public static Tile grass_e2n_tile = new GrassEasyToNoobTile(14);
	public static Tile grass_n2s_tile = new GrassNoobToSafeTile(15);
	
	public static Tile brick_batt_up6_tile = new BrickWallBattUpSixTile(16);
	public static Tile brick_batt_up5_tile = new BrickWallBattUpFiveTile(17);
	public static Tile brick_batt_up4_tile = new BrickWallBattUpFourTile(18);
	public static Tile brick_batt_up3_tile = new BrickWallBattUpThreeTile(19);
	public static Tile brick_batt_up2_tile = new BrickWallBattUpTwoTile(20);
	public static Tile brick_batt_up1_tile = new BrickWallBattUpOneTile(21);
	public static Tile brick_batt_side_tile = new BrickWallBattSideTile(22);
	
	public static Tile brick_top_left6_tile = new BrickWallTopLeftSixTile(23);
	public static Tile brick_top_left5_tile = new BrickWallTopLeftFiveTile(24);
	public static Tile brick_top_left4_tile = new BrickWallTopLeftFourTile(25);
	public static Tile brick_top_left3_tile = new BrickWallTopLeftThreeTile(26);
	public static Tile brick_top_left2_tile = new BrickWallTopLeftTwoTile(27);
	public static Tile brick_top_left1_tile = new BrickWallTopLeftOneTile(28);
	public static Tile brick_top_right6_tile = new BrickWallTopRightSixTile(29);
	public static Tile brick_top_right5_tile = new BrickWallTopRightFiveTile(30);
	public static Tile brick_top_right4_tile = new BrickWallTopRightFourTile(31);
	public static Tile brick_top_right3_tile = new BrickWallTopRightThreeTile(32);
	public static Tile brick_top_right2_tile = new BrickWallTopRightTwoTile(33);
	public static Tile brick_top_right1_tile = new BrickWallTopRightOneTile(34);
	
	public static Tile brick_bottom_left_tile = new BrickWallBottomLeftTile(35);
	public static Tile brick_bottom_right_tile = new BrickWallBottomRightTile(36);
	
	public static Tile brick_clean_tile = new BrickWallCleanTile(37);
	public static Tile brick_crack1_tile = new BrickWallCrackOneTile(38);
	public static Tile brick_crack2_tile = new BrickWallCrackTwoTile(39);
	public static Tile brick_crack3_tile = new BrickWallCrackThreeTile(40);
	public static Tile brick_grate_tile = new BrickWallGrateTile(41);
	
	public static Tile dirt_tile = new DirtTile(100);
	public static Tile rock_tile = new RockTile(101);
	public static Tile edge_tile = new EdgeTile(254);
	public static Tile missing_texture_tile = new MissingTile(255);
	
	
	// Class
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

	protected BufferedImage texture;
	protected final int id;
	
	// Tile Constructor takes in an image and an int. After it stores the values and adds the new tile to the tiles array @ index 'id'
	public Tile(BufferedImage texture,int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}

	// NOt implemented but animated and intractable tiles could be included.
	public void tick () {
		
	}
	
	// Draws the tile with its texture
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
		
	}
	
	// Getters
	public int getId() {
		return id;
	}
	
	public boolean isSolid() {
		return false;
	}
}
