package com.dmitryboz.actions;

import com.dmitryboz.Map;
import com.dmitryboz.Simulation;
import com.dmitryboz.entities.creatures.Creature;

import java.util.List;

public class TurnCreaturesAction extends Action {
    private final Simulation simulation;

    public TurnCreaturesAction(final Map map, final Simulation simulation) {
        super(map);
        this.simulation = simulation;
    }

    @Override
    public void perform() {
        // HashMap<Coordinates, Entity> entities = map.getEntities();
        //получим список существ
        List<Creature> creaturesList = map.getCreaturesList();

        //System.out.println("creaturesList " +creaturesList);

        boolean movesAvailable = false;
        for (Creature creature : creaturesList) {
            if (creature.isAlive()) {//если существо ещё живо после ходов предыдущих существ, то выполним ход
                //System.out.println(creature+ " makeMove");
                if (creature.makeMove(map) && !movesAvailable) {
                    movesAvailable = true;
                }
            }

        }
        if (!movesAvailable) {
            simulation.setEndOfUniverse();
        }
    }
}
