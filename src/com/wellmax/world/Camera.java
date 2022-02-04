package com.wellmax.world;

/**
 * The camera follows the player. It is a constant class
 * that cannot be instantiated
 * @author joao.gomes
 *
 */
public final class Camera {

	
	/**
	 * Camera x position
	 */
	public static int x;
	/**
	 * Camear y position
	 */
	public static int y;
	
	/**
	 * Private Constructor, only to indicates that
	 * class cannot be instantiated
	 */
	private Camera() {
		
	}

	/**
	 * The clamp method will calculate when the camera should be
	 * @param current Player position (Should aways be at center)
	 * @param min Minimal camera position (Avoid camera go out of map)
	 * @param max Max camera position (Avoid camera go out of map)
	 * @return the current camera coordinate (x or y)
	 */
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
