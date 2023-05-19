package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.Set;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date startTimeIdMeasure = new Date();

        Date endTimeIdMeasure = new Date();
        return endTimeIdMeasure.getTime() - startTimeIdMeasure.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date startTimeStringMeasure = new Date();

        Date endTimeStringMeasure = new Date();
        return endTimeStringMeasure.getTime() - startTimeStringMeasure.getTime();
    }

    @Test
    public void testHashMapStorage() {

    }
}
