package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int count = 0;
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            count += entry.getValue().size();
        }
        return count;
    }

    @Override
    public V put(K key, V value) {
        if (map.containsKey(key)) {
            V valueToRemove;
            if (map.get(key).size() < repeatCount) {
                valueToRemove = map.get(key).get(map.get(key).size() - 1);
            } else {
                valueToRemove = map.get(key).get(repeatCount - 1);
                map.get(key).remove(0);
            }
            map.get(key).add(value);
            return valueToRemove;
        } else {
            List<V> list = new ArrayList<>(repeatCount);
            map.put(key, list);
            list.add(value);
            return null;
        }
    }

    @Override
    public V remove(Object key) {
        if (map.containsKey(key)) {
            V valueToReturn;
            if (map.get(key).size() > 1) {
                valueToReturn = map.get(key).get(0);
                map.get(key).remove(0);
            } else {
                valueToReturn = map.get(key).get(0);
                map.remove(key, map.get(key));
            }
            return valueToReturn;
        } else {
            return null;
        }
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        List<V> list = new ArrayList<>();
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            list.addAll(entry.getValue());
        }
        return list;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            if (entry.getValue().contains(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}