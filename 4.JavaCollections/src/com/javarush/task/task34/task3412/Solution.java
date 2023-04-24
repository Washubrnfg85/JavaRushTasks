package com.javarush.task.task34.task3412;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/* 
Добавление логирования в класс
*/

public class Solution {
    private static final Logger logger = LoggerFactory.getLogger(Solution.class);

    private int value1;
    private String value2;
    private Date value3;

    public Solution(int value1, String value2, Date value3) {
        logger.debug("Debug method: Solution.constructor");
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public static void main(String[] args) {
        Solution solution = new Solution(3765, "JavaRushTask3412", new Date());

        solution.calculateAndSetValue3(2648939L);
        solution.printString();
        solution.printDateAsLong();
        solution.divide(36, 4);

        solution.setValue1(Integer.MAX_VALUE);
        solution.setValue2("");
        solution.setValue3(null);

        solution.calculateAndSetValue3(2648939L);
        solution.printString();
        solution.printDateAsLong();
        solution.divide(38, 0);
    }

    public void calculateAndSetValue3(long value) {
        logger.debug("Debug method: Solution.divide");
        value -= 133;
        if (value > Integer.MAX_VALUE) {
            logger.debug("Debug method: Solution.calculateAndSetValue3");
            value1 = (int) (value / Integer.MAX_VALUE);
        } else {
            logger.trace("Program works correctly");
            value1 = (int) value;
        }
    }

    public void printString() {
        if (value2 != null) {
            logger.trace("Program works correctly");
            System.out.println(value2.length());
        }
    }

    public void printDateAsLong() {
        if (value3 != null) {
            logger.trace("Program works correctly");
            System.out.println(value3.getTime());
        }
    }

    public void divide(int number1, int number2) {
        try {
            logger.trace("Program works correctly");
            System.out.println(number1 / number2);
        } catch (ArithmeticException e) {
            logger.error(new Date() + " Solution.divide(" + number1 + " / " + number2 + ") Invalid arithmetical operation");
        }
    }

    public void setValue1(int value1) {
        logger.debug("Debug method: Solution.setValue1");
        this.value1 = value1;
    }

    public void setValue2(String value2) {
        logger.debug("Debug method: Solution.setValue2");
        this.value2 = value2;
    }

    public void setValue3(Date value3) {
        logger.debug("Debug method: Solution.setValue3");
        this.value3 = value3;
    }
}
