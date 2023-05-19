package com.wellmax.main.types;

import java.awt.Graphics;

public interface GameLogic {
	void update();

	void render(Graphics g);

    double getY();

    int getHeight();
}

