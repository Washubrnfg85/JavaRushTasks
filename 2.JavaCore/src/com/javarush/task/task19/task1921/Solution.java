package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Хуан Хуанович
D:\Java_Projects\test_chars_3.txt
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {
        String fileName = args[0];

        List<String> lines = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                lines.add(fileReader.readLine());
            }
        }

        for (String line : lines) {
            String[] parameters = line.split(" ");
            Calendar date = new GregorianCalendar(Integer.parseInt(parameters[parameters.length - 1]),
                                            (Integer.parseInt(parameters[parameters.length - 2]) - 1) ,
                                                  Integer.parseInt(parameters[parameters.length - 3]));
            Date dateOfBirth = date.getTime();

            String[] nameParts = Arrays.copyOfRange(parameters, 0, parameters.length - 3);
            String fullName = "";
            for (int i = 0; i < nameParts.length; i++) {
                if (i == nameParts.length -1) {
                    fullName += nameParts[i];
                } else {
                    fullName += nameParts[i] + " ";
                }
            }

            PEOPLE.add(new Person(fullName, dateOfBirth));
        }
    }
}
