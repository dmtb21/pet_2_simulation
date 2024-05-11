package com.dmitryboz.entities.creatures;

import com.dmitryboz.Coordinates;

public class Wolf extends Predator{
    public Wolf(Coordinates coordinates, String name) {
        super(coordinates, name, 250, 20, 2, 25);
    }

    @Override
    public String getRenderIcon() {
        return "\uD83D\uDC3A";
    }
}
