package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.KidGirl;
import com.javarush.task.task37.task3702.female.TeenGirl;
import com.javarush.task.task37.task3702.female.Woman;
import com.javarush.task.task37.task3702.male.KidBoy;
import com.javarush.task.task37.task3702.male.Man;
import com.javarush.task.task37.task3702.male.TeenBoy;

public class HumanFactory {
    public Human getPerson(int age, boolean isMale) {
        if (isMale) {
            if (age <= KidBoy.MAX_AGE) {
                return new KidBoy();
            } else if (age <= TeenBoy.MAX_AGE) {
                return new TeenBoy();
            }
            return new Man();
        } else {
            if (age <= KidGirl.MAX_AGE) {
                return new KidGirl();
            } else if (age <= TeenGirl.MAX_AGE) {
                return new TeenGirl();
            }
            return new Woman();
        }
    }
}
