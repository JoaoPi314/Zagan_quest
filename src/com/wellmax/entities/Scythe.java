package com.wellmax.entities;

import com.wellmax.entities.types.Directions;
import com.wellmax.main.Game;
import com.wellmax.world.Camera;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


/**
 * THe scythe class will compute all scythe logic
 * @author joao.gomes
 */
public class Scythe extends Entity {

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

    private Directions scytheDir;

    private double damage;
    private int knockBackDealt;


    //---------------------------- Methods ----------------------------------//

    public Scythe(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.setNumberOfSprites(6);
        this.setAttackMaxFrames(5);
        this.setMask(0, 0, 0, 0);
        this.setDamage(2);
        this.setKnockBackDealt(2);

        this.scytheAttackDown = new BufferedImage[this.getNumberOfSprites()];
        this.scytheAttackLeft = new BufferedImage[this.getNumberOfSprites()];
        this.scytheAttackRight = new BufferedImage[this.getNumberOfSprites()];
        this.scytheAttackUp = new BufferedImage[this.getNumberOfSprites()];

        for (int i = 0; i < this.getNumberOfSprites(); i++) {
            this.scytheAttackDown[i] = Game.spritesheet.getSprite(224, i * 48, this.getWidth(), 48);
            this.scytheAttackLeft[i] = Game.spritesheet.getSprite(320 + i * 48, 0, 48, this.getWidth());
            this.scytheAttackRight[i] = Game.spritesheet.getSprite(320 + i * 48, 96, 48, this.getWidth());
            this.scytheAttackUp[i] = Game.spritesheet.getSprite(608, i * 48, this.getWidth(), 48);
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

    public Directions getScytheDir() {
        return scytheDir;
    }

    public void setScytheDir(Directions scytheDir) {
        this.scytheDir = scytheDir;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public int getKnockBackDealt() {
        return knockBackDealt;
    }

    public void setKnockBackDealt(int knockBackDealt) {
        this.knockBackDealt = knockBackDealt;
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
    public void updateScythe(Directions playerDir, int x, int y) {

        this.setScytheDir(playerDir);

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
        this.setX(x + this.getScytheOffsetX());
        this.setY(y + this.getScytheOffsetY());

    }


    @Override
    public void update() {
        if (this.isStartScytheAttack() && (this.getAttackIndex() == 3)) {
            switch (this.getScytheDir()) {
                case DOWN -> {
                    this.setMask(3, 17, 54, 31);
                }
                case RIGHT -> {
                    this.setMask(16, 41, 26, 40);
                }
                case UP -> {
                    this.setMask(51, 0, 39, 26);
                }
                case LEFT -> {
                    this.setMask(0, 7, 31, 44);
                }
            }
        } else {
            this.setMask(0, 0, 0, 0);
        }
    }

    @Override
    public void render(Graphics g) {

        BufferedImage currentScytheSprite = null;
        if (this.isStartScytheAttack()) {
            switch (this.getScytheDir()) {
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

//        g.drawRect((int) (this.getX() + this.getMaskX()), (int) (this.getY() + this.getMaskY()), this.getMaskWidth(), this.getMaskHeight());
        g.drawImage(currentScytheSprite, (int) (this.getX() - Camera.x),
                (int) (this.getY() - Camera.y), null);

    }

}