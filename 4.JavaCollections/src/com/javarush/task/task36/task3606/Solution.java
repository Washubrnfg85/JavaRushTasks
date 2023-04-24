package com.javarush.task.task36.task3606;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* 
Осваиваем ClassLoader и Reflection
*/

public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void fileFinder(File rootFile, List<Class> list) throws ClassNotFoundException {
        ClassLoader loader = Solution.class.getClassLoader();
        if (rootFile.isDirectory()) {
            for (File file : Objects.requireNonNull(rootFile.listFiles())) {
                if (file.isDirectory()) {
                    fileFinder(file, list);
                } else {
                    if (file.getName().endsWith(".class")) {
                        list.add(loader.loadClass(("com/javarush/task/task36/task3606/data/second/" + file.getName()).replaceAll("/", ".").replaceAll(".class", "")));
                    }
                }
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        try {
            for (Class clazz : hiddenClasses) {
                if (clazz.getSimpleName().toLowerCase().startsWith(key)) {
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return (HiddenClass) constructor.newInstance();
                }
            }
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException |
                 IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void scanFileSystem() {
        try {
            fileFinder(new File(packageName), hiddenClasses);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}