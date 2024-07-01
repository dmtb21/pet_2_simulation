package com.dmitryboz.entities.creatures;

import com.dmitryboz.Coordinates;
import com.dmitryboz.SimMap;
import com.dmitryboz.PathFinder;
import com.dmitryboz.entities.EdibleToHerbivores;
import com.dmitryboz.entities.EdibleToPredators;
import com.dmitryboz.entities.Entity;

import java.util.List;

public abstract class Herbivore extends Creature implements EdibleToPredators {
    protected final float healPower;

    public Herbivore(Coordinates coordinates, String name, float maxHP, int movementSpeed) {
        super(coordinates, name, maxHP, movementSpeed);
        healPower = maxHP / 2;
    }

    @Override
    public boolean makeMove(SimMap map) {
        List<Coordinates> path = PathFinder.getPath(map, this.coordinates, EdibleToHerbivores.class);
        if (!path.isEmpty()) {//путь найден
            Coordinates targetCoordinates = path.get(path.size() - 1);
            if (this.coordinates.isSibling(targetCoordinates)) {//цель достингута, находимся в соседней клетке к цели
                Entity food = map.getEntity(targetCoordinates);
                eat(food);
                map.removeEntity(food);
                map.moveEntity(this, targetCoordinates);//ставим травоядное на ячейку, где была пища
            } else {//выполняем движение к цели
                map.moveEntity(this, path.get(Math.min(movementSpeed - 1, path.size() - 2)));
            }
            return true;
        }
        return false;
    }

    @Override
    public void eat(Entity food) {
        if (food instanceof EdibleToHerbivores) {
            heal(((EdibleToHerbivores) food).getHealPower());
        } else {
            throw new RuntimeException("Attemption to eat " + food.getClass());
        }
    }

    /**
     * @return возвращает кол-во hp, которое может приобрести хищник если съест данное травоядное
     */
    @Override
    public float getHealPower() {
        return healPower;
    }
}
