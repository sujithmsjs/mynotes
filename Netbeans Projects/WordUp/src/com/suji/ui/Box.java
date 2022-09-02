package com.suji.ui;

import java.util.ArrayList;
import java.util.List;

public class Box {

    private List<String> list;
    private int index = -1;
    private int size;

    public Box(List<String> list) {
        this.list = list;
        size = list.size();
    }

    public int getIndex() {
        return index;
    }

    public int getSize() {
        return size;
    }

    public static Box getABox() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(" Value at  " + i);

        }
        System.out.println(list);
        return new Box(list);
    }

    public boolean hasNext() {
        return index < size-1;
    }

    public String get() {
        return list.get(index);
    }

    public boolean hasPrevious() {
        return index > 0;
    }

    public String next() {
        if (hasNext()) {
            index++;
        }
        return list.get(index);
    }

    public String previous() {
        if (hasPrevious()) {
            index--;
        }
        return list.get(index);
    }

    public void set(String e) {
        list.set(index, e);
    }

    public int size() {
        return list.size();
    }
}
