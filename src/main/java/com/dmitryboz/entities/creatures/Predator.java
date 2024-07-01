package com.dmitryboz.entities.creatures;

import com.dmitryboz.Coordinates;
import com.dmitryboz.SimMap;
import com.dmitryboz.PathFinder;
import com.dmitryboz.entities.EdibleToPredators;
import com.dmitryboz.entities.Entity;

import java.util.List;

public abstract class Predator extends Creature {
    protected int maxAttackDistance;
    protected float damage;

    public Predator(Coordinates coordinates, String name, float maxHP, int movementSpeed, int maxAttackDistance, float damage) {
        super(coordinates, name, maxHP, movementSpeed);
        this.maxAttackDistance = maxAttackDistance;
        this.damage = damage;
    }

    @Override
    public boolean makeMove(SimMap map) {
        List<Coordinates> path = PathFinder.getPath(map, this.coordinates, Herbivore.class);
        if (!path.isEmpty()) {//путь найден
            Coordinates targetCoordinates = path.get(path.size() - 1);

            if (this.coordinates.isSibling(targetCoordinates, this.maxAttackDistance)) {//цель достингута, находимся на расстоянии атаки
                Herbivore food = (Herbivore) map.getEntity(targetCoordinates);

                if (food.isAlive()) {//пока травоядное не повержено - атакуем
                    attack(food);
                } else {
                    eat(food);
                    map.removeEntity(food);
                    map.moveEntity(this, targetCoordinates);//ставим хищника на ячейку, где была пища
                }
            } else {//выполняем движение к цели
                map.moveEntity(this, path.get(Math.min(movementSpeed - 1, path.size() - 2)));
            }
            return true;
        }
        return false;
    }

    public void attack(Creature creature) {
        creature.takeDamage(damage);
    }

    @Override
    public void eat(Entity food) {
        if (food instanceof EdibleToPredators) {
            if (((Creature) food).isAlive()) {
                throw new RuntimeException("Attemption to eat alive creature" + food.getClass());
            } else {
                heal(((EdibleToPredators) food).getHealPower());
            }
        } else {
            throw new RuntimeException("Attemption to eat " + food.getClass());
        }
    }

}
