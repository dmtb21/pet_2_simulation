package com.dmitryboz;

import com.dmitryboz.entities.Entity;

import java.util.Map;

public class ConsoleRenderer {
    private ConsoleRenderer() {
        throw new UnsupportedOperationException();
    }

    public static void renderMap(SimMap map) {
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

        for (int y = 0; y < height; y++) {
            sb = new StringBuilder("|\t");
            for (int x = 0; x < width; x++) {
                current = map.getEntity(new Coordinates(x, y));
                if (current == null) {
                    sb.append(" \t");
                } else {
                    sb.append(current.getRenderIcon());
                    sb.append("\t");
                }
            }
            sb.append("|");
            sb.append(y);
            System.out.println(sb);
        }
        System.out.println();

    }

    public static void printPopulation(SimMap map) {
        System.out.println("Population:");
        Map<String, Integer> pupulationData = map.getPopulationData();

        for (String key : pupulationData.keySet()) {
            System.out.println(key + " " + pupulationData.get(key));
        }
    }

    public static void clearConsole() {
        try {
            String operatingSystem = System.getProperty("os.name");
            if (operatingSystem.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
