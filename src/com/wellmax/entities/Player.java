package com.wellmax.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.Camera;
import com.wellmax.world.World;

/**
 * THe player class cis responsible for all player logic
 * @author joao.gomes
 */
public class Player extends Creature {

	//---------------------------- Attributes ----------------------------------//
	/**
	 * Directions of player movement
	 */
	private boolean right, left, up, down;
	/**
	 * Sprite of player dead
	 */
	private final BufferedImage playerDead;
	/**
	 * Flag that indicates if player has fireball power
	 */
	private boolean hasFireball;
	/**
	 * Number of fireballs remaining
	 */
	private int fireballs;
	/**
	 * Flag that indicates if player is shooting
	 */
	private boolean shoot;

	
	//---------------------------- Methods ----------------------------------//
	/**
	 * The constructor creates a player with the parameters
	 * @param x x position
	 * @param y y position
	 * @param width player width
	 * @param height player height
	 */
	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.setSpeed(0.9);
		this.setFaceDir(Directions.DOWN); 
		this.setLife(12);
		this.setMaxLife(12);
		this.setDefense(1);
		
		this.setHasFireball(false);
		this.setFireballs(0);
		
		// Initiates sprites
		this.enRight = new BufferedImage[this.getNumberOfSprites()];
		this.enLeft = new BufferedImage[this.getNumberOfSprites()];
		this.enUp = new BufferedImage[this.getNumberOfSprites()];
		this.enDown = new BufferedImage[this.getNumberOfSprites()];

		this.enDamageRight = Game.spritesheet.getSprite(64, 48, this.getWidth(), this.getHeight());
		this.enDamageLeft = Game.spritesheet.getSprite(64, 64, this.getWidth(), this.getHeight());
		this.enDamageUp = Game.spritesheet.getSprite(64, 16, this.getWidth(), this.getHeight());
		this.enDamageDown = Game.spritesheet.getSprite(64, 32, this.getWidth(), this.getHeight());
		
		this.playerDead = Game.spritesheet.getSprite(16, 144, this.getWidth(), this.getHeight());
		
		for(int i = 0; i < this.getNumberOfSprites(); i++) {
			this.enRight[i] = Game.spritesheet.getSprite(i*16, 48, this.getWidth(), this.getHeight());
			this.enLeft[i] = Game.spritesheet.getSprite(i*16, 64, this.getWidth(), this.getHeight());
			this.enUp[i] = Game.spritesheet.getSprite(i*16, 16, this.getWidth(), this.getHeight());
			this.enDown[i] = Game.spritesheet.getSprite(i*16, 32, this.getWidth(), this.getHeight());
		}
		
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isHasFireball() {
		return hasFireball;
	}

	public void setHasFireball(boolean hasFireball) {
		this.hasFireball = hasFireball;
	}

	public int getFireballs() {
		return fireballs;
	}

	public void setFireballs(int fireballs) {
		this.fireballs = fireballs;
	}

	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	
	/**
	 * Method to calculate player movement based on
	 * user inputs
	 */
	private void controlledMovement() {
		
		this.setMoving(false);
		
		if(this.isRight() && World.isFree((int)(this.getX() + this.getSpeed()), (int) this.getY()) &&
				this.isNotCollidingWithScenario((int) (this.getX() + this.getSpeed()), (int) this.getY()) &&
				this.isNotCollidingWithEnemies((int) (this.getX() + this.getSpeed()), (int) this.getY())){
			this.setX(this.getX() + this.getSpeed());
			this.setFaceDir(Directions.RIGHT);
			this.setMoving(true);
		}else if(this.isLeft() && World.isFree((int)(this.getX() - this.getSpeed()), (int) this.getY())&&
				this.isNotCollidingWithScenario((int) (this.getX() - this.getSpeed()), (int) this.getY())&&
				this.isNotCollidingWithEnemies((int) (this.getX() - this.getSpeed()), (int) this.getY())){
			this.setX(this.getX() - this.getSpeed());
			this.setFaceDir(Directions.LEFT);
			this.setMoving(true);
		}
		if(this.isUp() && World.isFree((int)this.getX(), (int) (this.getY() - this.getSpeed())) &&
				this.isNotCollidingWithScenario((int) this.getX(), (int) (this.getY() - this.getSpeed()))&&
				this.isNotCollidingWithEnemies((int) this.getX(), (int) (this.getY() - this.getSpeed()))) {
			this.setY(this.getY() - this.getSpeed());
			this.setFaceDir(Directions.UP);
			this.setMoving(true);
		}else if(this.isDown() && World.isFree((int)this.getX(), (int) (this.getY() + this.getSpeed())) &&
				this.isNotCollidingWithScenario((int) this.getX(), (int) (this.getY() + this.getSpeed())) &&
				this.isNotCollidingWithEnemies((int) this.getX(), (int) (this.getY() + this.getSpeed()))) {
			this.setY(this.getY() + this.getSpeed());
			this.setFaceDir(Directions.DOWN);
			this.setMoving(true);
		}
		
	}

	/**
	 * Method to check collision of player with collectibles
	 */
	public void checkItems() {
		for(int i = 0; i < Game.collectibles.size(); i++) {
			Collectible en = Game.collectibles.get(i);
			if(Entity.isColliding(this, en)) {
				en.effect();
				Game.collectibles.remove(en);
			}
		}
	}

	@Override
	public void attack() {

		// FIRE
		this.setShoot(false);
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

		// After shoot, player must wait a cool down
		this.setCoolDown(true);
		// If player has fireball power and fireballs remaining
		if(this.isHasFireball() && this.getFireballs() > 0) {
			FireballShoot fire = new FireballShoot((int)(this.getX()), (int)(this.getY()), this.getWidth(),
					this.getHeight(), this.getFaceDir(), dx, dy);
			Game.projectiles.add(fire);
			this.setFireballs(this.getFireballs() - 1);

			if(this.getFireballs() <= 0)
				this.setHasFireball(false);

			// Cool down of fireball
			this.setMaxFramesCoolDown(60);
		}else { // Default shoot is lute fire
			// Choose a random note sprite
			int randFire = Game.rand.nextInt(3);
			LuteFire fire = new LuteFire((int)(this.getX()), (int)(this.getY()), this.getWidth(), this.getHeight(),
					this.getFaceDir(), dx, dy, randFire);
			Game.projectiles.add(fire);

			// Cool down of lute fire
			this.setMaxFramesCoolDown(30);
		}
		// The initial frames is equal to max frames
		this.setFramesCoolDown(getMaxFramesCoolDown());

	}

	public void update() {
		
		// Keyboard movement
		this.controlledMovement();
		
		// Damage movement
		this.damageMovement();

		// Sprite update
		this.countFrames(this.isMoving());
		
		// Check collectibles
		this.checkItems();
	
		// Shooting update
		if(this.isShoot() && !this.isCoolDown()) {
			this.attack();
		}

		this.coolDownCalculus();

		// Makes camera follow player
		Camera.x = Camera.clamp((int)this.getX() - (Game.WIDTH / 2), 0, World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp((int)this.getY() - (Game.HEIGHT / 2), 0, World.HEIGHT*16 - Game.HEIGHT);
		
	}

	/**
	 * Method to render sprite of player dead
	 * @param g Graphics to render sprite
	 */
	public void renderDead(Graphics g) {
		g.drawImage(playerDead, (int)(this.getX() - Camera.x), (int)(this.getY() - Camera.y), null);
	}
	
	
}
