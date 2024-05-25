package com.dmitryboz;

import com.dmitryboz.actions.Action;
import com.dmitryboz.actions.FillAndPopulateAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//индикация, произошло ли любое движение. Если нет - завершаем мир
public class Simulation {

    private final List<Action> initActions;

    private final List<Action> turnActions;
    private final Map map;
    private Scanner scanner;

    private int stepNumber = 0;
    private boolean endOfUniverse = false;
    public Simulation() throws InterruptedException {
        map = new Map(20, 15);
        scanner = new Scanner(System.in);
        initActions = new ArrayList<>();
        turnActions = new ArrayList<>();

        createActions();
        performActions(initActions);

        while (true) {
            Thread.sleep(1000);
            if (!endOfUniverse) {
                nextTurn();
            }
            else{
                System.out.println("endOfUniverse: True ");
                break;
            }

        }
    }

    private void createActions() {
        initActions.add(new FillAndPopulateAction(map));
        // turnActions.add(new TurnCreaturesAction());
        //turnActions.add(new RespawnGrassAction(worldMap));
    }

    private void performActions(List<Action> actions) {
        for (Action action : actions) {
            action.perform();
        }
    }

    public void nextTurn() {
        ++stepNumber;
        Renderer.clearConsole();
        System.out.println("Step " + stepNumber);
        //turnActions(map);
        performActions(turnActions);
        Renderer.renderMap(map);
        Renderer.printPopulation(map);
    }

    public static void main(String[] args) {
        try {
            Simulation simulation = new Simulation();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}