package com.dmitryboz;

import java.util.*;

import static com.dmitryboz.Directions.*;

public class PathFinder {
    public static List<Coordinates> getPath(Map map, Coordinates srcCoord, Class targetElClass) {

        int mapWidth = map.getWidth();
        int mapHeight = map.getHeight();

        Queue<Coordinates> queue = new LinkedList<Coordinates>();
        //Помеченные вершины в качестве ключа и точки откуда пришел в качестве значения
        HashMap<Coordinates, Coordinates> marked = new HashMap<Coordinates, Coordinates>(mapWidth * mapHeight, 2);
        boolean isTargetFound = false;

        Coordinates currentCheckPoint = srcCoord;
        Coordinates siblingCoordinates;
        List<Coordinates> path = new ArrayList<Coordinates>();

        Directions nextLvlDirection = TOP;

        boolean allSiblingsChecked = false;

        do {
            //получаем соседний узел графа

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

            //сменим на направление, в котором проверяем сязанные узлы
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
/*
        System.out.println(queue);
        System.out.println(marked);
        RenderPathFinder(map, srcCoord,  queue, marked,path);
*/
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

/*
    //функции для отладки getPath()
    private static void RenderPathFinder(Map map, Coordinates srcCoord, Queue<Coordinates> queue, HashMap<Coordinates, Coordinates> marked, List<Coordinates> path) {
         int width = map.getWidth();
        int height = map.getHeight();

        Entity current;
        System.out.println();

        StringBuilder sb = new StringBuilder("|\t");
        for (int x = 0; x < width; x++) {
            sb.append(x);
            sb.append("\t");
        }
        sb.append("|");
        System.out.println(sb);
        Coordinates currCoord;
        for (int y = 0; y < height; y++) {
            sb = new StringBuilder("|\t");
            for (int x = 0; x < width; x++) {
                currCoord=new Coordinates(x, y);
                if (currCoord.equals(srcCoord)){
                    sb.append("S\t");
                }
                else{
                    current = map.getEntity(currCoord);
                    if (current == null) {
                        if (path.contains(currCoord)) {
                            sb.append("P\t");
                        }
                        else{
                            if (queue.contains(currCoord)) {
                                sb.append("q\t");
                            }
                            else{
                                if (marked.containsKey(currCoord)) {
                                    sb.append("m\t");
                                }
                                else{
                                    sb.append(" \t");
                                }

                            }
                        }

                    } else {
                        sb.append(current.getRenderIcon());
                        sb.append("\t");
                    }
                }
            }
            sb.append("|");
            sb.append(y);
            System.out.println(sb);
        }
        System.out.println();

    }

    public static void main(String[] args) throws InterruptedException {
        Map map = new Map(20, 15);
        int width = map.getWidth();
        int height = map.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Coordinates currentCoord = new Coordinates(x, y);
                map.setEmptyCell(currentCoord);

            }
        }

        Coordinates srcPoint = new Coordinates(10,7);
        Coordinates target2Point = new Coordinates(19,0);

        map.setEntity(srcPoint, new Wolf(srcPoint, CreaturesNamesGenerator.getName()));

        for (int i = 0; i < 13; i++) {
            Coordinates rockPoint = new Coordinates(8,i);
            map.setEntity(rockPoint, new Grass(rockPoint));

        }

        map.setEntity(target2Point, new Rabbit(target2Point, CreaturesNamesGenerator.getName()));

        List path=getPath(map, srcPoint, Herbivore.class);
        System.out.println(path);

    }*/
}