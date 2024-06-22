package com.dmitryboz.entities.static_objects;

import com.dmitryboz.Coordinates;
import com.dmitryboz.entities.EdibleToHerbivores;

public class Grass extends StaticObject implements EdibleToHerbivores {
    private float healPower;

    public Grass(Coordinates coordinates) {
        super(coordinates);
        this.healPower = 15;
    }

    public Grass(Coordinates coordinates, float healPower) {
        super(coordinates);
        this.healPower = healPower;
    }

    public float getHealPower() {
        return healPower;
    }

    ;

    @Override
    public String getRenderIcon() {
        return "\uD83C\uDF31";
    }

}
