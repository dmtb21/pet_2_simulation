package com.dmitryboz;

import com.dmitryboz.actions.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private final SimMap map;

    private int stepNumber = 0;
    private boolean endOfUniverse = false;
    private boolean isRun = false;

    public Simulation() throws InterruptedException {
        map = new SimMap(20, 15);
        initActions = new ArrayList<>();
        turnActions = new ArrayList<>();

        createActions();
        performActions(initActions);
    }

    public void start() throws InterruptedException {
        if(!isRun){
            endOfUniverse=false;
            while (true) {
                Thread.sleep(500);
                if (!endOfUniverse) {
                    nextTurn();
                } else {
                    System.out.println("endOfUniverse: True ");
                    isRun=false;
                    break;
                }
            }
        }
    }

    public void setEndOfUniverse() {
        this.endOfUniverse = true;
    }

    private void createActions() {
        initActions.add(new FillAndPopulateAction(map));
        turnActions.add(new TurnCreaturesAction(map, this));
        turnActions.add(new GrowGrassAction(map));
        turnActions.add(new RespawnHerbivoreAction(map));
    }

    private void performActions(List<Action> actions) {
        for (Action action : actions) {
            action.perform();
        }
    }

    private void nextTurn() {
        ++stepNumber;
        ConsoleRenderer.clearConsole();
        System.out.println("Step " + stepNumber);
        ConsoleRenderer.renderMap(map);
        ConsoleRenderer.printPopulation(map);
        performActions(turnActions);
    }
}