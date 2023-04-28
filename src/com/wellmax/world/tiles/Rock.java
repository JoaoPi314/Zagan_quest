package com.wellmax.world.tiles;

import com.wellmax.main.Game;

import java.awt.image.BufferedImage;

/**
 * Rock class
 * @author joao.gomes
 *
 */
public class Rock extends Scenario {
	/**
	 * The rock constructor initializes the collision mask values
	 * @param x x position
	 * @param y y position
	 * @param sprite Rock sprite
	 */
	public Rock(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);

		// Width and height
		this.setWidth(16);
		this.setHeight(16);
		
		// Collision mask
		this.setMaskX(1);
		this.setMaskY(9);
		this.setmWidth(30);
		this.setmHeight(22);

		this.shadowSprite = Game.spritesheet1.getSprite(192, 96, 42, 21);

		this.setShadowX(this.getX() - 6);
		this.setShadowY(this.getY() + 12);
	}
}
