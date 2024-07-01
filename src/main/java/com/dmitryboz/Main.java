package com.dmitryboz;

public class Main {
    private Main(){
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        try {
            Simulation simulation = new Simulation();
            simulation.start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}