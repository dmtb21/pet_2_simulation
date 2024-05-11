package com.dmitryboz.entities;

import com.dmitryboz.Coordinates;

public abstract class Entity {
    protected Coordinates coordinates;
    public abstract String getRenderIcon();


    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
