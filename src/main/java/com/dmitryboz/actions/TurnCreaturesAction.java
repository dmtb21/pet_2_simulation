package com.dmitryboz.actions;

import com.dmitryboz.Map;

public class TurnCreaturesAction extends Action {

    public TurnCreaturesAction(final Map map) {
        super(map);
    }

    @Override
    public void perform(){
       /* HashMap<Coordinates, Entity> entities=map.getEntities();

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
        }*/
    }
}
