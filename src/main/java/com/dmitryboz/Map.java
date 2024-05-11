package com.dmitryboz;

import com.dmitryboz.entities.*;
import com.dmitryboz.entities.creatures.*;
import com.dmitryboz.entities.static_objects.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Map {
    private final int width;
    private final int height;
    private HashMap<Coordinates, Entity> entities;

    /**
     * Hashset для оптимизации выборки случайной пустой ячейки
     */
    private HashSet<Coordinates> emptyCells;
    private HashMap<String, Integer> entitiesCounter = new HashMap<>();

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        entities = new HashMap<>(width * height, 2);
        //loadfactor 2, так как более чем на 100% HM заполнена не будет и увеличение её не потребуется,
        //размер HM всегда будет соответствовать initialCapacity
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public HashMap<Coordinates, Entity> getEntities() {
        return entities;
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        entities.put(coordinates, entity);
        entitiesCounter.merge(entity.getClass().getSimpleName(), 1, Integer::sum);
        emptyCells.remove(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public void removeEntity(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(null);
        entities.put(coordinates, null);
        entitiesCounter.put(entity.getClass().getSimpleName(), entitiesCounter.get(entity.getClass().getSimpleName()) - 1);
        emptyCells.add(coordinates);
    }

    /**
     * Использовать только для первичной установки пустых ячеек при инииализаци карты
     *
     * @param coordinates
     */
    public void setEmptyCell(Coordinates coordinates) {
        emptyCells.add(coordinates);
    }

    public boolean isEmptyСell(Coordinates coordinates) {
        return entities.get(coordinates) == null;
    }


    public boolean hasEmptyCells() {
        return !emptyCells.isEmpty();
    }

    public Coordinates getRandomEmptyCoord() throws IllegalStateException {
        if (hasEmptyCells()) {
            Random rand = new Random(47);
            int targetElPos = rand.nextInt(emptyCells.size());
            int i = 0;

            for (Coordinates coord : emptyCells) {
                if (i == targetElPos)
                    return coord;
                i++;
            }
        }
        throw new IllegalStateException("Has no empty cell");
    }


    public HashMap<String, Integer> getPopulationData() {
        return new HashMap<String, Integer>(entitiesCounter);
    }

}
