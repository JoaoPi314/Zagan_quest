package com.melqjpgames.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.melqjpgames.entities.*;
import com.melqjpgames.main.Game;

public class World {
	
	
	public static Tile[] tiles;
	public static Tile statue;
	
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;
	
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			
			
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			int[] pixels = new int[WIDTH * HEIGHT];
			tiles = new Tile[WIDTH * HEIGHT];
			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
			
			int xStatue= 0, yStatue = 0; 
			
			// Sweeps map
			for(int xx = 0; xx < WIDTH; xx++) {
				for(int yy = 0; yy < HEIGHT; yy++) {
					int currentPixel =pixels[xx + (yy*WIDTH)]; 
					
					tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_FLOOR_1);

					
					if(currentPixel == 0xFF000000) {
						// floor
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_FLOOR_1);
					}else if(currentPixel == 0xFF555555) {
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_FLOOR_2);
					}else if(currentPixel == 0xFF1D1D1D) {
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_FLOOR_3);
					}else if(currentPixel == 0xFFFFFFFF) {
						// Wall
						tiles[xx + (yy*WIDTH)] = new WallTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_WALL);
					}else if(currentPixel == 0xFF0000FF) {
						// Player
						Game.player.setX(xx*TILE_SIZE);
						Game.player.setY(yy*TILE_SIZE);
					}else if(currentPixel == 0xFFFF0000) {
						// Enemy
						Enemy en = new Enemy(xx*TILE_SIZE, yy*TILE_SIZE, TILE_SIZE, TILE_SIZE);
						Game.entities.add(en);
						Game.enemies.add(en);
					}else if(currentPixel == 0xFFFF8200) {
						// Fireball
						Fireball fireball = new Fireball(xx*TILE_SIZE, yy*TILE_SIZE, TILE_SIZE, TILE_SIZE);
						fireball.setMask(4, 0, 8, 16);
						Game.entities.add(fireball);
					}else if(currentPixel == 0xFF14FF00) {
						// Health potion
						HealthPotion healthPotion = new HealthPotion(xx*TILE_SIZE, yy*TILE_SIZE, TILE_SIZE, TILE_SIZE);
						healthPotion.setMask(4, 0, 8, 16);
						Game.entities.add(healthPotion);
					}else if(currentPixel == 0xFF6F009F) {
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_RITUAL_FLOOR);
					}else if(currentPixel == 0xFFEE03FF) {
						xStatue = xx;
						yStatue = yy;
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_RITUAL_FLOOR);
					}else if(currentPixel == 0xFF6C0073) {
						tiles[xx + (yy*WIDTH)] = new WallTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_RITUAL_FLOOR);
					}
				}
			}
			
			statue = new WallTile(xStatue*TILE_SIZE, yStatue*TILE_SIZE, Tile.TILE_RITUAL_STATUE);
						
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int x, int y) {
		// Converts position to tiles
		int x1 = x / TILE_SIZE;
		int y1 = y / TILE_SIZE;
		
		int x2 = (x + TILE_SIZE - 1) / TILE_SIZE;
		int y2 = y / TILE_SIZE;
		
		int x3 = x  / TILE_SIZE;
		int y3 = (y + TILE_SIZE - 1) / TILE_SIZE;
		
		int x4 = (x + TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (y + TILE_SIZE - 1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				 (tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				 (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				 (tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
		
	}
	
	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}		
		statue.render(g);
	}
	
	
	
}
