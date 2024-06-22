package com.dmitryboz.actions;

import com.dmitryboz.Coordinates;
import com.dmitryboz.Map;
import com.dmitryboz.entities.CreaturesNamesGenerator;
import com.dmitryboz.entities.Entity;
import com.dmitryboz.entities.creatures.Rabbit;

import java.util.Random;

public class RespawnHerbivoreAction extends RespawnAction {
    private Random rand = new Random(47);

    public RespawnHerbivoreAction(final Map map) {
        super(map);
    }

    @Override
    public void perform() {
        if (rand.nextInt(15) == 0) {//кролики будут появляться не каждую итерацию, а ~1 из 15
            respawnCycle();
        }
    }

    @Override
    Entity createEntity(Coordinates coord) {
        return new Rabbit(coord, CreaturesNamesGenerator.getName());
    }

    @Override
    protected int getRespawnMaxQuantity() {
        return (int) Math.ceil((double) map.getWidth() * map.getHeight() / 90 / 2);
    }
}