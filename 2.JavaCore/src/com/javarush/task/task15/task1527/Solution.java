package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        parseURLForParametersAndObjectValues(url);



    }

    public static void parseURLForParametersAndObjectValues(String url) {
        String stringWithParameters = url.substring(url.lastIndexOf("?") + 1);
        String[] parametersWithValues = stringWithParameters.split("&");
        ArrayList<String> parameterValues = new ArrayList<>();

        for(String each : parametersWithValues) {
            if (each.contains("=")) {
                String[] parameterParts = each.split("=");
                if (parameterParts[0].equals("obj")) {
                    parameterValues.add(parameterParts[1]);
                }
                System.out.print(parameterParts[0] + " ");
            } else {
                System.out.print(each + " ");
            }
        }

        System.out.println();

        for (String item : parameterValues) {
            try {
                alert(Double.parseDouble(item));
            } catch (Exception e) {
                alert(item);
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
