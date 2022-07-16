package com.wellmax.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.wellmax.main.Game;
import com.wellmax.world.tiles.Scenario;

/**
 * Generic Creature class. The Creature, projectile and Collectible classes
 * will inheritance this class
 * @author joao.gomes
 *
 */
public abstract class Entity {
	//---------------------------- Attributes ----------------------------------//
	
	/**
	 * x position of Creature
	 */
	private double x;
	/**
	 * y position of Creature
	 */
	private double y;
	/**
	 * Width of Creature
	 */
	private int width;
	/**
	 * Height of entity
	 */
	private int height;
	/**
	 * x position of collision mask (offset based on x attribute)
	 */
	private int maskX;
	/**
	 * y position of collision mask(offset based on y attribute)
	 */
	private int maskY;
	/**
	 * Width of collision mask. Based on (maskX, maskY) point
	 */
	private int maskWidth;
	/**
	 * Height of collision mask. Based on (maskX, maskY) point
	 */
	private int maskHeight;
	/**
	 * Number of sprites of Creature (Used when entity has animation)
	 */
	private int numberOfSprites;
	/**
	 * Current frames of animation
	 */
	private int frames;
	/**
	 * Max value of frames before sprite changes
	 */
	private int maxFrames;
	/**
	 * Index of sprite array (Animation calculus)
	 */
	private int index;
	
	//---------------------------- Methods ----------------------------------//
	/**
	 * The constructor takes the fundamental parameters of a Creature
	 * @param x: x position
	 * @param y: y position
	 * @param width: Creature width
	 * @param height: Creature height
	 */
	public Entity(int x, int y, int width, int height) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		
		this.setMaskWidth(width);
		this.setMaskHeight(height);
		
		// Other initializations
		this.setMaxFrames(6);
		this.setFrames(0);
		this.setIndex(0);
		this.setNumberOfSprites(6);
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMaskX() {
		return maskX;
	}

	public void setMaskX(int maskX) {
		this.maskX = maskX;
	}

	public int getMaskY() {
		return maskY;
	}

	public void setMaskY(int maskY) {
		this.maskY = maskY;
	}

	public int getMaskWidth() {
		return maskWidth;
	}

	public void setMaskWidth(int maskWidth) {
		this.maskWidth = maskWidth;
	}
	
	public int getMaskHeight() {
		return maskHeight;
	}

	public void setMaskHeight(int maskHeight) {
		this.maskHeight = maskHeight;
	}

	public int getNumberOfSprites() {
		return numberOfSprites;
	}

	public void setNumberOfSprites(int numberOfSprites) {
		this.numberOfSprites = numberOfSprites;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getFrames() {
		return frames;
	}

	public void setFrames(int frames) {
		this.frames = frames;
	}

	public int getMaxFrames() {
		return maxFrames;
	}

	public void setMaxFrames(int maxFrames) {
		this.maxFrames = maxFrames;
	}

	/**
	 * Static method to check collision between two entities
	 * @param e1 Generic entity 1
	 * @param e2 Generic entity 2
	 * @return true if e1 and e2 are colliding
	 */
	public static boolean isColliding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle((int)(e1.getX() + e1.getMaskX()), (int)(e1.getY() + e1.getMaskY()), e1.getMaskWidth(), e1.getMaskHeight());
		Rectangle e2Mask = new Rectangle((int)(e2.getX() + e2.getMaskX()), (int)(e2.getY() + e2.getMaskY()), e2.getMaskWidth(), e2.getMaskHeight());
		
		return e1Mask.intersects(e2Mask);
	}	
	
	/**
	 * Checks collision with scenario items
	 * @param xx next player x position
	 * @param yy next player y position
	 * @return true if player isn't colliding with any scenario item
	 */
	protected boolean isNotCollidingWithScenario(int xx, int yy) {
		Rectangle entity = new Rectangle((int)(xx + this.getMaskX()), 
				(int)(yy + this.getMaskY()), this.getMaskWidth(), this.getMaskHeight());
		
		for(int i = 0; i < Game.scenario.size(); i++) {
			Scenario s = Game.scenario.get(i);
			
			Rectangle scenarioItem = new Rectangle(s.getX() + s.getMaskX(), 
					s.getY() +  s.getMaskY(), s.getmWidth(), s.getmHeight());
			if(scenarioItem.intersects(entity)){
				return false;
			}
		}
		return true;
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
		this.setMaskWidth(mWidth);
		this.setMaskHeight(mHeight);
	}
	
	/**
	 * Method to count the frames necessary to update Creature sprite
	 * @param count Flag that indicates if entity sprite must be updated
	 */
	protected void countFrames(boolean count) {
		if(count) {
			this.setFrames(this.getFrames() + 1);
			if(this.getFrames() == this.getMaxFrames()) {
				this.setFrames(0);
				this.index++;
				if(this.index >= this.getNumberOfSprites()) {
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

