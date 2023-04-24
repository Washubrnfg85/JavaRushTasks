package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/


public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0},
                {1, 1, 0, 0}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        byte[][] a3 = new byte[][]{
                {1, 1, 1, 0},
                {1, 1, 1, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
        int count3 = getRectangleCount(a3);
        System.out.println("count = " + count3 + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {
        int count = 0;
        int leftColumn = 0;
        int rightColumn = 0;
        boolean inRectangle = false;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == 1 && !inRectangle) {
                    count++;
                    a[i][j] = 0;
                    inRectangle = true;
                    leftColumn = j;
                } else if (a[i][j] == 1 && inRectangle) {
                    a[i][j] = 0;
                } else if (a[i][j] == 0 && inRectangle && j == leftColumn) {
                    inRectangle = false;
                    i = 0;
                    j = 0;
                } else if (a[i][j] == 0 && inRectangle && i < a.length - 1) {
                    i++;
                    rightColumn = j - 1;
                    j = leftColumn - 1;
                } else if (a[i][j] == 0 && inRectangle && i == a.length - 1) {
                    rightColumn = j - 1;
                    inRectangle = false;
                }
            }
        }
        return count;
    }
}
