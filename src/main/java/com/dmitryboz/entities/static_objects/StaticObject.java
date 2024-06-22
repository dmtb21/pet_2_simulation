package com.dmitryboz.entities.static_objects;

import com.dmitryboz.Coordinates;
import com.dmitryboz.entities.Entity;

public abstract class StaticObject extends Entity {

    public StaticObject(Coordinates coordinates) {
        super(coordinates);
    }

    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "id=" + id +
                ", " + coordinates +
                '}';
    }
}
