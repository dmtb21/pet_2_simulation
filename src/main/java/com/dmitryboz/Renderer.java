package com.dmitryboz;

import com.dmitryboz.entities.Entity;

import java.util.HashMap;

public class Renderer {
    private Renderer() {
    }

    public static void renderMap(Map map) {
        int width = map.getWidth();
        int height = map.getHeight();

        Entity current;
        System.out.println();
        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder("|\t");
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
            System.out.println(sb);
        }
        System.out.println();

    }

    public static void printPopulation(Map map) {
        System.out.println("Population:");
        HashMap<String, Integer> pupulationData = map.getPopulationData();

        for (String key : pupulationData.keySet()) {
            System.out.println(key + " " + pupulationData.get(key));
        }
    }

    public static void clearConsole() {
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
