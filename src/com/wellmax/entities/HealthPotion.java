package com.wellmax.entities;

import java.awt.Graphics;

import com.wellmax.main.Game;
import com.wellmax.world.Camera;

/**
 * The heatlhPotion heals the player when
 * collected
 * @author joao.gomes
 *
 */
public class HealthPotion extends Collectible{

	//---------------------------- Attributes ----------------------------------//	
	/**
	 * Life healed by collectible
	 */
	private int lifeHealed;
	//---------------------------- Methods ----------------------------------//
	
	/**
	 * Constructor
	 * @param x x position
	 * @param y y position
	 * @param width Healthpotion width
	 * @param height Healthpotion height
	 */
	public HealthPotion(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setLifeHealed(6);
	}

	/**
	 * @return the lifeHealed
	 */
	public int getLifeHealed() {
		return lifeHealed;
	}

	/**
	 * @param lifeHealed the lifeHealed to set
	 */
	public void setLifeHealed(int lifeHealed) {
		this.lifeHealed = lifeHealed;
	}



	public void effect() {
		Game.player.setLife(Game.player.getLife() + this.getLifeHealed());
		if(Game.player.getLife() > Game.player.getMaxLife())
			Game.player.setLife(Game.player.getMaxLife());
	}
	
	
	public void update() {
		this.countFrames(true);
	}
	
	
	public void render(Graphics g) {
		g.drawImage(HEALTH_POTION[index], (int)(this.getX() - Camera.x), (int)(this.getY() - Camera.y), null);
	}
	
}
