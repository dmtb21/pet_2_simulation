package com.dmitryboz.entities.static_objects;

import com.dmitryboz.Coordinates;

public class Tree extends StaticObject {
    public Tree(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String getRenderIcon() {
        return "\uD83C\uDF33";
    }
}
