package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }

    static {
        reset();
    }

    public static CanFly result;

    public static void reset() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String machine = bufferedReader.readLine();

            if (machine.equals("helicopter")) {
                result = new Helicopter();
            } else if (machine.equals("plane")) {
                result = new Plane(bufferedReader.read());
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("IOException");
        }

    }
}
