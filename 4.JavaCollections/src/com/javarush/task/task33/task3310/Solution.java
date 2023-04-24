package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
    }

    public static void testStrategy (StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> testSet = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testSet.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);
        Date startTimeIdMeasure = new Date();
        Set<Long> allIds = getIds(shortener, testSet);
        Date endTimeIdMeasure = new Date();
        Helper.printMessage(String.valueOf(endTimeIdMeasure.getTime() - startTimeIdMeasure.getTime()));

        Date startTimeStringsMeasure = new Date();
        Set<String> allStrings = getStrings(shortener, allIds);
        Date endTimeStringsMeasure = new Date();
        Helper.printMessage(String.valueOf(endTimeStringsMeasure.getTime() - startTimeStringsMeasure.getTime()));

        if (testSet.equals(allStrings)) {
            Helper.printMessage("Test passed.");
        } else {
            Helper.printMessage("Test not passed.");
        }
    }

    public static Set<Long> getIds (Shortener shortener, Set<String> strings) {
        Set<Long> set = new HashSet<>();
        for (String string : strings)
            set.add(shortener.getId(string));
        return set;
    }

    public static Set<String> getStrings (Shortener shortener, Set<Long> keys) {
        Set<String> set = new HashSet<>();
        for (Long id : keys)
            set.add(shortener.getString(id));
        return set;
    }
}
