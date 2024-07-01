package com.dmitryboz;

import java.util.*;

import static com.dmitryboz.Directions.*;

public class PathFinder {
    public static List<Coordinates> getPath(SimMap map, Coordinates srcCoord, Class targetElClass) {
        int mapWidth = map.getWidth();
        int mapHeight = map.getHeight();
        Queue<Coordinates> queue = new LinkedList<Coordinates>();
        Map<Coordinates, Coordinates> marked = new HashMap<Coordinates, Coordinates>(mapWidth * mapHeight, 2);
        boolean isTargetFound = false;
        Coordinates currentCheckPoint = srcCoord;
        Coordinates siblingCoordinates;
        List<Coordinates> path = new ArrayList<Coordinates>();
        Directions nextLvlDirection = TOP;
        boolean allSiblingsChecked = false;

        do {
            if (allSiblingsChecked) {//если проверили все - меняем ячейку
                currentCheckPoint = queue.poll();
                allSiblingsChecked = false;//сбрасываем флаг что всё проверено
            }
            siblingCoordinates = getNextCoord(currentCheckPoint, nextLvlDirection);

            if (map.containsCoordinates(siblingCoordinates) && !marked.containsKey(siblingCoordinates)) {//такая точка есть на карте и ещё не помечена
                if (map.getEntity(siblingCoordinates) != null) {//ячейка не пуста, пройти в неё нельзя
                    if (targetElClass.isInstance(map.getEntity(siblingCoordinates))) {//является искомым объектом
                        isTargetFound = true;
                        marked.put(siblingCoordinates, currentCheckPoint);
                        currentCheckPoint = siblingCoordinates;
                    }
                } else {//ячейка пуста. Помечаем и добавляем в очередь
                    marked.put(siblingCoordinates, currentCheckPoint);
                    queue.add(siblingCoordinates);
                }
            }

            switch (nextLvlDirection) {
                case TOP:
                    nextLvlDirection = RIGHT;
                    break;
                case RIGHT:
                    nextLvlDirection = BOTTOM;
                    break;
                case BOTTOM:
                    nextLvlDirection = LEFT;
                    break;
                case LEFT:
                    nextLvlDirection = TOP;
                    allSiblingsChecked = true;
                    break;
            }
        } while (!isTargetFound && (!queue.isEmpty() || !allSiblingsChecked));

        if (!isTargetFound) {
            return path;//путь не найден, возвращаем пустой путь
        }
        while (currentCheckPoint != srcCoord) {
            path.add(currentCheckPoint);
            currentCheckPoint = marked.get(currentCheckPoint);
        }
        Collections.reverse(path);//инвертируем обратный путь

        return path;
    }

    private static Coordinates getNextCoord(Coordinates current, Directions direction) {
        switch (direction) {
            case TOP:
                return new Coordinates(current.getX(), current.getY() - 1);
            case BOTTOM:
                return new Coordinates(current.getX(), current.getY() + 1);
            case LEFT:
                return new Coordinates(current.getX() - 1, current.getY());
            case RIGHT:
                return new Coordinates(current.getX() + 1, current.getY());
        }
        throw new RuntimeException("Invalid direction");
    }
}