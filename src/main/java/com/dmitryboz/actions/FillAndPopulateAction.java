package com.dmitryboz.actions;

import com.dmitryboz.Coordinates;
import com.dmitryboz.SimMap;
import com.dmitryboz.entities.CreaturesNamesGenerator;
import com.dmitryboz.entities.creatures.Rabbit;
import com.dmitryboz.entities.creatures.Wolf;
import com.dmitryboz.entities.static_objects.Grass;
import com.dmitryboz.entities.static_objects.Rock;
import com.dmitryboz.entities.static_objects.Tree;

import java.util.Random;

public class FillAndPopulateAction extends Action {

    public FillAndPopulateAction(final SimMap map) {
        super(map);
    }

    @Override
    public void perform() {
        Random rand = new Random(47);
        int width = map.getWidth();
        int height = map.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Coordinates currentCoord = new Coordinates(x, y);
                switch (rand.nextInt(70)) {
                    case 0:
                    case 1:
                        map.setEntity(currentCoord, new Rabbit(currentCoord, CreaturesNamesGenerator.getName()));
                        break;
                    case 2:
                        map.setEntity(currentCoord, new Wolf(currentCoord, CreaturesNamesGenerator.getName()));
                        break;
                    case 3:
                        map.setEntity(currentCoord, new Tree(currentCoord));
                        break;
                    case 4:
                        map.setEntity(currentCoord, new Rock(currentCoord));
                        break;
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        map.setEntity(currentCoord, new Grass(currentCoord));
                        break;
                    default:
                        map.setEmptyCell(currentCoord);
                        break;
                }

            }
        }
    }
}
