package com.melqjpgames.world;

public class Camera {

	public static int x;
	public static int y;
	
	public Camera() {
		x = 0;
		y = 0;
	}

	public static int clamp(int current, int min, int max) {
		if(current < min) {
			current = min;
		}
		if(current > max) {
			current = max;
		}
		return current;
	}
	
}
