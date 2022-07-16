package com.wellmax.world.types.strategy;

import com.wellmax.world.types.PixelType;

public interface PixelTypeStrategy {

    void createTile();
    boolean shouldCreate(PixelType pixelType);

}

