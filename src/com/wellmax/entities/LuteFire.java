package com.wellmax.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.Camera;
import com.wellmax.world.World;

/**
 * The luteFire is the base projectile launched
 * by player. It deals normal damage and causes
 * normal knockback
 * @author joao.gomes
 *
 */
public class LuteFire extends Projectile{

	//---------------------------- Attributes ----------------------------------//	
	
	/**
	 * Array with all luteFire sprites
	 */
	private BufferedImage[][] notes;
	/**
	 * Index of note sprite at notes array
	 */
	private int indexNote;
	/**
	 * Number of notes existing in notes array
	 */
	private int nOfNotes;
	
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
		this.setnOfNotes(3);
		this.setTotalTime(45);
		this.setTimeRemain(0);
		this.setFaceDir(dir);
		this.setDamage(2);
		
		this.notes = new BufferedImage[this.getnOfNotes()][this.getnOfSprites()];
			
		this.setSpeed(2);	
		
		for(int i = 0; i < this.getnOfNotes(); i++) {
			for(int j = 0; j < this.getnOfSprites(); j++) {
				this.notes[i][j] = Game.spritesheet.getSprite(96 + j*16, 64 + i*16, this.getWidth(), this.getHeight());
			}
		}
		
	}
	
	
	/**
	 * @return the indexNote
	 */
	public int getIndexNote() {
		return indexNote;
	}

	/**
	 * @param indexNote the indexNote to set
	 */
	public void setIndexNote(int indexNote) {
		this.indexNote = indexNote;
	}

	/**
	 * @return the nOfNotes
	 */
	public int getnOfNotes() {
		return nOfNotes;
	}

	/**
	 * @param nOfNotes the nOfNotes to set
	 */
	public void setnOfNotes(int nOfNotes) {
		this.nOfNotes = nOfNotes;
	}

	
	public void render(Graphics g) {
		g.drawImage(notes[indexNote][index], (int)(getX() - Camera.x), (int)(getY() - Camera.y), null);
	}

	
}
