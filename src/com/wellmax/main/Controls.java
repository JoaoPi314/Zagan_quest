package com.wellmax.main;

import java.awt.event.KeyEvent;

public class Controls {
    private int jump;

    private int walkRight;
    private int walkLeft;
    private int walkUp;
    private int walkDown;

    private int run;

    private int attack;

    private int pause;

    private int numberOfControls;



    public Controls(){

        this.setNumberOfControls(8);
        this.setJump(KeyEvent.VK_Z);
        
        this.setWalkRight(KeyEvent.VK_RIGHT);
        this.setWalkLeft(KeyEvent.VK_LEFT);
        this.setWalkUp(KeyEvent.VK_UP);
        this.setWalkDown(KeyEvent.VK_DOWN);

        this.setPause(KeyEvent.VK_ESCAPE);

        this.setRun(KeyEvent.VK_SHIFT);
        
        this.setAttack(KeyEvent.VK_SPACE);

    }

    public int getJump() {
        return this.jump;
    }

    public void setJump(int jump) {
        this.jump = jump;
    }

    public int getWalkRight() {
        return this.walkRight;
    }

    public void setWalkRight(int walkRight) {
        this.walkRight = walkRight;
    }

    public int getWalkLeft() {
        return this.walkLeft;
    }

    public void setWalkLeft(int walkLeft) {
        this.walkLeft = walkLeft;
    }

    public int getWalkUp() {
        return this.walkUp;
    }

    public void setWalkUp(int walkUp) {
        this.walkUp = walkUp;
    }

    public int getWalkDown() {
        return this.walkDown;
    }

    public void setWalkDown(int walkDown) {
        this.walkDown = walkDown;
    }

    public int getRun() {
        return this.run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }


    public int getPause() {
        return this.pause;
    }

    public void setPause(int pause) {
        this.pause = pause;
    }


    public int getNumberOfControls() {
        return this.numberOfControls;
    }

    public void setNumberOfControls(int numberOfControls) {
        this.numberOfControls = numberOfControls;
    }
}
