package com.wellmax.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import com.wellmax.entities.Player;
import com.wellmax.entities.types.Directions;
import com.wellmax.world.Camera;

public class ControlsMenu extends OptionsMenu {

    private BufferedImage[][] controlsSprite;
    private BufferedImage selectingSprite;

    // Players
    private Player playerWalkingUp;
    private Player playerWalkingDown;
    private Player playerWalkingLeft;
    private Player playerWalkingRight;

    private Player playerAttacking;
    private Player playerRunning;
    private Player playerJumping;

    private int [][] cursorArray;
    private int horizontalCursor;
    private int verticalCursor;
    private int maxHorizontalCursor;
    private int maxVerticalCursor;
    private int framesIdle;

    /**
     * SelectStatus = 0->1 : Selected
     * SelectStatus = 1->0 : Assigned
     * SelectStatus = 0: Not selected
     */
    private int selectStatus;
    private int selectX;
    private int selectY;

    public ControlsMenu() {
        this.selectStatus = 0;

        controlsSprite = new BufferedImage[2][4];
        
        playerWalkingUp = new Player(Game.WIDTH/5 + 96, 128, 32, 32);
        playerWalkingDown = new Player(Game.WIDTH/5, 128, 32, 32);
        playerWalkingLeft = new Player(Game.WIDTH/5 + 96*2, 128, 32, 32);
        playerWalkingRight = new Player(Game.WIDTH/5 + 96*3, 128, 32, 32);
    
        playerAttacking = new Player(Game.WIDTH/5, Game.HEIGHT/6 + 196, 32, 32);
        playerJumping = new Player(Game.WIDTH/5 + 96, Game.HEIGHT/6 + 196, 32, 32);
        // playerRunning = new Player(Game.WIDTH/5, Game.HEIGHT/6, 32, 32);
        


        controlsSprite[0][0] = Game.controlSpritesheet.getSprite(32, 128, 32, 32);
        controlsSprite[0][1] = Game.controlSpritesheet.getSprite(448, 96, 32, 32);
        controlsSprite[0][2] = Game.controlSpritesheet.getSprite(416, 96, 32, 32);
        controlsSprite[0][3] = Game.controlSpritesheet.getSprite(0, 128, 32, 32);
        
        controlsSprite[1][0] = Game.controlSpritesheet.getSprite(384, 96, 32, 32);
        controlsSprite[1][1] = Game.controlSpritesheet.getSprite(64, 96, 32, 32);
        controlsSprite[1][2] = Game.controlSpritesheet.getSprite(0, 96, 32, 32);
        controlsSprite[1][3] = Game.controlSpritesheet.getSprite(448, 32, 32, 32);

        selectingSprite = Game.controlSpritesheet.getSprite(64, 128, 32, 32);
        
        this.cursorArray = new int[2][4];
        for(int[] row:this.cursorArray)
            Arrays.fill(row, 0);

        this.cursorArray[0][0] = 1;
        this.maxHorizontalCursor = 3;
        this.maxVerticalCursor = 1;
        this.horizontalCursor = 0;
        this.verticalCursor = 0;

        this.selectX = Game.WIDTH/5 - 2;
        this.selectY = Game.HEIGHT/6 - 2;


    }

    public void moveCursor(int keyCode) {
        if(this.getSelectStatus() ==  0) {
            for(int[] row:this.cursorArray)
            Arrays.fill(row, 0);

            switch(keyCode) {
                case KeyEvent.VK_LEFT ->{
                    if(this.horizontalCursor > 0)
                        this.horizontalCursor--;
                }
                case KeyEvent.VK_RIGHT ->{
                    if(this.horizontalCursor < this.maxHorizontalCursor)
                        this.horizontalCursor++;
                }
                case KeyEvent.VK_UP ->{
                    if(this.verticalCursor > 0)
                        this.verticalCursor--;
                }
                case KeyEvent.VK_DOWN ->{
                    if(this.verticalCursor < this.maxVerticalCursor)
                        this.verticalCursor++;
                }
            }
            this.cursorArray[this.verticalCursor][this.horizontalCursor] = 1;
        }
        
    }

    public void select(int keyEvent) {
        switch(this.getSelectStatus()) {
            case 0 -> {
                this.setSelectStatus(1);
            }
            case 1 -> {
                this.setSelectStatus(0);
                this.updateControls(keyEvent);
            }
        }
    }

    public int getSelectStatus() {
        return this.selectStatus;
    }

    public void setSelectStatus(int selectStatus) {
        this.selectStatus = selectStatus;
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

        // Draw selection
        g.setColor(Color.GREEN);
        g.fillRect(selectX, selectY, 36, 36);

        // Options
        g.setColor(Color.RED);

        int xPosition = Game.WIDTH/5;
        int yPosition = Game.HEIGHT/6;

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 4; j++) {
                g.drawImage(this.controlsSprite[i][j], xPosition +j*96, yPosition + i*256,32, 32,null);
            }
        }

        // Text
        g.setColor(Color.WHITE);
        String text;

        if(this.getSelectStatus() == 0) 
            text = "Press Enter to assign a Key...";    
        else {
            text = "Waiting new Key...";
            g.drawImage(selectingSprite, selectX+2, selectY+2, 32, 32, null);
        }

        Font controlsText = new Font("URW Chancery L Medium Italic", Font.BOLD, 18); 
        g.setFont(controlsText);
        g.drawString(text, (Game.WIDTH - g.getFontMetrics(controlsText).stringWidth(text))/2, Game.HEIGHT - 48);

        // Draw players
        playerWalkingLeft.render(g);
        playerWalkingUp.render(g);
        playerWalkingDown.render(g);
        playerWalkingRight.render(g);

        if(!playerAttacking.scythe.isStartScytheAttack())
            playerAttacking.render(g);
        else {
            playerAttacking.scythe.render(g);
            playerAttacking.renderAttackWithScythe(g);
        }
        playerJumping.render(g);
        // playerRunning.render(g);



    }

    public void update() {

        this.selectX = Game.WIDTH/5 + this.horizontalCursor*96 - 2;
        this.selectY = Game.HEIGHT/6 + this.verticalCursor*256 - 2;

        playerWalkingDown.setMoving(false);
        playerWalkingUp.setMoving(false);
        playerWalkingLeft.setMoving(false);
        playerWalkingRight.setMoving(false);
        playerJumping.setJumping(false);

        playerWalkingUp.setFaceDir(Directions.DOWN);
        playerWalkingLeft.setFaceDir(Directions.DOWN);
        playerWalkingRight.setFaceDir(Directions.DOWN);
        playerAttacking.setFaceDir(Directions.DOWN);
        playerJumping.setZ(0);      
        
        String cursorStr = verticalCursor + "" + horizontalCursor; 
        switch(cursorStr) {
            case "00" -> {
                playerAttacking.scythe.setStartScytheAttack(false);
                playerAttacking.setAttacking(false);
                playerJumping.setTJump(0);
                playerWalkingDown.update();
                playerWalkingDown.setDown(true);
                playerWalkingDown.setY(selectY + 55);
            }
            case "01" -> {
                playerAttacking.scythe.setStartScytheAttack(false);        
                playerJumping.setTJump(0);
                playerWalkingUp.update();
                playerWalkingUp.setUp(true);
                playerWalkingUp.setY(selectY + 55);        
            }
            case "02" -> {
                playerAttacking.scythe.setStartScytheAttack(false);        
                playerJumping.setTJump(0);
                playerWalkingLeft.update();
                playerWalkingLeft.setLeft(true);
                playerWalkingLeft.setX(selectX);   
            }
            case "03" -> {
                playerAttacking.scythe.setStartScytheAttack(false);        
                playerJumping.setTJump(0);
                playerWalkingRight.update();
                playerWalkingRight.setRight(true);
                playerWalkingRight.setX(selectX);   
            }
            case "10" -> {
                playerJumping.setTJump(0);
                if(framesIdle == 75) {
                    playerAttacking.attack();
                    framesIdle = 0;
                }
                playerAttacking.update();
                playerAttacking.scythe.countAttackFrames();
                playerAttacking.scythe.update();
                framesIdle++;
            }
            case "11" -> {
                playerAttacking.scythe.setStartScytheAttack(false);
                playerJumping.setJumping(true);
                playerJumping.update();
            }
            // case "12" -> Game.controls.setRun(keyEvent);
            // case "13" -> Game.controls.setPause(keyEvent);
        }            
        
        Camera.x = 0;
        Camera.y = 0;

    }


    private void updateControls(int keyEvent) {
        int newX = 0;
        int newY = 0;
        switch(keyEvent) {
            case KeyEvent.VK_QUOTE -> {newX = 0; newY = 0;}
            case KeyEvent.VK_1 -> {newX = 32; newY = 0;}
            case KeyEvent.VK_2 -> {newX = 64; newY = 0;}
            case KeyEvent.VK_3 -> {newX = 96; newY = 0;}
            case KeyEvent.VK_4 -> {newX = 128; newY = 0;}
            case KeyEvent.VK_5 -> {newX = 160; newY = 0;}
            case KeyEvent.VK_6 -> {newX = 192; newY = 0;}
            case KeyEvent.VK_7 -> {newX = 224; newY = 0;}
            case KeyEvent.VK_8 -> {newX = 256; newY = 0;}
            case KeyEvent.VK_9 -> {newX = 288; newY = 0;}
            case KeyEvent.VK_0 -> {newX = 320; newY = 0;}
            case KeyEvent.VK_MINUS -> {newX = 352; newY = 0;}
            case KeyEvent.VK_EQUALS -> {newX = 384; newY = 0;}
            case KeyEvent.VK_CLOSE_BRACKET -> {newX = 416; newY = 0;}
            case KeyEvent.VK_BACK_SPACE -> {newX = 448; newY = 0;}
            case KeyEvent.VK_Q -> {newX = 32; newY = 32;}
            case KeyEvent.VK_W -> {newX = 64; newY = 32;}
            case KeyEvent.VK_E -> {newX = 96; newY = 32;}
            case KeyEvent.VK_R -> {newX = 128; newY = 32;}
            case KeyEvent.VK_T -> {newX = 160; newY = 32;}
            case KeyEvent.VK_Y -> {newX = 192; newY = 32;}
            case KeyEvent.VK_U -> {newX = 224; newY = 32;}
            case KeyEvent.VK_I -> {newX = 256; newY = 32;}
            case KeyEvent.VK_O -> {newX = 288; newY = 32;}
            case KeyEvent.VK_P -> {newX = 320; newY = 32;}
            case KeyEvent.VK_OPEN_BRACKET -> {newX = 384; newY = 32;}
            case KeyEvent.VK_ENTER -> {newX = 416; newY = 32;}
            case KeyEvent.VK_ESCAPE -> {newX = 448; newY = 32;}
            case KeyEvent.VK_A -> {newX = 32; newY = 64;}
            case KeyEvent.VK_S -> {newX = 64; newY = 64;}
            case KeyEvent.VK_D -> {newX = 96; newY = 64;}
            case KeyEvent.VK_F -> {newX = 128; newY = 64;}
            case KeyEvent.VK_G -> {newX = 160; newY = 64;}
            case KeyEvent.VK_H -> {newX = 192; newY = 64;}
            case KeyEvent.VK_J -> {newX = 224; newY = 64;}
            case KeyEvent.VK_K -> {newX = 256; newY = 64;}
            case KeyEvent.VK_L -> {newX = 288; newY = 64;}
            case KeyEvent.VK_DEAD_CEDILLA -> {newX = 320; newY = 64;}
            case KeyEvent.VK_DEAD_TILDE -> {newX = 352; newY = 64;}
            case KeyEvent.VK_SLASH -> {newX = 384; newY = 64;}
            case KeyEvent.VK_CONTROL -> {newX = 416; newY = 64;}
            case KeyEvent.VK_ALT -> {newX = 448; newY = 64;}
            case KeyEvent.VK_SHIFT -> {newX = 0; newY = 96;}
            case KeyEvent.VK_BACK_SLASH -> {newX = 32; newY = 96;}
            case KeyEvent.VK_Z -> {newX = 64; newY = 96;}
            case KeyEvent.VK_X -> {newX = 96; newY = 96;}
            case KeyEvent.VK_C -> {newX = 128; newY = 96;}
            case KeyEvent.VK_V -> {newX = 160; newY = 96;}
            case KeyEvent.VK_B -> {newX = 192; newY = 96;}
            case KeyEvent.VK_N -> {newX = 224; newY = 96;}
            case KeyEvent.VK_M -> {newX = 256; newY = 96;}
            case KeyEvent.VK_COMMA -> {newX = 288; newY = 96;}
            case KeyEvent.VK_PERIOD -> {newX = 320; newY = 96;}
            case KeyEvent.VK_SEMICOLON -> {newX = 352; newY = 96;}
            case KeyEvent.VK_SPACE -> {newX = 384; newY = 96;}
            case KeyEvent.VK_LEFT -> {newX = 416; newY = 96;}
            case KeyEvent.VK_UP -> {newX = 448; newY = 96;}
            case KeyEvent.VK_RIGHT -> {newX = 0; newY = 128;}
            case KeyEvent.VK_DOWN -> {newX = 32; newY = 128;}
        }

        String cursorStr = verticalCursor + "" + horizontalCursor; 
        switch(cursorStr) {
            case "00" -> Game.controls.setWalkDown(keyEvent);
            case "01" -> Game.controls.setWalkUp(keyEvent);
            case "02" -> Game.controls.setWalkLeft(keyEvent);
            case "03" -> Game.controls.setWalkRight(keyEvent);
            case "10" -> Game.controls.setAttack(keyEvent);
            case "11" -> Game.controls.setJump(keyEvent);
            case "12" -> Game.controls.setRun(keyEvent);
            case "13" -> Game.controls.setPause(keyEvent);
        }
        
        this.controlsSprite[verticalCursor][horizontalCursor] = Game.controlSpritesheet.getSprite(newX, newY, 32, 32);
    }
}