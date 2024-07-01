package com.dmitryboz.actions;

import com.dmitryboz.Coordinates;
import com.dmitryboz.SimMap;
import com.dmitryboz.entities.Entity;

abstract class RespawnAction extends Action {

    public RespawnAction(final SimMap map) {
        super(map);
    }

    @Override
    public void perform() {
        respawnCycle();
    }

    protected void respawnCycle() {
        int emptyCellsCnt = map.getEmptyCellsCount();
        int mapSize = map.getWidth() * map.getHeight();
        if (emptyCellsCnt > mapSize / 3) {
            int quantityOfNewGrass = getRespawnMaxQuantity();
            if (quantityOfNewGrass > emptyCellsCnt / 3) {
                quantityOfNewGrass = emptyCellsCnt / 3;
            }

            Coordinates targetCoord = null;
            if (quantityOfNewGrass > 0) {
                for (int i = 0; i < quantityOfNewGrass; i++) {
                    targetCoord = map.getRandomEmptyCoord();
                    map.setEntity(targetCoord, createEntity(targetCoord));
                }
            }
        }
    }

    abstract Entity createEntity(Coordinates coord);

    protected int getRespawnMaxQuantity() {
        return (int) Math.ceil((double) map.getWidth() * map.getHeight() / 90);
    }

    ;
}
