package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Клубок
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new Rope1());
        threads.add(new Rope2());
        threads.add(new Rope3());
        threads.add(new Rope4());
        threads.add(new Rope5());
    }

    public static void main(String[] args) throws InterruptedException {
    }

    public static class Rope1 extends Thread {
        @Override
        public void run() {
            while (true) {
            }
        }
    }

    public static class Rope2 extends Thread {
        @Override
        public void run() {
            if (Thread.interrupted()) {
                try {
                    throw new InterruptedException();
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
                }
            }
        }
    }

    public static class Rope3 extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("Ура");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;

                }
            }
        }
    }

    public static class Rope4 extends Thread implements Message {
        @Override
        public void showWarning() {
            this.interrupt();
        }

        @Override
        public void run() {
            while (!isInterrupted()) {

            }
        }
    }

    public static class Rope5 extends Thread {
        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int sum = 0;
            String key;
            try {
                while (!(key = reader.readLine()).equals("N")) {
                    sum += Integer.parseInt(key);
                }
                System.out.println(sum);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}