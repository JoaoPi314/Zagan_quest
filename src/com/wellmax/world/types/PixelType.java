package com.wellmax.world.types;

public enum PixelType {
    // Wall tiles
    WALL_FACING_DOWN(0xFF9D8686),
    WALL_FACING_RIGHT(0xFF786565),
    WALL_MIDDLE(0xFF665252),
    WALL_FACING_LEFT(0xFF5D4848),
    WALL_FACING_UP(0xFF3A3131),
    WALL_CORNER_DOWN_LEFT(0xFF8A7575),
    WALL_CORNER_DOWN_RIGHT(0xFFB19898),
    WALL_CORNER_TOP_RIGHT(0xFF4A3B3B),
    WALL_CORNER_TOP_LEFT(0xFF2D2C2C),
    // Stone-floor tiles
    STONE_FLOOR_MIDDLE(0xFF999999),
    STONE_FLOOR_FACING_UP(0xFF333333),
    STONE_FLOOR_FACING_RIGHT(0xFFB8B8B8),
    STONE_FLOOR_FACING_DOWN(0xFFDEDCDC),
    STONE_FLOOR_FACING_LEFT(0xFF777777),
    STONE_FLOOR_CORNER_TOP_LEFT(0xFF181717),
    STONE_FLOOR_CORNER_TOP_RIGHT(0xFF555555),
    STONE_FLOOR_CORNER_DOWN_LEFT(0xFFCECECE),
    STONE_FLOOR_CORNER_DOWN_RIGHT(0xFFF7F5F5),
    // Path tiles
    PATH_MIDDLE(0xFFCDA664),
    PATH_FACING_UP(0xFFA58259),
    PATH_FACING_DOWN(0xFFD1B57E),
    PATH_FACING_LEFT(0xFFDCA548),
    PATH_FACING_RIGHT(0xFFF1BA5C),
    PATH_CORNER_TOP_RIGHT(0xFFB39471),
    PATH_CORNER_TOP_LEFT(0xFFAF7B40),
    PATH_CORNER_DOWN_RIGHT(0xFFDEC47E),
    PATH_CORNER_DOWN_LEFT(0xFFC4A97A),
    // Scenario tiles
    ROCK(0xFF0E0404),
    TREE(0xFF0D260E),
    STATUE(0xFFEE03FF),
    // Entities
    HEALTH_POTION(0xFFF4FF00),
    FIREBALL(0xFFFF8200),
    ENEMY(0xFFFF0000),
    PLAYER(0xFF0000FF);


    private final int color;

    private PixelType(int color) {
        this.color = color;
    }

    public final int getColor() {
        return color;
    }

    public static PixelType fromColor(int color) {
        for (PixelType pixelType : values()) {
            if (pixelType.getColor() == color) {
                return pixelType;
            }
        }
        return null;
    }


}


