package com.dmitryboz;

import com.dmitryboz.action.Action;
import com.dmitryboz.action.FillAndPopulateAction;

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
    private boolean running = true;
    private boolean stopProgFlag = false;
    private long timestamp;

    public Simulation() {
        map = new Map(20, 15);
        scanner = new Scanner(System.in);
        timestamp = System.currentTimeMillis();
        initActions = new ArrayList<>();
        turnActions = new ArrayList<>();

        createActions();

        initActions(map); //инициализируем карту


        while (true) {
            if (System.currentTimeMillis() > timestamp + 1000) {

                keyPressEvents();
                timestamp = System.currentTimeMillis();

                if (running) {
                    nextTurn();
                    if (stopProgFlag) {
                        break;
                    }
                }

            }
        }
    }

    public void createActions () {
        initActions.add(new FillAndPopulateAction());
        turnActions.add(new TurnCreaturesAction());
        turnActions.add(new RespawnGrassAction(worldMap));
    }

    public void nextTurn() {
        ++stepNumber;
        Renderer.clearConsole();
        System.out.println("Step " + stepNumber);
        turnActions(map);
        Renderer.renderMap(map);
        Renderer.printPopulation(map);
    }


    public void keyPressEvents() {
        System.out.println("Press P to pause/continue");
        System.out.println("Press Q to quit");
        try {

            String input = scanner.nextLine();
            if (input.equals("P") || input.equals("p")) {
                if (running) {
                    pauseSimulation();
                } else {
                    startSimulation();
                }
            } else if (input.equals("Q") || input.equals("Q")) {
                stopProgram();
            }
        } catch (Exception e) {

        }

    }


    public void startSimulation() {
        running = true;
    }

    public void pauseSimulation() {
        running = false;
    }

    public void stopProgram() {
        stopProgFlag = true;
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
    }
}