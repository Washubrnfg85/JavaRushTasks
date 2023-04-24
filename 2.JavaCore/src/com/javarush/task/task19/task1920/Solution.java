package com.javarush.task.task19.task1920;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
Самый богатый
D:\Java_Projects\test_chars_3.txt
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        List<Person> persons = new ArrayList<>();
        try (BufferedReader readLines = new BufferedReader(new FileReader(args[0]))) {
            while (readLines.ready()) {
                String[] parameters = readLines.readLine().split(" ");
                persons.add(new Person(parameters[0], Double.valueOf(parameters[1])));
            }
        }

        Map<String, Double> table = new TreeMap<>();
        for (Person person : persons) {
            if (table.containsKey(person.getName())) {
                person.setSalary(person.getSalary() + table.get(person.getName()));
                table.put(person.getName(), person.getSalary());
            } else {
                table.put(person.getName(), person.getSalary());
            }
        }

        List<Double> salaries = new ArrayList<>(table.values());
        Double maxSalary = Collections.max(salaries);

        for (Map.Entry<String, Double> each : table.entrySet()) {
            if (Objects.equals(each.getValue(), maxSalary)) {;
                System.out.println(each.getKey());
            }
        }
    }

    public static class Person {
        private String name;
        private Double salary;

        public Person (String name, Double salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return this.name;
        }

        public Double getSalary() {
            return this.salary;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSalary(Double salary) {
            this.salary = salary;
        }
    }
}
