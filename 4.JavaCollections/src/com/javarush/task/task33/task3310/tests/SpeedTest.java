package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date startTimeIdMeasure = new Date();
        for(String str : strings) {
            ids.add(shortener.getId(str));
        }
        Date endTimeIdMeasure = new Date();
        return endTimeIdMeasure.getTime() - startTimeIdMeasure.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date startTimeStringMeasure = new Date();
        for(Long id : ids) {
            strings.add(shortener.getString(id));
        }
        Date endTimeStringMeasure = new Date();
        return endTimeStringMeasure.getTime() - startTimeStringMeasure.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>(10000);
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> ids1 = new HashSet<>(10000);
        Set<Long> ids2 = new HashSet<>(10000);

        long timeToGetIds1 = getTimeToGetIds(shortener1, origStrings, ids1);
        long timeToGetIds2 = getTimeToGetIds(shortener2, origStrings, ids2);

        Assert.assertTrue(timeToGetIds1 > timeToGetIds2);

        Set<String> reStrings1 = new HashSet<>(10000);
        Set<String> reStrings2 = new HashSet<>(10000);

        long timeToGetString1 = getTimeToGetStrings(shortener1, ids1, reStrings1);
        long timeToGetString2 = getTimeToGetStrings(shortener2, ids2, reStrings2);

        Assert.assertEquals(timeToGetString1, timeToGetString2, 30);
    }
}
