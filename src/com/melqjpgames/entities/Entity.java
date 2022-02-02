package com.melqjpgames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.melqjpgames.entities.types.Directions;
import com.melqjpgames.main.Game;

/**
 *  The Entity class covers all entities that
 *  has an intelligence (include player)
 * @author joao.gomes
 *
 */
public class Entity extends GenericEntity{

	//---------------------------- Attributes ----------------------------------//	

	/**
	 * Direction the entity wants to move
	 */
	protected Directions moveDir;
	/**
	 * Direction the entity faces
	 */
	protected Directions faceDir;
	/**
	 * Entity speed
	 */
	protected double speed;
	/**
	 * Flag that indicates if entity is moving
	 */
	protected boolean moving;
	/**
	 * Entity life
	 */
	protected double life;
	/**
	 * Entity defense
	 */
	protected double defense;
	/**
	 * Flag that indicates if entity took damage recently
	 */
	protected boolean damaged;
	/**
	 * Number of frames the damage sprite should be rendered 
	 */
	protected int damageFrames;
	/**
	 * Knockback speed when taking damage
	 */
	protected int kbSpeed;
	/**
	 * Knockback direction when taking damage
	 */
	protected Directions kbDir;
	
	//---------------------------- Methods ----------------------------------//	

	
	public static BufferedImage []HEALTH_POTION_EN;
	public static BufferedImage []FIREBALL_EN;

	static {
		HEALTH_POTION_EN = new BufferedImage[4];
		FIREBALL_EN = new BufferedImage[4];
		
		
		for(int i = 0; i < FIREBALL_EN.length; i++) {
			HEALTH_POTION_EN[i] = Game.spritesheet.getSprite(96 + i*16, 112, 16, 16);
			FIREBALL_EN[i] = Game.spritesheet.getSprite(96 + i*16, 128, 16, 16);
		}
		
	}
	
	/**
	 * The constructor takes the fundamental parameters of an Entity
	 * @param x: x position
	 * @param y: y position
	 * @param width: Entity width
	 * @param height: Entity heighy
	 */
	public Entity(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	
	

	/**
	 * @return the moveDir
	 */
	public Directions getMoveDir() {
		return moveDir;
	}
	
	/**
	 * @param moveDir the moveDir to set
	 */
	public void setMoveDir(Directions moveDir) {
		this.moveDir = moveDir;
	}
	
	/**
	 * @return the faceDir
	 */
	public Directions getFaceDir() {
		return faceDir;
	}
	
	/**
	 * @param faceDir the faceDir to set
	 */
	public void setFaceDir(Directions faceDir) {
		this.faceDir = faceDir;
	}
	
	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}
	
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	/**
	 * @return the moving
	 */
	public boolean isMoving() {
		return moving;
	}
	
	/**
	 * @param moving the moving to set
	 */
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	/**
	 * @return the life
	 */
	public double getLife() {
		return life;
	}
	
	/**
	 * @param life the life to set
	 */
	public void setLife(double life) {
		this.life = life;
	}
	
	/**
	 * @return the defense
	 */
	public double getDefense() {
		return defense;
	}
	
	/**
	 * @param defense the defense to set
	 */
	public void setDefense(double defense) {
		this.defense = defense;
	}
	
	/**
	 * @return the damaged
	 */
	public boolean isDamaged() {
		return damaged;
	}
	
	/**
	 * @param damaged the damaged to set
	 */
	public void setDamaged(boolean damaged) {
		this.damaged = damaged;
	}
	
	/**
	 * @return the damageFrames
	 */
	public int getDamageFrames() {
		return damageFrames;
	}
	
	/**
	 * @param damageFrames the damageFrames to set
	 */
	public void setDamageFrames(int damageFrames) {
		this.damageFrames = damageFrames;
	}
	
	/**
	 * @return the kbspeed
	 */
	public int getKbSpeed() {
		return kbSpeed;
	}
	
	/**
	 * @param kbspeed the kbspeed to set
	 */
	public void setKbspeed(int kbSpeed) {
		this.kbSpeed = kbSpeed;
	}
	
	/**
	 * @return the kbdir
	 */
	public Directions getKbDir() {
		return kbDir;
	}
	
	/**
	 * @param kbdir the kbdir to set
	 */
	public void setKbDir(Directions kbDir) {
		this.kbDir = kbDir;
	}


	
	@Override
	public void update() {
		
	}


	@Override
	public void render(Graphics g) {
		
	}

}
