package com.dmitryboz.actions;

import com.dmitryboz.Map;

public abstract class Action {

    final Map map;

    public Action(Map map) {
        this.map = map;
    }

    public abstract void perform();
}
