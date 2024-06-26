package com.dmitryboz.actions;

import com.dmitryboz.Coordinates;
import com.dmitryboz.SimMap;
import com.dmitryboz.entities.Entity;
import com.dmitryboz.entities.static_objects.Grass;

public class GrowGrassAction extends RespawnAction {
    public GrowGrassAction(final SimMap map) {
        super(map);
    }

    @Override
    public void perform() {
        respawnCycle();
    }

    @Override
    Entity createEntity(Coordinates coord) {
        return new Grass(coord);
    }
}
