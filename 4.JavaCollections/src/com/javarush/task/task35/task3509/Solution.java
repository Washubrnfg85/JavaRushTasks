package com.javarush.task.task35.task3509;

import java.util.*;

/* 
Collections & Generics
*/

public class Solution {

    public static void main(String[] args) {
        Integer[] numbers = new Integer[] {1, 2, 3, 4, 5};
        for (Object o : newArrayList(numbers)) {
            System.out.println(o.getClass().getSimpleName());
        }
        System.out.println();

        String[] strings = new String[] {"one", "two", "three", "four", "five"};
        for (Object o : newHashSet(strings)) {
            System.out.println(o.getClass().getSimpleName());
        }
        System.out.println();

        for (Object entry : newHashMap(newArrayList(numbers), newArrayList(strings)).entrySet()) {
            System.out.println(entry);
        }
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        ArrayList<T> list = new ArrayList<>();
        for (T element : elements) {
            list.add(element);
        }
        return list;
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        HashSet<T> set = new HashSet<>();
        for (T element : elements) {
            set.add(element);
        }
        return set;
    }

    public static <K, V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        if (keys.size() == values.size()) {
            HashMap<K, V> hashMap = new HashMap<>();
            for (int i = 0; i < keys.size(); i++) {
                hashMap.put(keys.get(i), values.get(i));
            }
            return hashMap;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
