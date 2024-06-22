package com.dmitryboz.entities.creatures;

import com.dmitryboz.Coordinates;
import com.dmitryboz.Map;
import com.dmitryboz.entities.Entity;

public abstract class Creature extends Entity {
    protected final String name;
    protected float HP;
    protected float maxHP;
    protected int movementSpeed;
    protected boolean alreadyMoved = false;

    Creature(Coordinates coordinates, String name, float maxHP, int movementSpeed) {
        super(coordinates);
        this.name = name;
        this.maxHP = maxHP;
        this.HP = maxHP;
        this.movementSpeed = movementSpeed;
    }

    public abstract boolean makeMove(Map map);

    public abstract void eat(Entity food);

    public void takeDamage(float damage) {
        HP -= damage;
        if (HP < 0) HP = 0;
    }

    ;

    public void heal(float healPower) {
        HP += healPower;
        if (HP > maxHP) HP = maxHP;
    }

    ;

    public boolean isAlive() {
        return HP > 0;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", HP='" + HP + '\'' +
                ", " + coordinates +
                '}';
    }
}
