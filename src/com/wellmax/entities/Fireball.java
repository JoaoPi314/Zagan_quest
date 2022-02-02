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
	private int nOfFireballs;
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
		this.setnOfFireballs(5);
	}

	/**
	 * @return the nOfFireballs
	 */
	public int getnOfFireballs() {
		return nOfFireballs;
	}

	/**
	 * @param nOfFireballs the nOfFireballs to set
	 */
	public void setnOfFireballs(int nOfFireballs) {
		this.nOfFireballs = nOfFireballs;
	}
	

	public void effect() {
		Game.player.setFireballs(Game.player.getFireballs() + this.getnOfFireballs());
	}
	
	
	public void update() {
		this.countFrames(true);
	}
	
	
	public void render(Graphics g) {
		g.drawImage(FIREBALL[index], (int)(this.getX() - Camera.x), (int)(this.getY() - Camera.y), null);
	}
	
}
