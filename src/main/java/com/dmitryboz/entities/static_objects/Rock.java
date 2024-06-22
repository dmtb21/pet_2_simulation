package com.dmitryboz.entities.static_objects;

import com.dmitryboz.Coordinates;

public class Rock extends StaticObject {
    public Rock(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String getRenderIcon() {
        return "\uD83D\uDDFF";
    }
}
