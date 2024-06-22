package com.dmitryboz.entities;

import com.dmitryboz.Coordinates;

public abstract class Entity {
    protected Coordinates coordinates;
    protected static int entitiesCounter = 0;
    public final int id;

    public abstract String getRenderIcon();

    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.id = ++entitiesCounter;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
