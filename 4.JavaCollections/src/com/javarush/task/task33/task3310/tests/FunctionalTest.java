package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {
    public void testStorage(Shortener shortener) {
        String one = "This string is the same for one and three";
        String two = "This string shouldn't be equal to any other string";
        String three = "This string is the same for one and three";

        Long[] ids = new Long[3];

        ids[0] = shortener.getId(one);
        ids[1] = shortener.getId(two);
        ids[2] = shortener.getId(three);

        Assert.assertEquals(ids[0], ids[2]);

        Assert.assertNotEquals(ids[1], ids[0]);
        Assert.assertNotEquals(ids[1], ids[2]);

        String[] strings = new String[3];

        strings[0] = shortener.getString(ids[0]);
        strings[1] = shortener.getString(ids[1]);
        strings[2] = shortener.getString(ids[2]);

        Assert.assertEquals(one, strings[0]);
        Assert.assertEquals(two, strings[1]);
        Assert.assertEquals(three, strings[2]);
    }

    @Test
    public void testHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }
}
