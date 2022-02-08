package com.wellmax.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.Scenario;

/**
 * Generic Entity class. The Entity, projectile and Collectible classes
 * will inheritate this class
 * @author joao.gomes
 *
 */
public abstract class GenericEntity {

	//---------------------------- Attributes ----------------------------------//
	
	/**
	 * x position of Entity
	 */
	protected double x;
	/**
	 * y position of Entity
	 */
	protected double y;
	/**
	 * Width of Entity
	 */
	protected int width;
	/**
	 * Height of entity
	 */
	protected int height;
	
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
	
	/**
	 * Number of sprites of Entity (Used when entity has animation)
	 */
	protected int nOfSprites;
	/**
	 * Frames of actual sprite (Animation calculus)
	 */
	protected int frames;
	/**
	 * Max number of frames to change sprite (Animation calculus)
	 */
	protected int maxFrames;
	/**
	 * Index of sprite array (Animation calculus)
	 */
	protected int index;
	
	//---------------------------- Methods ----------------------------------//	
	


	/**
	 * The constructor takes the fundamental parameters of an Entity
	 * @param x: x position
	 * @param y: y position
	 * @param width: Entity width
	 * @param height: Entity heighy
	 */
	public GenericEntity(int x, int y, int width, int height) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		
		this.setmWidth(width);
		this.setmHeight(height);
		
		// Other initializations
		this.maxFrames = 10;
		this.frames = 0;
		this.index = 0;
		this.setnOfSprites(4);
		
	}
	
	
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}


	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}


	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}


	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}


	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}


	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}


	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}


	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
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
	 * @return the nOfSprites
	 */
	public int getnOfSprites() {
		return nOfSprites;
	}


	/**
	 * @param nOfSprites the nOfSprites to set
	 */
	public void setnOfSprites(int nOfSprites) {
		this.nOfSprites = nOfSprites;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}


	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Static method to check collision between two entities
	 * @param e1 Generic entity 1
	 * @param e2 Generic entity 2
	 * @return true if e1 and e2 are colliding
	 */
	public static boolean isColliding(GenericEntity e1, GenericEntity e2) {
		Rectangle e1Mask = new Rectangle((int)(e1.getX() + e1.getMaskX()), (int)(e1.getY() + e1.getMaskY()), e1.getmWidth(), e1.getmHeight());
		Rectangle e2Mask = new Rectangle((int)(e2.getX() + e2.getMaskX()), (int)(e2.getY() + e2.getMaskY()), e2.getmWidth(), e2.getmHeight());
		
		return e1Mask.intersects(e2Mask);
	}	
	
	/**
	 * Checks collision with scenario items
	 * @param xx next player x position
	 * @param yy next player y position
	 * @return true if player collides with any scenario item
	 */
	protected boolean isCollidingWithScenario(int xx, int yy) {
		Rectangle entity = new Rectangle((int)(xx + this.getMaskX()), 
				(int)(yy + this.getMaskY()), this.getmWidth(), this.getmHeight());
		
		for(int i = 0; i < Game.scenario.size(); i++) {
			Scenario s = Game.scenario.get(i);
			
			Rectangle scenarioItem = new Rectangle(s.getX() + s.getMaskX(), 
					s.getY() +  s.getMaskY(), s.getmWidth(), s.getmHeight());
			if(scenarioItem.intersects(entity)){
				return true;
			}
		}

		return false;
	}
	
	
	/**
	 * Method to set all collision mask attributes at once
	 * @param maskX x mask position
	 * @param maskY y mask position
	 * @param mWidth mask width
	 * @param mHeight mask height
	 */
	public void setMask(int maskX, int maskY, int mWidth, int mHeight) {
		this.setMaskX(maskX);
		this.setMaskY(maskY);
		this.setmWidth(mWidth);
		this.setmHeight(mHeight);
	}
	
	/**
	 * Method to count the frames necessary to update Entity sprite
	 * @param count Flag that indicates if entity sprite must be updated
	 */
	protected void countFrames(boolean count) {
		if(count) {
			this.frames++;
			if(this.frames == this.maxFrames) {
				this.frames = 0;
				this.index++;
				if(this.index >= this.getnOfSprites()) {
					this.index = 0;
				}
			}
		}
	}
	
	/**
	 * Abstract method to evaluate entity logic at each frame
	 */
	public abstract void update();
	/**
	 * Abstract method to render entity at each frame
	 * @param g Graphic object to render entity
	 */
	public abstract void render(Graphics g);
	

}
