package com.wellmax.entities;

import java.awt.Graphics;

import com.wellmax.main.Game;
import com.wellmax.world.Camera;

/**
 * The fireball is a projectile stronger than
 * luteFire, dealing double damage. But it has a
 * greater coolDown.
 * @author joao.gomes
 *
 */
public class Fireball extends Collectible{

	//---------------------------- Attributes ----------------------------------//	
	/**
	 * Number of fireballs that the collectible gives
	 */
	private int numberOfFireballs;
	//---------------------------- Methods ----------------------------------//	
	
	/**
	 * Constructor
	 * @param x x position
	 * @param y y position
	 * @param width fireball width
	 * @param height fireball height
	 */
	public Fireball(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setNumberOfFireballs(5);
		this.setNumberOfSprites(4);
	}

	public int getNumberOfFireballs() {
		return numberOfFireballs;
	}

	public void setNumberOfFireballs(int numberOfFireballs) {
		this.numberOfFireballs = numberOfFireballs;
	}

	@Override
	public void effect() {
		Game.player.setFireballs(Game.player.getFireballs() + this.getNumberOfFireballs());
		Game.player.setHasFireball(true);
	}

	@Override
	public void update() {
		this.countFrames(true);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(FIREBALL[this.getIndex()], (int)(this.getX() - Camera.x), (int)(this.getY() - Camera.y),
				null);
	}
	
}
