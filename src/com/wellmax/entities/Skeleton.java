package com.wellmax.entities;

import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;

/**
 * Skeleton class. The skeleton fires bones when player is near. 
 * It also is stronger than orcs
 * @author joao.gomes
 *
 */
public class Skeleton extends Enemy{
	//---------------------------- Attributes ----------------------------------//
	/**
	 * Max approach of enemy
	 */
	private int maxApproaching;
	/**
	 * Flag that indicates id skeleton is attacking with ranged attacks
	 */
	private boolean ranged;



	//---------------------------- Methods ----------------------------------//

	/**
	 * Constructor
	 * @param x x position
	 * @param y y position
	 * @param width Skeleton width
	 * @param height Skeleton height
	 * @param dir Skeleton initial faceDir
	 */
	public Skeleton(int x, int y, int width, int height, Directions dir) {
		super(x, y, width, height);
		this.setSpeed(1);
		this.setFaceDir(Directions.DOWN); 
		this.setLife(10);
		this.setFov(8);
		this.setDefense(1.5);
		this.setDamage(1);
		this.setMaxApproaching(4);
		
		// Initiates sprites
		enRight = new BufferedImage[this.getNumberOfSprites()];
		enLeft = new BufferedImage[this.getNumberOfSprites()];
		enUp = new BufferedImage[this.getNumberOfSprites()];
		enDown = new BufferedImage[this.getNumberOfSprites()];

		enDamageRight = Game.spritesheet.getSprite(80, 112, this.getWidth(), this.getHeight());
		enDamageLeft = Game.spritesheet.getSprite(80, 128, this.getWidth(), this.getHeight());
		enDamageUp = Game.spritesheet.getSprite(80, 80, this.getWidth(), this.getHeight());
		enDamageDown = Game.spritesheet.getSprite(80, 96, this.getWidth(), this.getHeight());

		
		for(int i = 0; i < this.getNumberOfSprites(); i++) {
			enRight[i] = Game.spritesheet.getSprite(160 + i*16, 32, this.getWidth(), this.getHeight());
			enLeft[i] = Game.spritesheet.getSprite(160 + i*16, 48, this.getWidth(), this.getHeight());
			enUp[i] = Game.spritesheet.getSprite(160 + i*16, 0, this.getWidth(), this.getHeight());
			enDown[i] = Game.spritesheet.getSprite(160 + i*16, 16, this.getWidth(), this.getHeight());	
		}
		
	}

	public int getMaxApproaching() {
		return maxApproaching;
	}

	public void setMaxApproaching(int maxApproaching) {
		this.maxApproaching = maxApproaching;
	}

	public boolean isRanged() {
		return ranged;
	}

	public void setRanged(boolean ranged) {
		this.ranged = ranged;
	}

	/**
	 * Method to check if enemy is at approaching limit of player
	 * @return true if enemy is too near the player
	 */
	private boolean limitApproachPlayer(){
		boolean limitX = (Math.abs(this.getX() - Game.player.getX()) <= this.getWidth()*this.getMaxApproaching());
		boolean limitY = (Math.abs(this.getY() - Game.player.getY()) <= this.getWidth()*this.getMaxApproaching());;

		return (limitX && limitY);
	}


	/**
	 * Method to execute enemy IA
	 */
	protected void enemyIA() {

		if(!Entity.isColliding(this, Game.player) || Game.player.getLife() <= 0) {
			if(this.nearPlayer() && !this.limitApproachPlayer()) { // Enemy is near player nut not too near
				this.enemyFollowingIA();
			}else if(this.limitApproachPlayer()){ // Enemy is at limit range - Starts firing
				this.setRanged(true);
				this.attack();
			}
		}else if(Game.player.getLife() > 0) { // If enemy is colliding with player, and player is alive
			this.setRanged(false);
			this.attack();
		}

	}

	/**
	 * Method to adjust skeleton direction when it near player
	 */
	private void facingPlayer(){
		// Vector starting at enemy and ending at player
		double xDistance = Game.player.getX() - this.getX();
		double yDistance = Game.player.getY() - this.getY();

		// Angle of this vector
		double angle = Math.toDegrees(Math.atan2(yDistance, xDistance));

		// Checks angle to make Skeleton always look to the nearest player direction
		if(angle > 135 || angle <= -135) { // Player is in left region
			this.setFaceDir(Directions.LEFT);
		}else if(angle <= -45){ // Player is in down region
			this.setFaceDir(Directions.UP);
		}else if(angle <= 45) { // Player is in right region
			this.setFaceDir(Directions.RIGHT);
		}else { // Player is in up region
			this.setFaceDir(Directions.DOWN);
		}
	}


	@Override
	public void attack() {
		if(this.isRanged()) { // Ranged attack

			this.facingPlayer();

			// Bone logic
			int xDistanceAbs = Math.abs((int) (Game.player.getX() - this.getX()));
			int yDistanceAbs = Math.abs((int) (Game.player.getY() - this.getY()));

			if((xDistanceAbs <= 8 || yDistanceAbs <= 8) && !this.isCoolDown()) {
				int dx = 0;
				int dy = 0;

				switch (this.getFaceDir()) {
					case RIGHT -> dx = 1;
					case LEFT -> dx = -1;
					case UP -> dy = -1;
					case DOWN -> dy = 1;
					default -> {
					}
				}

				this.setCoolDown(true);
				this.setMaxFramesCoolDown(40);
				this.setFramesCoolDown(this.getMaxFramesCoolDown());
				Bone fire = new Bone((int) (this.getX()), (int) (this.getY()), this.getWidth(),
						this.getHeight(), this.getFaceDir(), dx, dy);
				Game.projectiles.add(fire);
			}
		} else {
			// Melee attack
			if(Game.rand.nextInt(100) < 10) {
				Game.player.setLife(Game.player.getLife() - this.getDamage() / Game.player.getDefense());
				Game.player.setKnockBackDir(this.getFaceDir());
				Game.player.setKnockBackSpeed(2);
				Game.player.setDamaged(true);
			}
		}
	}

}
