package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

/* 
Десериализация JSON объекта
*/

public class Solution {
    static final String fileName = "D:/Java_Projects/JSON.txt";
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new FileReader(fileName), clazz);
    }

    public static void main(String[] args) throws IOException {
        Cat cat = convertFromJsonToNormal(fileName, Cat.class);
        System.out.println(cat.toString());
    }

    @JsonAutoDetect
    public static class Cat {
        public String name;
        public int age;
        public int weight;
        Cat(){}
    }
}
