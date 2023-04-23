package com.wellmax.world.tiles;

import com.wellmax.main.Game;
import com.wellmax.world.tiles.Scenario;

import java.awt.image.BufferedImage;

/**
 * Tree class
 * @author joao.gomes
 *
 */
public class Tree extends Scenario {
	/**
	 * The tree constructor initializes the collision mask values
	 * @param x x position
	 * @param y y position
	 * @param sprite Rock sprite
	 */
	public Tree(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);

		// Width and height
		this.setWidth(32);
		this.setHeight(48);
		
		// Collision mask
		this.setMaskX(6);
		this.setMaskY(86);
		this.setmWidth(52);
		this.setmHeight(10);

		this.shadowSprite = Game.spritesheet1.getSprite(192, 128, 72, 32);

		this.setShadowX(this.getX() - 4);
		this.setShadowY(this.getY() + 71);
	}
}
