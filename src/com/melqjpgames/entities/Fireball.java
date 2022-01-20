package com.melqjpgames.entities;

import java.awt.Graphics;

import com.melqjpgames.world.Camera;

public class Fireball extends Entity{

	
	private int frames;
	private final int maxFrames = 10;
	private int index;
	private final int maxIndex = 3;
	
	
	public Fireball(int x, int y, int width, int height) {
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
		g.drawImage(FIREBALL_EN[index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
	}
	
}
