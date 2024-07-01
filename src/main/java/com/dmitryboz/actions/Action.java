package com.dmitryboz.actions;

import com.dmitryboz.SimMap;

public abstract class Action {
    final SimMap map;
    public Action(SimMap map) {
        this.map = map;
    }
    public abstract void perform();
}
