package com.wellmax.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;

import com.wellmax.entities.*;
import com.wellmax.main.Game;
import com.wellmax.world.tiles.*;
import com.wellmax.world.types.PixelType;

/**
 * The World class initiates the map and all the scenario items
 * @author joao.gomes
 *
 */
public class World {
	
	/**
	 * Array with all tiles that will be rendered on screen
	 */
	public static Tile[] tiles;

	/**
	 * List with all rocks
	 */
	public static List<Rock> rocks;
	/**
	 * List with all rocks
	 */
	public static List<Tree> trees;
	
	/**
	 * Width and height of map
	 */
	public static int WIDTH, HEIGHT;
	
	/**
	 * Constant Tile size
	 */
	public static final int TILE_SIZE = 32;
	
	
	/**
	 * World constructor
	 * @param path Path to map image
	 */
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			
			rocks = new ArrayList<>();
			trees = new ArrayList<>();

			
			
			int[] pixels = new int[WIDTH * HEIGHT];
			tiles = new Tile[WIDTH * HEIGHT];
			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);



			// Sweeps map
			for(int xx = 0; xx < WIDTH; xx++) {
				for(int yy = 0; yy < HEIGHT; yy++) {
					int currentPixel = pixels[xx + (yy*WIDTH)];
					PixelType pixelType = PixelType.fromColor(currentPixel);

					tiles[xx + (yy*WIDTH)] = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_GRASS[1][1]);

					if(pixelType != null) {
						switch (pixelType) {
							case WALL_FACING_DOWN ->
									tiles[xx + (yy * WIDTH)] = new WallTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_WALL[2][1]);
							case WALL_FACING_RIGHT ->
									tiles[xx + (yy * WIDTH)] = new WallTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_WALL[1][2]);
							case WALL_MIDDLE ->
									tiles[xx + (yy * WIDTH)] = new WallTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_WALL[1][1]);
							case WALL_FACING_LEFT ->
									tiles[xx + (yy * WIDTH)] = new WallTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_WALL[1][0]);
							case WALL_FACING_UP ->
									tiles[xx + (yy * WIDTH)] = new WallTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_WALL[0][1]);
							case WALL_CORNER_DOWN_LEFT ->
									tiles[xx + (yy * WIDTH)] = new WallTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_WALL[2][0]);
							case WALL_CORNER_DOWN_RIGHT ->
									tiles[xx + (yy * WIDTH)] = new WallTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_WALL[2][2]);
							case WALL_CORNER_TOP_RIGHT ->
									tiles[xx + (yy * WIDTH)] = new WallTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_WALL[0][2]);
							case WALL_CORNER_TOP_LEFT ->
									tiles[xx + (yy * WIDTH)] = new WallTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_WALL[0][0]);

							case STONE_FLOOR_MIDDLE ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_STONE_FLOOR[1][1]);
							case STONE_FLOOR_FACING_UP ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_STONE_FLOOR[0][1]);
							case STONE_FLOOR_FACING_RIGHT ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_STONE_FLOOR[1][2]);
							case STONE_FLOOR_FACING_DOWN ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_STONE_FLOOR[2][1]);
							case STONE_FLOOR_FACING_LEFT ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_STONE_FLOOR[1][0]);
							case STONE_FLOOR_CORNER_TOP_LEFT ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_STONE_FLOOR[0][0]);
							case STONE_FLOOR_CORNER_TOP_RIGHT ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_STONE_FLOOR[0][2]);
							case STONE_FLOOR_CORNER_DOWN_LEFT ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_STONE_FLOOR[2][0]);
							case STONE_FLOOR_CORNER_DOWN_RIGHT ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_STONE_FLOOR[2][2]);

							case PATH_MIDDLE ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_PATH[1][1]);
							case PATH_FACING_UP ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_PATH[0][1]);
							case PATH_FACING_DOWN ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_PATH[2][1]);
							case PATH_FACING_LEFT ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_PATH[1][0]);
							case PATH_FACING_RIGHT ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_PATH[1][2]);
							case PATH_CORNER_TOP_RIGHT ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_PATH[0][2]);
							case PATH_CORNER_TOP_LEFT ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_PATH[0][0]);
							case PATH_CORNER_DOWN_RIGHT ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_PATH[2][2]);
							case PATH_CORNER_DOWN_LEFT ->
									tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_PATH[2][0]);

							case ROCK -> {
								Rock rock = new Rock(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_ROCK);
								Game.scenario.add(rock);
								World.rocks.add(rock);
							}
							case TREE -> {
								Tree tree = new Tree(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_TREE);
								Game.scenario.add(tree);
								World.trees.add(tree);
							}
							case STATUE -> {
								tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_STONE_FLOOR[1][1]);
								Statue statue = new Statue(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_STATUE);
								Game.scenario.add(statue);
							}

							case HEALTH_POTION -> {
								HealthPotion healthPotion = new HealthPotion(xx * TILE_SIZE, yy * TILE_SIZE, TILE_SIZE, TILE_SIZE);
								healthPotion.setMask(4, 0, 8, 16);
								Game.collectibles.add(healthPotion);
							}
							case FIREBALL -> {
								Fireball fireball = new Fireball(xx * TILE_SIZE, yy * TILE_SIZE, TILE_SIZE, TILE_SIZE);
								fireball.setMask(8, 0, 16, 32);
								Game.collectibles.add(fireball);
							}
							case ENEMY -> {
								Orc en = new Orc(xx * TILE_SIZE, yy * TILE_SIZE, TILE_SIZE, TILE_SIZE);
								Game.enemies.add(en);
							}
							case PLAYER -> {
								Game.player.setX(xx * TILE_SIZE);
								Game.player.setY(yy * TILE_SIZE);
							}

							default -> tiles[xx + (yy * WIDTH)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.TILE_GRASS[1][1]);
						}
					}
				}
			}
									
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to check if the next player position isn't a wall
	 * @param x Next player x position
	 * @param y Next player y position
	 * @return true if player won't collide with any wall
	 */
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

		int xStart = Camera.x >> 5;
		int yStart = Camera.y >> 5;
		
		int xFinal = xStart + (Game.WIDTH >> 5);
		int yFinal = yStart + (Game.HEIGHT >> 5);
				
		for(int xx = xStart; xx <= xFinal; xx++) {
			for(int yy = yStart; yy <= yFinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile underFloor = new FloorTile(xx*TILE_SIZE, yy*TILE_SIZE, Tile.TILE_GRASS[1][1]);
				Tile tile = tiles[xx + (yy*WIDTH)];
				underFloor.render(g);
				tile.render(g);
			}
		}
	}
	
	
	
}
