package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* 
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    List<Entry> allEntries = new ArrayList<>();
    List<Entry> entriesToDelete = new ArrayList<>();
    Entry<String> root;

    public CustomTree () {
        this.root = new Entry<>("0 (root)");
        allEntries.add(this.root);
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }
    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }
    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }
    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }
    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String s) {
        checkForLeafsAndUnblockThem(allEntries);
        Entry<String> newEntry = new Entry<>(s);
        for (Entry entry : allEntries) {
            if (entry.availableToAddLeftChildren) {
                newEntry.parent = entry;
                entry.leftChild = newEntry;
                entry.availableToAddLeftChildren = false;
                allEntries.add(newEntry);
                return true;
            } else if (entry.availableToAddRightChildren) {
                newEntry.parent = entry;
                entry.rightChild = newEntry;
                entry.availableToAddRightChildren = false;
                allEntries.add(newEntry);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (!o.getClass().equals(String.class)) {
            throw new UnsupportedOperationException();
        } else {
            for (Entry entry : allEntries) {
                if (entry.elementName.equals(o)) {
                    entriesToDelete.add(entry);
                    addChildrenToDeleteList(entry);
                }
            }
            for (Entry entry : entriesToDelete) {
                allEntries.remove(entry);
            }
        }
        return false;
    }

    public void addChildrenToDeleteList(Entry root) {
        if (root.leftChild != null) {
            entriesToDelete.add(root.leftChild);
            addChildrenToDeleteList(root.leftChild);
        }
        if (root.rightChild != null) {
            entriesToDelete.add(root.rightChild);
            addChildrenToDeleteList(root.rightChild);
        }
    }

    public boolean checkForLeafsAndUnblockThem (List<Entry> list) {
        int counter = 0;
        for (Entry entry : allEntries) {
            if (!entry.isAvailableToAddChildren())
                counter++;
        }
        if (counter == list.size()) {
            for (int i = list.size() - 1; i >= list.size() - ((list.size() + 1) / 2); i--) {
                allEntries.get(i).availableToAddLeftChildren = true;
                allEntries.get(i).availableToAddRightChildren = true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return allEntries.size() - 1;
    }

    public String getParent(String s) {
        for (Entry entry : allEntries) {
            if (entry.elementName.equals(s)) {
                return entry.parent.elementName;
            }
        }
        return null;
    }

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren () {
            return availableToAddLeftChildren | availableToAddRightChildren;
        }
    }
}
