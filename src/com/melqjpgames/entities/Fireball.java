package com.melqjpgames.entities;

import java.awt.Graphics;

import com.melqjpgames.world.Camera;

public class Fireball extends Entity{

	public Fireball(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void render(Graphics g) {
		g.drawImage(FIREBALL_EN, (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
	}
	
}
