package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final long serialVersionUID = 1L;
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet(){
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        this.map = new HashMap<>((int) Math.max (16, (collection.size()/.75f) + 1));
        addAll(collection);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o, map.get(o));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            AmigoSet<E> newSet = (AmigoSet<E>) super.clone();
            newSet.map = (HashMap<E, Object>) map.clone();
            return newSet;
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject();
        outputStream.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        outputStream.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        outputStream.writeInt(map.size());
        for (E element : map.keySet()) {
            outputStream.writeObject(element);
        }
    }

    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject();
        map = new HashMap<>(inputStream.readInt(), inputStream.readFloat());
        int size = inputStream.readInt();
        for (int i = 0; i < size; i++) {
            E element = (E) inputStream.readObject();
            map.put(element , PRESENT);
        }
    }
}
