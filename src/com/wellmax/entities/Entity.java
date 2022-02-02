package com.wellmax.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.Camera;
import com.wellmax.world.World;

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
	 * Entity max life
	 */
	protected int maxLife;
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
	
	
	// Sprites
	/**
	 * Sprites of entity facing right
	 */
	protected BufferedImage[] enRight;
	/**
	 * sprites of entity facing left
	 */
	protected BufferedImage[] enLeft;
	/**
	 * Sprites of entity facing up
	 */
	protected BufferedImage[] enUp;
	/**
	 * Sprites of entity facing down
	 */
	protected BufferedImage[] enDown;
	
	/**
	 * Sprite of entity taking damage facing right
	 */
	protected BufferedImage enDamageRight;
	/**
	 * Sprite of entity taking damage facing left
	 */
	protected BufferedImage enDamageLeft;
	/**
	 * Sprite of entity taking damage facing up
	 */
	protected BufferedImage enDamageUp;
	/**
	 * Sprite of entity taking damage facing down
	 */
	protected BufferedImage enDamageDown;

	
	
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
	 * @return the maxLife
	 */
	public int getMaxLife() {
		return maxLife;
	}

	/**
	 * @param maxLife the maxLife to set
	 */
	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
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

	/**
	 * Method to calculate player movement when it is
	 * taking damage
	 */
	protected void damageMovement() {
		if(this.isDamaged()) {
			
			switch(this.getKbDir()) {
				case RIGHT:
					if(World.isFree((int)(this.getX() + this.getSpeed()*this.getKbSpeed()), (int) this.getY())) {
						this.setX(this.getX() + this.getSpeed()*this.getKbSpeed());
						this.setMoving(true);
					}
					break;
				case LEFT:
					if(World.isFree((int)(this.getX() - this.getSpeed()*this.getKbSpeed()), (int) this.getY())) {
						this.setX(this.getX() - this.getSpeed()*this.getKbSpeed());
						this.setMoving(true);
					}
					break;
				case UP:
					if(World.isFree((int)this.getX(), (int) (this.getY() - this.getSpeed()*this.getKbSpeed()))) {
						this.setY(this.getY() - this.getSpeed()*this.getKbSpeed());
						this.setMoving(true);						
					}
					break;
				case DOWN:
					if(World.isFree((int)this.getX(), (int) (this.getY() + this.getSpeed()*this.getKbSpeed()))) {
						this.setY(this.getY() + this.getSpeed()*this.getKbSpeed());
						this.setMoving(true);						
					}
					break;
				default:
					break;
			
			}
			
			// Sprite damage calculus
			damageFrames++;
			if(damageFrames ==8) {
				damageFrames = 0;
				setDamaged(false);
			}	
		}
	}
	
	@Override
	public void update() {
		
	}


	@Override
	public void render(Graphics g) {
		
		BufferedImage currentSprite;
		
		switch(this.getFaceDir()) {
			case RIGHT:
				if(!this.isDamaged()) {
					currentSprite = this.enRight[index];
				}else {
					currentSprite = this.enDamageRight;
				}
				break;
			case LEFT:
				if(!this.isDamaged()) {
					currentSprite = this.enLeft[index];
				}else {
					currentSprite = this.enDamageLeft;
				}
				break;
			case UP:
				if(!this.isDamaged()) {
					currentSprite = this.enUp[index];
				}else {
					currentSprite = this.enDamageUp;
				}
				break;
			case DOWN: // If DOWN or NONE, sprite down is rendered	
			default:
				if(!this.isDamaged()) {
					currentSprite = this.enDown[index];
				}else {
					currentSprite = this.enDamageDown;
				}
				break;
		}
		
		g.drawImage(currentSprite, (int)(this.getX() - Camera.x), (int)(this.getY() - Camera.y), null);

	}

}
