package dev.lucas.game.gfx;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;

	
	public static BufferedImage player_still, stone, grass, tree, boulder, dirt, wood, gravel, 
	                            grass_noob, grass_easy, grass_med, grass_diff, grass_hard,
	                            brick_crack_1, brick_crack_2, brick_crack_3, brick_grate,
	                            brick_clean, brick_batt_side, brick_bottom_right, brick_bottom_left, 
								brick_top_right1, brick_top_right2, brick_top_right3, brick_top_right4,
	                            brick_top_right5, brick_top_right6, brick_top_left1, brick_top_left2, 
	                            brick_top_left3, brick_top_left4, brick_top_left5, brick_top_left6,
	                            brick_batt_up1, brick_batt_up2, brick_batt_up3, brick_batt_up4,
	                            brick_batt_up5, brick_batt_up6;
	
	public static BufferedImage[] player_down, player_up, player_right, player_left;
	public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
	public static BufferedImage[] start;
	public static BufferedImage[] attack_up, attack_down, attack_right, attack_left;
	public static Font[] font_10, font_11, font_12, font_14,
	                   font_18, font_24, font_30, font_32;
	
	public static FontMetrics[] font_10_fm = new FontMetrics[3], font_11_fm, font_12_fm, font_14_fm,
	                            font_18_fm, font_24_fm, font_30_fm, font_32_fm;
	
	public static void init(Graphics g) {
		
		// Get Sheet files
		
		SpriteSheet entity_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/EntitySheet.png"));
		SpriteSheet static_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/StaticSheet.png"));
		SpriteSheet tile_sheet   = new SpriteSheet(ImageLoader.loadImage("/textures/TileSheet.png"));
		SpriteSheet ui_sheet     = new SpriteSheet(ImageLoader.loadImage("/textures/UISheet.png"));
		SpriteSheet item_sheet   = new SpriteSheet(ImageLoader.loadImage("/textures/ItemSheet.png"));
		SpriteSheet attack_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Attackmap.png"));
		
		// Get Fonts
		
		font_10 = FontLoader.loadFont("/fonts/rune.ttf",10);
		font_11 = FontLoader.loadFont("/fonts/rune.ttf",11);
		font_12 = FontLoader.loadFont("/fonts/rune.ttf",12);
		font_14 = FontLoader.loadFont("/fonts/rune.ttf",14);
		font_18 = FontLoader.loadFont("/fonts/rune.ttf",18);
		font_24 = FontLoader.loadFont("/fonts/rune.ttf",24);
		font_30 = FontLoader.loadFont("/fonts/rune.ttf",30);
		font_32 = FontLoader.loadFont("/fonts/rune.ttf",32);
		
		// Getting Font Metrics		
		font_10_fm = FontLoader.loadFontMetrics(g, font_10);
		font_11_fm = FontLoader.loadFontMetrics(g, font_11);
		font_12_fm = FontLoader.loadFontMetrics(g, font_12);
		font_14_fm = FontLoader.loadFontMetrics(g, font_14);
		font_18_fm = FontLoader.loadFontMetrics(g, font_18);
		font_24_fm = FontLoader.loadFontMetrics(g, font_24);
		font_30_fm = FontLoader.loadFontMetrics(g, font_30);
		font_32_fm = FontLoader.loadFontMetrics(g, font_32);
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_right = new BufferedImage[2];
		player_left = new BufferedImage[2];
		
		start = new BufferedImage[2];
		
		attack_up = new BufferedImage[8];
		attack_down = new BufferedImage[8];
		attack_left = new BufferedImage[8];
		attack_right = new BufferedImage[8];
		
		zombie_down = new BufferedImage[3];
		zombie_left = new BufferedImage[3];
		zombie_right = new BufferedImage[3];
		zombie_up = new BufferedImage[3];
		
		// For loops for animations
		
		for(int i = 0; i < 2; i++) {
			player_down[i] = entity_sheet.crop(width*i, 0, width, height);
			player_up[i]   = entity_sheet.crop(width*i, height, width, height);
			player_left[i] = entity_sheet.crop(width*i, height*2, width, height);
			player_right[i]= entity_sheet.crop(width*i, height*3, width, height);
		}
		
		for (int i = 7,index = 0; i > -1; i--) {
			attack_up[index] = attack_sheet.crop(width*i, 0, width, height);
			attack_down[index]  = attack_sheet.crop(width*i, height, width, height);
			attack_right[index] = attack_sheet.crop(width*i, height*2, width, height);
			attack_left[index] = attack_sheet.crop(width*i, height*3, width, height);
			index++;
		}
		
		
		start[0]        = ui_sheet.crop(0,0,width*2,height);  
		start[1]        = ui_sheet.crop(width*2,0,width*2,height);
		
		// Tiles 
		stone        = tile_sheet.crop(0, 0, width, height);
		dirt         = tile_sheet.crop(width, 0, width, height);
		grass        = tile_sheet.crop(width*2, 0, width, height);
		grass_noob 	 = tile_sheet.crop(width*3, 0, width, height); 
		grass_easy   = tile_sheet.crop(width*4, 0, width, height);
		grass_med    = tile_sheet.crop(width*5, 0, width, height);
		grass_diff   = tile_sheet.crop(width*6, 0, width, height);
		grass_hard   = tile_sheet.crop(width*7, 0, width, height);
		gravel       = tile_sheet.crop(width*8, 0, width, height);
		
		brick_crack_1= tile_sheet.crop(0, height, width, height);
		brick_crack_2= tile_sheet.crop(width, height, width, height);
		brick_grate  = tile_sheet.crop(0, height*2, width, height);
		brick_crack_3= tile_sheet.crop(width, height*2, width, height);
		brick_clean  = tile_sheet.crop(0, height*3, width, height);
		
		brick_batt_side = tile_sheet.crop(width, height*3, width, height);
		brick_bottom_right = tile_sheet.crop(0, height*4, width, height);
		brick_bottom_left = tile_sheet.crop(width, height*4, width, height);
		
		brick_top_right1 = tile_sheet.crop(width*2, height, width, height);
		brick_top_right2 = tile_sheet.crop(width*3, height, width, height);
		brick_top_right3 = tile_sheet.crop(width*4, height, width, height);
		brick_top_right4 = tile_sheet.crop(width*5, height, width, height);
		brick_top_right5 = tile_sheet.crop(width*6, height, width, height);
		brick_top_right6 = tile_sheet.crop(width*7, height, width, height);
		
		brick_top_left1 = tile_sheet.crop(width*2, height*2, width, height);
		brick_top_left2 = tile_sheet.crop(width*3, height*2, width, height);
		brick_top_left3 = tile_sheet.crop(width*4, height*2, width, height);
		brick_top_left4 = tile_sheet.crop(width*5, height*2, width, height);
		brick_top_left5 = tile_sheet.crop(width*6, height*2, width, height);
		brick_top_left6 = tile_sheet.crop(width*7, height*2, width, height);
		
		brick_batt_up1 = tile_sheet.crop(width*2, height*3, width, height);
		brick_batt_up2 = tile_sheet.crop(width*3, height*3, width, height);
		brick_batt_up3 = tile_sheet.crop(width*4, height*3, width, height);
		brick_batt_up4 = tile_sheet.crop(width*5, height*3, width, height);
		brick_batt_up5 = tile_sheet.crop(width*6, height*3, width, height);
		brick_batt_up6 = tile_sheet.crop(width*7, height*3, width, height);
		
		// Static Sprites
		tree         = static_sheet.crop(0, 0, width, height);
		boulder      = static_sheet.crop(0, height, width, height);
		
		player_still = entity_sheet.crop(width*2, 0, width, height); 
		
		wood         = item_sheet.crop(0, 0, width, height);

		
		// missing texture
		//missing = sheet.crop(width*4, height*4, width, height);
	}
}
