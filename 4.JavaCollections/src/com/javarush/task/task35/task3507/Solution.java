package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/

public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> set = new HashSet<>();
        File directory = new File(pathToAnimals);
        for (File fileClass : Objects.requireNonNull(directory.listFiles())) {
            try {
                Class clazz = Class.forName(fileClass.getAbsolutePath());
                if (clazz.getSuperclass() == Animal.class) {
                    Constructor constructor = clazz.getConstructor();
                    set.add((Animal) constructor.newInstance());
                }
            }  catch (Exception e) {
                e.printStackTrace();
            }
        }
        return set;
    }

    static class CustomClassLoader extends ClassLoader {
        @Override
        protected Class<? extends Animal> findClass(String fileName) throws ClassNotFoundException {
            try {
                Path classFile = new File(fileName).toPath();
                byte[] bytes = Files.readAllBytes(classFile);
                Class instance = defineClass(null, bytes, 0, bytes.length);
                return instance;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}