package com.dmitryboz.entities.creatures;

import com.dmitryboz.Coordinates;
import com.dmitryboz.entities.EdibleToPredators;
import com.dmitryboz.entities.Entity;

public abstract class Predator extends Creature{
    protected int maxAttackDistance;
    protected float damage;
    public Predator(Coordinates coordinates, String name, float maxHP, int movementSpeed, int maxAttackDistance, float damage) {
        super(coordinates, name, maxHP, movementSpeed);
        this.maxAttackDistance=maxAttackDistance;
        this.damage=damage;
    }


    public void attack(Herbivore creature){
        creature.takeDamage(damage);
    }

    @Override
    public void eat(Entity food){
            if (food instanceof EdibleToPredators) {
                if(((Creature)food).isAlive()){
                    throw new RuntimeException("Attemption to eat alive creature"+food.getClass());
                }
                else {
                    heal(((EdibleToPredators)food).getHealPower());
                }
            }
            else {
                throw new RuntimeException("Attemption to eat "+food.getClass());
            }
    }

    @Override
    public void makeMove() {

    }
}
