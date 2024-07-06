package ru.gb.family_tree.model.family_tree.comparators;

import ru.gb.family_tree.model.family_tree.FamilyTreeItems;

import java.util.Comparator;

public class FamilyTreeComparatorByName<T extends FamilyTreeItems<T>> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        return o1.getName().compareTo(o2.getName());
    }
}