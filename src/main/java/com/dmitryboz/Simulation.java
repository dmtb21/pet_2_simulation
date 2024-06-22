package com.dmitryboz;

import com.dmitryboz.actions.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<Action> initActions;

    private final List<Action> turnActions;
    private final Map map;
    // private Scanner scanner;

    private int stepNumber = 0;
    private boolean endOfUniverse = false;

    public Simulation() throws InterruptedException {
        map = new Map(20, 15);
        //map = new Map(5, 5);
        //scanner = new Scanner(System.in);
        initActions = new ArrayList<>();
        turnActions = new ArrayList<>();

        createActions();
        performActions(initActions);

        startSimulation();

    }

    private void startSimulation() throws InterruptedException {
        while (true) {
            Thread.sleep(500);
            if (!endOfUniverse) {
                nextTurn();
            } else {
                System.out.println("endOfUniverse: True ");
                break;
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
        Renderer.clearConsole();
        System.out.println("Step " + stepNumber);
        //turnActions(map);
        Renderer.renderMap(map);
        Renderer.printPopulation(map);
        performActions(turnActions);
    }

    public static void main(String[] args) {
        try {
            Simulation simulation = new Simulation();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}