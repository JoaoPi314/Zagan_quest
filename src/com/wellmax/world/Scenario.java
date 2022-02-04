package com.wellmax.world;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wellmax.entities.Enemy;
import com.wellmax.main.Game;

/**
 * Scenario items have a different behgavior when colliding with 
 * players. They have a configurable collision mask
 * @author joao.gomes
 *
 */
public class Scenario extends Tile{

	//---------------------------- Attributes ----------------------------------//	
	/**
	 * x position of collision mask (offset based on x attribute)
	 */
	protected int maskX;
	/**
	 * y position of collision mask(offset based on y attribute)
	 */
	protected int maskY;
	/**
	 * Width of collision mask. Based on (maskX, maskY) point
	 */
	protected int mWidth;
	/**
	 * Height of collision mask. Based on (maskX, maskY) point
	 */
	protected int mHeight;

	//---------------------------- Methods ----------------------------------//	
	
	/**
	 * Scenario constructor. It receives also the width and height
	 * @param x x position
	 * @param y y position
	 * @param sprite Scenario item sprite
	 * @param width Sprite width 
	 * @param height Sprite height
	 */
	public Scenario(int x, int y, BufferedImage sprite, int width, int height) {
		super(x, y, sprite);
		this.setWidth(width);
		this.setHeight(height);
	}
	
	/**
	 * @return the maskX
	 */
	public int getMaskX() {
		return maskX;
	}
	
	/**
	 * @param maskX the maskX to set
	 */
	public void setMaskX(int maskX) {
		this.maskX = maskX;
	}
	
	/**
	 * @return the maskY
	 */
	public int getMaskY() {
		return maskY;
	}
	
	/**
	 * @param maskY the maskY to set
	 */
	public void setMaskY(int maskY) {
		this.maskY = maskY;
	}
	
	/**
	 * @return the mWidth
	 */
	public int getmWidth() {
		return mWidth;
	}
	
	/**
	 * @param mWidth the mWidth to set
	 */
	public void setmWidth(int mWidth) {
		this.mWidth = mWidth;
	}
	
	/**
	 * @return the mHeight
	 */
	public int getmHeight() {
		return mHeight;
	}
	
	/**
	 * @param mHeight the mHeight to set
	 */
	public void setmHeight(int mHeight) {
		this.mHeight = mHeight;
	}
	
	
	/**
	 * Checks collision with scenario items
	 * @param xx next player x position
	 * @param yy next player y position
	 * @return true if player collides with any scenario item
	 */
	public boolean isCollisionWithEntity(int xx, int yy) {
		Rectangle currentItem = new Rectangle(this.getX() + this.getMaskX(), this.getY() + this.getMaskY(), this.getmWidth(), this.getmHeight());
		
		for(int i = 0; i < Game.scenario.size(); i++) {
			Scenario s = Game.scenario.get(i);
			if(s == this) {
				continue;
			}
			Rectangle player = new Rectangle(xx + Game.player.getMaskX(), yy +  Game.player.getMaskY(), Game.player.getmWidth(), Game.player.getmHeight());
			if(currentItem.intersects(player)){
				return true;
			}
		}

		return false;
	}
	
	
}
