package com.melqjpgames.entities;

import java.awt.Graphics;

public class HealthPotion extends Entity{

	public HealthPotion(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void render(Graphics g) {
		g.drawImage(HEALTH_POTION_EN, (int)getX(), (int)getY(), null);
	}
	
	
}
