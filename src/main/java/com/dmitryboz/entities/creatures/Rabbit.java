package com.dmitryboz.entities.creatures;

import com.dmitryboz.Coordinates;

public class Rabbit extends Herbivore{

    public Rabbit(Coordinates coordinates, String name) {
        super(coordinates, name, 50, 20);
    }

    @Override
    public String getRenderIcon() {
        return "\uD83D\uDC30";
    }

}
