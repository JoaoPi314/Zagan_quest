package com.wellmax.entities;

import java.awt.Graphics;

import com.wellmax.main.Game;
import com.wellmax.world.Camera;

/**
 * The health potion heals the player when
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
	 * @param width Health potion width
	 * @param height Health potion height
	 */
	public HealthPotion(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setLifeHealed(6);
	}

	public int getLifeHealed() {
		return lifeHealed;
	}

	public void setLifeHealed(int lifeHealed) {
		this.lifeHealed = lifeHealed;
	}


	@Override
	public void effect() {
		Game.player.setLife(Game.player.getLife() + this.getLifeHealed());
		if(Game.player.getLife() > Game.player.getMaxLife())
			Game.player.setLife(Game.player.getMaxLife());
	}

	@Override
	public void update() {
		this.countFrames(true);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(HEALTH_POTION[this.getIndex()], (int)(this.getX() - Camera.x), (int)(this.getY() - Camera.y),
				null);
	}
	
}
