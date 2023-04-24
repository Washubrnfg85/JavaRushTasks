package com.javarush.task.pro.task17.task1708;

import java.util.ArrayList;
import java.util.Collections;

/* 
Минимальное и максимальное
*/


public class MinMaxUtil {

    public static int min(int a, int b) {
        return a < b ? a : b;
    }

    public static int min(int a, int b, int c) {
        ArrayList<Integer> array = new ArrayList<>();
        array.add(a);
        array.add(b);
        array.add(c);
        return Collections.min(array);
    }

    public static int min(int a, int b, int c, int d) {
        ArrayList<Integer> array = new ArrayList<>();
        array.add(a);
        array.add(b);
        array.add(c);
        array.add(d);
        return Collections.min(array);
    }

    public static int min(int a, int b, int c, int d, int e) {
        ArrayList<Integer> array = new ArrayList<>();
        array.add(a);
        array.add(b);
        array.add(c);
        array.add(d);
        array.add(e);
        return Collections.min(array);
    }

    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    public static int max(int a, int b, int c) {
        ArrayList<Integer> array = new ArrayList<>();
        array.add(a);
        array.add(b);
        array.add(c);
        return Collections.max(array);
    }

    public static int max(int a, int b, int c, int d) {
        ArrayList<Integer> array = new ArrayList<>();
        array.add(a);
        array.add(b);
        array.add(c);
        array.add(d);
        return Collections.max(array);
    }

    public static int max(int a, int b, int c, int d, int e) {
        ArrayList<Integer> array = new ArrayList<>();
        array.add(a);
        array.add(b);
        array.add(c);
        array.add(d);
        array.add(e);
        return Collections.max(array);
    }
}
