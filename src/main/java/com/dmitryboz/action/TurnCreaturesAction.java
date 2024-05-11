package com.dmitryboz.action;

import com.dmitryboz.Coordinates;
import com.dmitryboz.Map;
import com.dmitryboz.entities.CreaturesNamesGenerator;
import com.dmitryboz.entities.Entity;
import com.dmitryboz.entities.creatures.Rabbit;
import com.dmitryboz.entities.creatures.Wolf;
import com.dmitryboz.entities.static_objects.Grass;
import com.dmitryboz.entities.static_objects.Rock;
import com.dmitryboz.entities.static_objects.Tree;

import java.util.HashMap;
import java.util.Random;

public class TurnCreaturesAction extends Action {

    @Override
    public void perform(final Map map){
        HashMap<Coordinates, Entity> entities=map.getEntities();

        for (int i = 0; i < map.getWorld().size(); i++) {
            if (worldMap.getEntity(i) instanceof Creature)
                ((Creature) worldMap.getEntity(i)).setAlreadyMove(false);
        }
        for (int i = 0; i < map.getWorld().size(); i++) {
            if (worldMap.getEntity(i) instanceof Creature &&
                    !((Creature) worldMap.getEntity(i)).isAlreadyMove()) {
                ((Creature) worldMap.getEntity(i)).setAlreadyMove(true);
                ((Creature) worldMap.getEntity(i)).makeMove(worldMap);
            }
        }

    }
}
