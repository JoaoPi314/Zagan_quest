package com.wellmax.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PauseMenu {
  	//---------------------------- Attributes ----------------------------------//

    protected String[] options = {"Resume", "Save Game", "Load Game", "Options", "Exit to menu"};
    protected int maxCursorPosition = options.length - 1;

    private int cursor = 0;
    private int cursorY;

    
    //---------------------------- Methods ----------------------------------//

    public void upCursor() {
        if(cursor > 0)
            cursor--;
    }

    public void downCursor() {
        if(cursor < maxCursorPosition)
            cursor++;
    }

    /**
     * Method to update frame at frame
     */
    public void update() {
        int baseCursor = (int)(Game.HEIGHT*Game.SCALE*0.458333333);
        switch(cursor) {
            case 0 -> cursorY = baseCursor;
            case 1 -> cursorY = baseCursor + 50;
            case 2 -> cursorY = baseCursor + 100;
            case 3 -> cursorY = baseCursor + 150;
            case 4 -> cursorY = baseCursor + 200;
            default -> {
            }
        }
    }

    /**
     *  Method to render frame
     */
    public void render(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0x00, 0x00, 0x00, 150));
        g2.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);

        // Draw Options
        g.setColor(Color.WHITE);
        Font menu_font = new Font("URW Chancery L Medium Italic", Font.BOLD, 36); 
        g.setFont(menu_font);
        int i = Game.HEIGHT*Game.SCALE/3;
        for (String string : options) {
            g.drawString(string, (Game.WIDTH*Game.SCALE - g.getFontMetrics(menu_font).stringWidth(string))/2, Game.HEIGHT*Game.SCALE/8 + i);
            i += 50;
        }

        // Draw cursor
        g.drawString(">", Game.WIDTH*Game.SCALE/4+50, cursorY);
    }

    public int getCursor() {
        return this.cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

}
