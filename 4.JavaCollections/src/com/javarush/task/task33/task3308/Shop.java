package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement
public class Shop {
    public Goods goods = new Goods();

    public int count = 12;
    public double profit = 123.4;

    public String[] secretData = new String[5];

    public static class Goods {
        List<String> names = new ArrayList<>(Arrays.asList("S1", "S2"));
    }
}
