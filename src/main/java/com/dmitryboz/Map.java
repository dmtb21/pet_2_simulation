package com.dmitryboz;

import com.dmitryboz.entities.Entity;
import com.dmitryboz.entities.creatures.Creature;

import java.util.*;

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

        //loadfactor 2, так как более чем на 100% HM заполнена не будет и увеличение её не потребуется,
        //размер HM всегда будет соответствовать initialCapacity
        entities = new HashMap<>(width * height, 2);

        emptyCells = new HashSet<>(width * height, 2);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<Creature> getCreaturesList() {
        List<Creature> creaturesList = new ArrayList<Creature>();

        //Создадим независимый от hashmap список Creatures
        for (Entity entity : entities.values()) {
            if (entity instanceof Creature) {
                creaturesList.add((Creature) entity);
            }
        }
        return creaturesList;
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        entities.put(coordinates, entity);
        entitiesCounter.merge(entity.getClass().getSimpleName(), 1, Integer::sum);
        if (emptyCells.contains(coordinates)) {
            emptyCells.remove(coordinates);
        }
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public boolean containsCoordinates(Coordinates coord) {
        return entities.containsKey(coord);
    }

    public void removeEntity(Entity entity) {
        Coordinates coordinates = entity.getCoordinates();
        if (entities.get(coordinates) != entity) {
            throw new RuntimeException("Ошибка. Попытка удалить объект, которого нет");
        }
        entity.setCoordinates(null);
        entities.put(coordinates, null);
        entitiesCounter.put(entity.getClass().getSimpleName(), entitiesCounter.get(entity.getClass().getSimpleName()) - 1);
        emptyCells.add(coordinates);
    }

    public void moveEntity(Entity entity, Coordinates coordTo) {
        Coordinates coordFrom = entity.getCoordinates();

        if (entities.get(coordFrom) != entity) {
            throw new RuntimeException("Ошибка. Попытка передвинуть объект, которого нет");
        }
        if (entities.get(coordTo) != null) {
            throw new RuntimeException("Ошибка. Попытка передвинуть объект на занятую ячейку");
        }

        entity.setCoordinates(coordTo);
        entities.put(coordFrom, null);
        entities.put(coordTo, entity);
        emptyCells.remove(coordTo);
        emptyCells.add(coordFrom);
    }

    /**
     * Использовать только для первичной установки пустых ячеек при инииализаци карты
     *
     * @param coordinates
     */
    public void setEmptyCell(Coordinates coordinates) {
        entities.put(coordinates, null);
        emptyCells.add(coordinates);
    }

    public boolean isEmptyСell(Coordinates coordinates) {
        return entities.get(coordinates) == null;
    }

    public boolean hasEmptyCells() {
        return !emptyCells.isEmpty();
    }

    public int getEmptyCellsCount() {
        return emptyCells.size();
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
