package com.wellmax.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.Camera;
import com.wellmax.world.World;

/**
 *  The Creature class covers all entities that
 *  has an intelligence (include player)
 * @author joao.gomes
 *
 */
public abstract class Creature extends Entity {
	//---------------------------- Attributes ----------------------------------//

	/**
	 * Direction the entity faces
	 */
	private Directions faceDir;
	/**
	 * Creature speed
	 */
	private double speed;
	/**
	 * Flag that indicates if entity is moving
	 */
	private boolean moving;
	/**
	 * Creature life
	 */
	private double life;
	/**
	 * Creature max life
	 */
	private int maxLife;
	/**
	 * Creature defense
	 */
	private double defense;
	/**
	 * Flag that indicates if entity took damage recently
	 */
	private boolean damaged;
	/**
	 * Number of frames the damage sprite should be rendered 
	 */
	private int damageFrames;
	/**
	 * Knock-back speed when taking damage
	 */
	private int knockBackSpeed;
	/**
	 * Knock-back direction when taking damage
	 */
	private Directions knockBackDir;

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
	/**
	 * Flag that indicates if player has shot recently (to apply cool down)
	 */
	private boolean coolDown;
	/**
	 * Cool down frames (Cool down calculus)
	 */
	private double framesCoolDown;
	/**
	 * Number of frames that cool down must be applied (cool down calculus)
	 */
	private double maxFramesCoolDown;
	//---------------------------- Methods ----------------------------------//	
	
	/**
	 * The constructor takes the fundamental parameters of a Creature
	 * @param x: x position
	 * @param y: y position
	 * @param width: Creature width
	 * @param height: Creature height
	 */
	public Creature(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public Directions getFaceDir() {
		return faceDir;
	}

	public void setFaceDir(Directions faceDir) {
		this.faceDir = faceDir;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public double getLife() {
		return life;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	public void setLife(double life) {
		this.life = life;
	}

	public double getDefense() {
		return defense;
	}

	public void setDefense(double defense) {
		this.defense = defense;
	}

	public boolean isDamaged() {
		return damaged;
	}

	public void setDamaged(boolean damaged) {
		this.damaged = damaged;
	}

	public int getDamageFrames() {
		return damageFrames;
	}

	public void setDamageFrames(int damageFrames) {
		this.damageFrames = damageFrames;
	}

	public int getKnockBackSpeed() {
		return knockBackSpeed;
	}

	public void setKnockBackSpeed(int knockBackSpeed) {
		this.knockBackSpeed = knockBackSpeed;
	}

	public Directions getKnockBackDir() {
		return knockBackDir;
	}

	public void setKnockBackDir(Directions knockBackDir) {
		this.knockBackDir = knockBackDir;
	}

	public boolean isCoolDown() {
		return coolDown;
	}

	public void setCoolDown(boolean coolDown) {
		this.coolDown = coolDown;
	}

	public double getFramesCoolDown() {
		return framesCoolDown;
	}

	public void setFramesCoolDown(double framesCoolDown) {
		this.framesCoolDown = framesCoolDown;
	}

	public double getMaxFramesCoolDown() {
		return maxFramesCoolDown;
	}

	public void setMaxFramesCoolDown(double maxFramesCoolDown) {
		this.maxFramesCoolDown = maxFramesCoolDown;
	}

	/**
	 * Checks collision with enemies
	 * @param xx Next creature x position
	 * @param yy Next creature y position
	 * @return true if the creature can move without colliding with any enemy
	 */
	public boolean isNotCollidingWithEnemies(int xx, int yy) {
		Rectangle enemyCurrent = new Rectangle(xx + this.getMaskX(), yy + this.getMaskY(), this.getMaskWidth(),
				this.getMaskHeight());

		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if(this instanceof Enemy) {
				if (this == e)
					continue;
			}
			Rectangle enemyTarget = new Rectangle((int)(e.getX() + this.getMaskX()), (int)(e.getY() +  this.getMaskY()),
					this.getMaskWidth(), this.getMaskHeight());
			if(enemyCurrent.intersects(enemyTarget)){
				return false;
			}
		}
		return true;
	}



	/**
	 * Method to calculate creature movement when it is
	 * taking damage
	 */
	protected void damageMovement() {
		if(this.isDamaged()) {
			switch(this.getKnockBackDir()) {
				case RIGHT:
					if(World.isFree((int)(this.getX() + this.getSpeed()*this.getKnockBackSpeed()), (int) this.getY()) &&
							this.isNotCollidingWithScenario((int) (this.getX() + this.getKnockBackSpeed()),
									(int) this.getY())) {
						this.setX(this.getX() + this.getSpeed()*this.getKnockBackSpeed());
						this.setMoving(true);
					}
					break;
				case LEFT:
					if(World.isFree((int)(this.getX() - this.getSpeed()*this.getKnockBackSpeed()), (int) this.getY()) &&
							this.isNotCollidingWithScenario((int) (this.getX() - this.getKnockBackSpeed()),
									(int) this.getY())) {
						this.setX(this.getX() - this.getSpeed()*this.getKnockBackSpeed());
						this.setMoving(true);
					}
					break;
				case UP:
					if(World.isFree((int)this.getX(), (int) (this.getY() - this.getSpeed()*this.getKnockBackSpeed())) &&
							this.isNotCollidingWithScenario((int) this.getX(),
									(int) (this.getY() - this.getKnockBackSpeed())) ) {
						this.setY(this.getY() - this.getSpeed()*this.getKnockBackSpeed());
						this.setMoving(true);						
					}
					break;
				case DOWN:
					if(World.isFree((int)this.getX(), (int) (this.getY() + this.getSpeed()*this.getKnockBackSpeed())) &&
							this.isNotCollidingWithScenario((int) this.getX(),
									(int) (this.getY() + this.getKnockBackSpeed())) ) {
						this.setY(this.getY() + this.getSpeed()*this.getKnockBackSpeed());
						this.setMoving(true);						
					}
					break;
				default:
					break;
			
			}
			
			// Sprite damage calculus
			this.setDamageFrames(this.getDamageFrames() + 1);
			if(this.getDamageFrames() == 8) {
				this.setDamageFrames(0);
				this.setDamaged(false);
			}	
		}
	}
	/**
	 * Method to compute if a projectile hits player
	 */
	protected void collidingProjectile() {
		// Search for projectiles
		for(int i = 0; i < Game.projectiles.size(); i++) {
			Projectile e = Game.projectiles.get(i);
			if(this instanceof Player) {
				if (!(e instanceof Bone)) {
					continue;
				}
			} else {
				if (e instanceof Bone) {
					continue;
				}
			}
			if(Entity.isColliding(this, e)) {
				this.setKnockBackDir(e.getFaceDir());
				this.setKnockBackSpeed(e.getKnockBackDealt());
				this.setDamaged(true);
				this.setLife(this.getLife() - e.getDamage()/this.getDefense());
				Game.projectiles.remove(i);
				return;
			}
		}
	}


	/**
	 * Cool down calculus
	 */
	public void coolDownCalculus(){
		// Cool down calculus
		if(this.isCoolDown()) {
			this.setFramesCoolDown(this.getFramesCoolDown() - 1);
			if(this.getFramesCoolDown() <= 0) {
				this.setCoolDown(false);
			}
		}
	}


	public abstract void attack();

	@Override
	public void render(Graphics g) {
		
		BufferedImage currentSprite;
		
		switch(this.getFaceDir()) {
			case RIGHT:
				if(!this.isDamaged()) {
					currentSprite = this.enRight[this.getIndex()];
				}else {
					currentSprite = this.enDamageRight;
				}
				break;
			case LEFT:
				if(!this.isDamaged()) {
					currentSprite = this.enLeft[this.getIndex()];
				}else {
					currentSprite = this.enDamageLeft;
				}
				break;
			case UP:
				if(!this.isDamaged()) {
					currentSprite = this.enUp[this.getIndex()];
				}else {
					currentSprite = this.enDamageUp;
				}
				break;
			case DOWN:
			default:
				if(!this.isDamaged()) {
					currentSprite = this.enDown[this.getIndex()];
				}else {
					currentSprite = this.enDamageDown;
				}
				break;
		}
		
		g.drawImage(currentSprite, (int)(this.getX() - Camera.x), (int)(this.getY() - Camera.y), null);

	}

}
