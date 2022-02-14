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
	private boolean attacking;

	/**
	 * Player scythe
	 */
	private final BufferedImage scytheDownAndRight;
	private final BufferedImage scytheUpAndLeft;

	/**
	 * Scythe attacking
	 */
	private final BufferedImage[] scytheAttackDownAndRight;
	private final BufferedImage[] scytheAttackUpAndLeft;


	/**
	 * Flag to start attacking animation
	 */
	private boolean startScytheAttack;



	/**
	 * Scythe position
	 */
	private double scytheX;
	private double scytheY;


	private int scytheFrames;
	private int scytheMaxFrames;
	private int scytheIndex;


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
		this.setSpeed(1.2);
		this.setFaceDir(Directions.DOWN); 
		this.setLife(12);
		this.setMaxLife(12);
		this.setDefense(1);
		
		this.setHasFireball(false);

		this.setScytheMaxFrames(6);


		// Initiates sprites
		this.enDown = Game.spritesheet.getSprite(0, 0, this.getWidth(), this.getHeight());
		this.enLeft = Game.spritesheet.getSprite(32, 0, this.getWidth(), this.getHeight());
		this.enUp = Game.spritesheet.getSprite(64, 0, this.getWidth(), this.getHeight());
		this.enRight = Game.spritesheet.getSprite(96, 0, this.getWidth(), this.getHeight());

		this.enWalkingRight = new BufferedImage[this.getNumberOfSprites()];
		this.enWalkingLeft = new BufferedImage[this.getNumberOfSprites()];
		this.enWalkingUp = new BufferedImage[this.getNumberOfSprites()];
		this.enWalkingDown = new BufferedImage[this.getNumberOfSprites()];
		this.scytheAttackDownAndRight = new BufferedImage[this.getNumberOfSprites()];
		this.scytheAttackUpAndLeft = new BufferedImage[this.getNumberOfSprites()];

		this.scytheDownAndRight = Game.spritesheet.getSprite(0, 288, this.getWidth(), this.getHeight());
		this.scytheUpAndLeft = Game.spritesheet.getSprite(32, 288, this.getWidth(), this.getHeight());

		this.enDamageRight = Game.spritesheet.getSprite(64, 48, this.getWidth(), this.getHeight());
		this.enDamageLeft = Game.spritesheet.getSprite(64, 64, this.getWidth(), this.getHeight());
		this.enDamageUp = Game.spritesheet.getSprite(64, 16, this.getWidth(), this.getHeight());
		this.enDamageDown = Game.spritesheet.getSprite(64, 32, this.getWidth(), this.getHeight());
		
		this.playerDead = Game.spritesheet.getSprite(16, 144, this.getWidth(), this.getHeight());
		
		for(int i = 0; i < this.getNumberOfSprites(); i++) {
			this.enWalkingRight[i] = Game.spritesheet.getSprite(i*this.getWidth(), 192, this.getWidth(),
					this.getHeight());
			this.enWalkingLeft[i] = Game.spritesheet.getSprite(i*this.getWidth(), 128, this.getWidth(),
					this.getHeight());
			this.enWalkingUp[i] = Game.spritesheet.getSprite(i*this.getWidth(), 256, this.getWidth(),
					this.getHeight());
			this.enWalkingDown[i] = Game.spritesheet.getSprite(i*this.getWidth(), 64, this.getWidth(),
					this.getHeight());

			this.scytheAttackDownAndRight[i] = Game.spritesheet.getSprite(64 + i*this.getWidth(), 288, this.getWidth(),
					this.getHeight());
			this.scytheAttackUpAndLeft[i] = Game.spritesheet.getSprite(i*this.getWidth(), 64, this.getWidth(),
					this.getHeight());

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

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public double getScytheX() {
		return scytheX;
	}

	public void setScytheX(double scytheX) {
		this.scytheX = scytheX;
	}

	public double getScytheY() {
		return scytheY;
	}

	public void setScytheY(double scytheY) {
		this.scytheY = scytheY;
	}

	public boolean isStartScytheAttack() {
		return startScytheAttack;
	}

	public void setStartScytheAttack(boolean startScytheAttack) {
		this.startScytheAttack = startScytheAttack;
	}

	public int getScytheFrames() {
		return scytheFrames;
	}

	public void setScytheFrames(int scytheFrames) {
		this.scytheFrames = scytheFrames;
	}

	public int getScytheMaxFrames() {
		return scytheMaxFrames;
	}

	public void setScytheMaxFrames(int scytheMaxFrames) {
		this.scytheMaxFrames = scytheMaxFrames;
	}

	public int getScytheIndex() {
		return scytheIndex;
	}

	public void setScytheIndex(int scytheIndex) {
		this.scytheIndex = scytheIndex;
	}



	/**
	 * Method to calculate player movement based on
	 * user inputs
	 */
	private void controlledMovement() {
		
		this.setMoving(false);
		
		if(this.isRight() && World.isFree((int)(this.getX() + this.getSpeed()), (int) this.getY()) &&
				this.isNotCollidingWithScenario((int) (this.getX() + this.getSpeed()), (int) this.getY())){
			this.setX(this.getX() + this.getSpeed());
			this.setFaceDir(Directions.RIGHT);
			this.setMoving(true);
		}else if(this.isLeft() && World.isFree((int)(this.getX() - this.getSpeed()), (int) this.getY())&&
				this.isNotCollidingWithScenario((int) (this.getX() - this.getSpeed()), (int) this.getY())){
			this.setX(this.getX() - this.getSpeed());
			this.setFaceDir(Directions.LEFT);
			this.setMoving(true);
		}
		if(this.isUp() && World.isFree((int)this.getX(), (int) (this.getY() - this.getSpeed())) &&
				this.isNotCollidingWithScenario((int) this.getX(), (int) (this.getY() - this.getSpeed()))) {
			this.setY(this.getY() - this.getSpeed());
			this.setFaceDir(Directions.UP);
			this.setMoving(true);
		}else if(this.isDown() && World.isFree((int)this.getX(), (int) (this.getY() + this.getSpeed())) &&
				this.isNotCollidingWithScenario((int) this.getX(), (int) (this.getY() + this.getSpeed()))) {
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
		// After attacking, player must wait a cool down
		this.setAttacking(false);
		this.setCoolDown(true);
		// If player has fireball power and fireballs remaining
		if(this.isHasFireball() && this.getFireballs() > 0) {
			// FIRE
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
			FireballShoot fire = new FireballShoot((int)(this.getX()), (int)(this.getY()), this.getWidth(),
					this.getHeight(), this.getFaceDir(), dx, dy);
			Game.projectiles.add(fire);
			this.setFireballs(this.getFireballs() - 1);

			if(this.getFireballs() <= 0)
				this.setHasFireball(false);

			// Cool down of fireball
			this.setMaxFramesCoolDown(60);
		}else { // Default attack is scythe
			this.setMaxFramesCoolDown(40);
			this.setStartScytheAttack(true);

		}
		// The initial frames is equal to max frames
		this.setFramesCoolDown(getMaxFramesCoolDown());

	}

	/**
	 * Method to update scythe position
	 */
	public void updateScythe() {

		switch (this.getFaceDir()) {
			case RIGHT, LEFT -> {
				this.setScytheX(this.getX() - 1);
				this.setScytheY(this.getY() - 1);
			}
			case UP, DOWN -> {
				this.setScytheX(this.getX() - 1);
				this.setScytheY(this.getY() - 3);
			}
		}

		if(this.isStartScytheAttack()) {
			this.setScytheFrames(this.getScytheFrames() + 1);
			if(this.getScytheFrames() == this.getScytheMaxFrames()) {
				this.setScytheFrames(0);
				this.scytheIndex++;
				if(this.scytheIndex >= this.getNumberOfSprites()) {
					this.scytheIndex = 0;
					this.setStartScytheAttack(false);
				}
			}
		}
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
		if(this.isAttacking() && !this.isCoolDown()) {
			this.attack();
		}

		// Colliding project
		this.collidingProjectile();

		this.coolDownCalculus();

		// Makes camera follow player
		Camera.x = Camera.clamp((int)this.getX() - (Game.WIDTH / 2), 0, World.WIDTH*32 - Game.WIDTH);
		Camera.y = Camera.clamp((int)this.getY() - (Game.HEIGHT / 2), 0, World.HEIGHT*32 - Game.HEIGHT);
		
	}

	/**
	 * Method do render scythe
	 * @param g Graphics to render sprite
	 */
	public void renderScythe(Graphics g) {

		int xOffset = 0;
		int yOffset = 0;

		BufferedImage currentSprite;
		if(this.isStartScytheAttack()) {
			switch (this.getFaceDir()) {
				case RIGHT -> {
					currentSprite = this.scytheAttackDownAndRight[this.getScytheIndex()];
					xOffset = 16;
				}
				case DOWN -> {
					currentSprite = this.scytheAttackDownAndRight[this.getScytheIndex()];
					yOffset = 16;
				}
				case LEFT -> {
					currentSprite = this.scytheAttackUpAndLeft[this.getScytheIndex()];
					xOffset = -16;
				}
				case UP -> {
					currentSprite = this.scytheAttackUpAndLeft[this.getScytheIndex()];
					yOffset = -16;
				}
				default -> currentSprite = this.scytheAttackDownAndRight[this.getScytheIndex()];
			};


		}else {
			currentSprite = switch (this.getFaceDir()) {
				case RIGHT, DOWN -> this.scytheDownAndRight;
				case LEFT, UP -> this.scytheUpAndLeft;
			};

		}

		g.drawImage(currentSprite, (int) (this.getScytheX() + xOffset - Camera.x),
				(int) (this.getScytheY() + yOffset - Camera.y), null);
	}


	/**
	 * Method to render sprite of player dead
	 * @param g Graphics to render sprite
	 */
	public void renderDead(Graphics g) {
		g.drawImage(playerDead, (int)(this.getX() - Camera.x), (int)(this.getY() - Camera.y), null);
	}


}
