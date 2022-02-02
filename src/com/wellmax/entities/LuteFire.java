package com.wellmax.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.Camera;
import com.wellmax.world.World;

public class LuteFire extends Entity{

	
	private int dx;
	private int dy;
	private int dir;
	private double speed;
	private int indexNote = 0;
	private BufferedImage[][] notes;

	
	private int nOfSprites = 4;
	private int nOfNotes = 3;
	// Animation of sprite
	private int frames;
	private final int maxFrames = 10;
	private int index;
	private final int maxIndex = 3;
	
	private int totalTime = 30; 
	private int timeRemain = 0;
	
	
	public LuteFire(int x, int y, int width, int height, Directions dir, int dx, int dy, int indexNote) {
		super(x, y, width, height);
		
		this.dx = dx;
		this.dy = dy;
		this.setFaceDir(dir);
		this.indexNote = indexNote;
		notes = new BufferedImage[3][4];
			
		speed = 2;
		
		for(int i = 0; i < nOfNotes; i++) {
			for(int j = 0; j < nOfSprites; j++) {
				notes[i][j] = Game.spritesheet.getSprite(96 + j*16, 64 + i*16, width, height);
			}
		}
		
	}
	
	public void update() {
		if(World.isFree((int)(x + dx*speed),(int)(y + dy*speed))) {
			x += dx*speed;
			y += dy*speed;
		}else {
			Game.luteFires.remove(this);
		}
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) {
				index = 0;
			}
		}
		
		timeRemain++;
		if(timeRemain >= totalTime) {
			Game.luteFires.remove(this);
			return;
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(notes[indexNote][index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
	
	
}
