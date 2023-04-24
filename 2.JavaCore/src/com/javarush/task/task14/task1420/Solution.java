package com.javarush.task.task14.task1420;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
НОД
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println(findCommonDivider(scanner.nextInt(), scanner.nextInt()));
        scanner.close();
    }

    public static int findCommonDivider(int a, int b) {
        if (a > b) {
            int divisible = a;
            int divider = b;
            int mod = divisible % divider;
            while (mod != 0) {
                divisible = divider;
                divider = mod;
                mod = divisible % divider;
            }
            return divider;
        } else if (a < b) {
            int divisible = b;
            int divider = a;
            int mod = divisible % divider;
            while (mod != 0) {
                divisible = divider;
                divider = mod;
                mod = divisible % divider;
            }
            return divider;
        } else {
            return a;
        }
    }
}
