package com.dmitryboz.actions;

import com.dmitryboz.SimMap;
import com.dmitryboz.Simulation;
import com.dmitryboz.entities.creatures.Creature;

import java.util.List;

public class TurnCreaturesAction extends Action {
    private final Simulation simulation;

    public TurnCreaturesAction(final SimMap map, final Simulation simulation) {
        super(map);
        this.simulation = simulation;
    }

    @Override
    public void perform() {
        //получим список существ
        List<Creature> creaturesList = map.getCreaturesList();

        boolean movesAvailable = false;
        for (Creature creature : creaturesList) {
            if (creature.isAlive()) {//если существо ещё живо после ходов предыдущих существ, то выполним ход
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
