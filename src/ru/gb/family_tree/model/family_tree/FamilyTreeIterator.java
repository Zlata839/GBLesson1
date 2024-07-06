package ru.gb.family_tree.model.family_tree;

import java.util.Iterator;
import java.util.List;

public class FamilyTreeIterator<T> implements Iterator<T> {

    private List<T> list;
    private int index;

    public FamilyTreeIterator(List<T> humanList) {
        this.list = humanList;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public T next() {
        return list.get(index++);
    }
}
