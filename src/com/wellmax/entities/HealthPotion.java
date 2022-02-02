package com.wellmax.entities;

import java.awt.Graphics;

import com.wellmax.world.Camera;

public class HealthPotion extends Entity{

	
	private int frames;
	private final int maxFrames = 10;
	private int index;
	private final int maxIndex = 3;
	
	public HealthPotion(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	
	public void update() {
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) {
				index = 0;
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(HEALTH_POTION_EN[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
	}
	
	
}
