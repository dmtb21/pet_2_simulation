package com.dmitryboz.entities.creatures;

import com.dmitryboz.Coordinates;
import com.dmitryboz.entities.EdibleToHerbivores;
import com.dmitryboz.entities.EdibleToPredators;
import com.dmitryboz.entities.Entity;

public abstract class Herbivore extends Creature implements EdibleToPredators {
    protected final float healPower;
    public Herbivore(Coordinates coordinates, String name, float maxHP, int movementSpeed) {
        super(coordinates, name, maxHP, movementSpeed);
        healPower=maxHP/2;
    }

    @Override
    public void makeMove() {

    }

    @Override
    public void eat(Entity food) {
        if (food instanceof EdibleToHerbivores) {
            heal(((EdibleToHerbivores)food).getHealPower());
        }
        else {
            throw new RuntimeException("Attemption to eat "+food.getClass());
        }
    }

    /**
     * @return возвращает кол-во hp, которое может приобрести хищник если съест данное травоядное
     */
    @Override
    public float getHealPower() {
        return healPower;
    }
}
