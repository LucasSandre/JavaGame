package dev.lucas.game.gfx;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;

	
	public static BufferedImage player_still, stone, grass, tree, boulder, dirt, wood, missing;
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
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/map.png"));
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
		
		
		
		
		// Animated Sprites
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
		
		player_down[0]  = sheet.crop(0, 0, width, height);
		player_down[1]  = sheet.crop(width, 0, width, height);
		player_up[0]    = sheet.crop(width*2, 0, width, height);
		player_up[1]    = sheet.crop(width*3, 0, width, height);
		player_right[0] = sheet.crop(width*4, 0, width, height);
		player_right[1] = sheet.crop(width*5, 0, width, height);
		player_left[0]  = sheet.crop(width*6, 0, width, height);
		player_left[1]  = sheet.crop(width*7, 0, width, height);
		
		start[0]        = sheet.crop(0,height*7,width*2,height);  
		start[1]        = sheet.crop(width*2,height*7,width*2,height);
		
		attack_up[0]    = attack_sheet.crop(width*7, 0, width, height);
		attack_up[1]    = attack_sheet.crop(width*6, 0, width, height);
		attack_up[2]    = attack_sheet.crop(width*5, 0, width, height);
		attack_up[3]    = attack_sheet.crop(width*4, 0, width, height);
		attack_up[4]    = attack_sheet.crop(width*3, 0, width, height);
		attack_up[5]    = attack_sheet.crop(width*2, 0, width, height);
		attack_up[6]    = attack_sheet.crop(width, 0, width, height);
		attack_up[7]    = attack_sheet.crop(0, 0, width, height);
		
		attack_down[0]  = attack_sheet.crop(width*7, height, width, height);
		attack_down[1]  = attack_sheet.crop(width*6, height, width, height);
		attack_down[2]  = attack_sheet.crop(width*5, height, width, height);
		attack_down[3]  = attack_sheet.crop(width*4, height, width, height);
		attack_down[4]  = attack_sheet.crop(width*3, height, width, height);
		attack_down[5]  = attack_sheet.crop(width*2, height, width, height);
		attack_down[6]  = attack_sheet.crop(width, height, width, height);
		attack_down[7]  = attack_sheet.crop(0, height, width, height);
		
		attack_right[0] = attack_sheet.crop(width*7, height*2, width, height);
		attack_right[1] = attack_sheet.crop(width*6, height*2, width, height);
		attack_right[2] = attack_sheet.crop(width*5, height*2, width, height);
		attack_right[3] = attack_sheet.crop(width*4, height*2, width, height);
		attack_right[4] = attack_sheet.crop(width*3, height*2, width, height);
		attack_right[5] = attack_sheet.crop(width*2, height*2, width, height);
		attack_right[6] = attack_sheet.crop(width, height*2, width, height);
		attack_right[7] = attack_sheet.crop(0, height*2, width, height);

		attack_left[0] = attack_sheet.crop(width*7, height*3, width, height);
		attack_left[1] = attack_sheet.crop(width*6, height*3, width, height);
		attack_left[2] = attack_sheet.crop(width*5, height*3, width, height);
		attack_left[3] = attack_sheet.crop(width*4, height*3, width, height);
		attack_left[4] = attack_sheet.crop(width*3, height*3, width, height);
		attack_left[5] = attack_sheet.crop(width*2, height*3, width, height);
		attack_left[6] = attack_sheet.crop(width, height*3, width, height);
		attack_left[7] = attack_sheet.crop(0, height*3, width, height);

		zombie_down[0] = sheet.crop(width*5, height, width, height);
		zombie_down[1] = sheet.crop(width*6, height, width, height);
		zombie_down[2] = sheet.crop(width*7, height, width, height);
		
		zombie_left[0] = sheet.crop(width*5, height*2, width, height);
		zombie_left[1] = sheet.crop(width*6, height*2, width, height);
		zombie_left[2] = sheet.crop(width*7, height*2, width, height);
		
		zombie_right[0] = sheet.crop(width*5, height*3, width, height);
		zombie_right[1] = sheet.crop(width*6, height*3, width, height);
		zombie_right[2] = sheet.crop(width*7, height*3, width, height);
		
		zombie_up[0] = sheet.crop(width*5, height*4, width, height);
		zombie_up[1] = sheet.crop(width*6, height*4, width, height);
		zombie_up[2] = sheet.crop(width*7, height*4, width, height);
		
		// Still Sprites 
		stone        = sheet.crop(0, height, width, height);
		grass        = sheet.crop(width, height, width, height);
		tree         = sheet.crop(width*2, height, width, height);
		dirt         = sheet.crop(width*3, height, width, height);
		player_still = sheet.crop(width*4, height, width, height); 
		boulder      = sheet.crop(width*2, height*2, width, height);
		wood         = sheet.crop(width*4, height*7, width, height);

		
		// missing texture
		missing = sheet.crop(width*4, height*4, width, height);
	}
}
