package com.javarush.task.task14.task1421;

public class Singleton {
    private static Singleton instance = new Singleton();
    private Singleton() {
        System.out.println("Singlton instance been created");
    }
    public static Singleton getInstance() {
        return instance;
    }
}
