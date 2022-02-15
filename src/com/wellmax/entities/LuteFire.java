package com.wellmax.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.Camera;

/**
 * The luteFire is the base projectile launched
 * by player. It deals normal damage and causes
 * normal knock-back
 * @author joao.gomes
 *
 */
public class LuteFire extends Projectile{
	//---------------------------- Attributes ----------------------------------//
	/**
	 * Array with all luteFire sprites
	 */
	private final BufferedImage[][] notes;
	/**
	 * Index of note sprite at notes array
	 */
	private int indexNote;
	/**
	 * Number of notes existing in notes array
	 */
	private int numberOfNotes;
	
	//---------------------------- Methods ----------------------------------//	
	/**
	 * Constructor
	 * @param x x position
	 * @param y y position
	 * @param width LuteFire width
	 * @param height LuteFire height
	 * @param dx x axis multiplier
	 * @param dy y axis multiplier
	 * @param indexNote Note sprite to be rendered
	 */
	public LuteFire(int x, int y, int width, int height, Directions dir, int dx, int dy, int indexNote) {
		super(x, y, width, height, dx, dy);
		
		this.setIndexNote(indexNote);
		this.setNumberOfNotes(3);
		this.setTotalTime(45);
		this.setTimeRemain(0);
		this.setFaceDir(dir);
		this.setDamage(2);
		this.setKnockBackDealt(1);
		
		this.notes = new BufferedImage[this.getNumberOfNotes()][this.getNumberOfSprites()];
			
		this.setSpeed(2);	
		
		for(int i = 0; i < this.getNumberOfNotes(); i++) {
			for(int j = 0; j < this.getNumberOfSprites(); j++) {
				this.notes[i][j] = Game.spritesheet.getSprite(96 + j*16, 64 + i*16, this.getWidth(),
						this.getHeight());
			}
		}
		
	}

	public int getIndexNote() {
		return indexNote;
	}

	public void setIndexNote(int indexNote) {
		this.indexNote = indexNote;
	}

	public int getNumberOfNotes() {
		return numberOfNotes;
	}

	public void setNumberOfNotes(int numberOfNotes) {
		this.numberOfNotes = numberOfNotes;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(notes[this.getIndexNote()][this.getIndex()], (int)(this.getX() - Camera.x), (int)(this.getY() - Camera.y),
				null);
	}

	
}
