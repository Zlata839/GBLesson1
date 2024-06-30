package ru.gb.family_tree.family_tree.comparators;

import ru.gb.family_tree.family_tree.FamilyTreeItems;

import java.util.Comparator;

public class FamilyTreeComparatorByDate<T extends FamilyTreeItems<T>> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) { return o1.getBirthDate().compareTo(o2.getBirthDate()); }
}

