package com.wellmax.entities.types;

import com.wellmax.main.Game;

/**
 * Util class
 * @author joao.gomes
 *
 */
public class Utils {
	/**
	 * Method to pick a random element from enum
	 * @param <T> Enum type
	 * @param clazz Enum to return value
	 * @return enum element
	 */
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = Game.rand.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
