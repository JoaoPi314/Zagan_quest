package com.wellmax.entities;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.Camera;
import com.wellmax.world.World;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


/**
 * THe scythe class will compute all scythe logic
 * @author joao.gomes
 */
public class Scythe extends Entity{

    //---------------------------- Attributes ----------------------------------//
    /**
     * Scythe attacking
     */
    private final BufferedImage[] scytheAttackDown;
    private final BufferedImage[] scytheAttackLeft;
    private final BufferedImage[] scytheAttackRight;
    private final BufferedImage[] scytheAttackUp;

    /**
     * Flag to start attacking animation
     */
    private boolean startScytheAttack;

    /**
     * Scythe position
     */
    private double scytheOffsetX;
    private double scytheOffsetY;

    /**
     * Couting attack frames
     */
    private int attackFrames;
    private int attackMaxFrames;
    private int attackIndex;

    //---------------------------- Methods ----------------------------------//

    public Scythe(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.setAttackMaxFrames(10);

        this.scytheAttackDown = new BufferedImage[this.getNumberOfSprites()];
        this.scytheAttackLeft = new BufferedImage[this.getNumberOfSprites()];
        this.scytheAttackRight = new BufferedImage[this.getNumberOfSprites()];
        this.scytheAttackUp = new BufferedImage[this.getNumberOfSprites()];

        for (int i = 0; i < this.getNumberOfSprites(); i++) {
            this.scytheAttackDown[i] = Game.spritesheet.getSprite(224, i * 48, this.getWidth(), 48);
            this.scytheAttackLeft[i] = Game.spritesheet.getSprite(320 + i * 48, 0, 48, this.getWidth());
            this.scytheAttackRight[i] = Game.spritesheet.getSprite(320 + i * 48, 96, 48, this.getWidth());
            this.scytheAttackUp[i] = Game.spritesheet.getSprite(608, i*48,this.getWidth(), 48);

        }
    }

    public double getScytheOffsetX() {
        return scytheOffsetX;
    }

    public void setScytheOffsetX(double scytheOffsetX) {
        this.scytheOffsetX = scytheOffsetX;
    }

    public double getScytheOffsetY() {
        return scytheOffsetY;
    }

    public void setScytheOffsetY(double scytheOffsetY) {
        this.scytheOffsetY = scytheOffsetY;
    }

    public boolean isStartScytheAttack() {
        return startScytheAttack;
    }

    public void setStartScytheAttack(boolean startScytheAttack) {
        this.startScytheAttack = startScytheAttack;
    }

    public int getAttackFrames() {
        return attackFrames;
    }

    public void setAttackFrames(int attackFrames) {
        this.attackFrames = attackFrames;
    }

    public int getAttackMaxFrames() {
        return attackMaxFrames;
    }

    public void setAttackMaxFrames(int attackMaxFrames) {
        this.attackMaxFrames = attackMaxFrames;
    }

    public int getAttackIndex() {
        return attackIndex;
    }

    public void setAttackIndex(int attackIndex) {
        this.attackIndex = attackIndex;
    }

    /**
     * Method to update scythe position
     */
    public void countAttackFrames() {
        if (this.isStartScytheAttack()) {
            this.setAttackFrames(this.getAttackFrames() + 1);
            if (this.getAttackFrames() == this.getAttackMaxFrames()) {
                this.setAttackFrames(0);
                this.setAttackIndex(this.getAttackIndex() + 1);
                if (this.getAttackIndex() >= this.getNumberOfSprites()) {
                    this.setAttackIndex(0);
                    this.setStartScytheAttack(false);
                }
            }
        }
    }

    /**
     * Update scythe position
     */
    public void updateScythe(Directions playerDir) {

        switch (playerDir) {
            case LEFT -> {
                this.setScytheOffsetX(-12);
                this.setScytheOffsetY(-14);
            }
            case RIGHT -> {
                this.setScytheOffsetX(0);
                this.setScytheOffsetY(-42);
            }
            case DOWN -> {
                this.setScytheOffsetX(-14);
                this.setScytheOffsetY(-4);
            }
            case UP -> {
                this.setScytheOffsetX(-53);
                this.setScytheOffsetY(-8);
            }
        }
    }


    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }

    public void render(Graphics g, Directions playerDir, int x, int y) {

        BufferedImage currentScytheSprite = null;
        if (this.isStartScytheAttack()) {
            switch (playerDir) {
                case DOWN -> {
                    currentScytheSprite = this.scytheAttackDown[this.getAttackIndex()];
                }
                case LEFT -> {
                    currentScytheSprite = this.scytheAttackLeft[this.getAttackIndex()];
                }
                case RIGHT -> {
                    currentScytheSprite = this.scytheAttackRight[this.getAttackIndex()];
                }
                case UP -> {
                    currentScytheSprite = this.scytheAttackUp[this.getAttackIndex()];
                }
            }


        }

        g.drawImage(currentScytheSprite, (int) (x + this.getScytheOffsetX() - Camera.x),
                (int) (y + this.getScytheOffsetY() - Camera.y), null);

    }
}
