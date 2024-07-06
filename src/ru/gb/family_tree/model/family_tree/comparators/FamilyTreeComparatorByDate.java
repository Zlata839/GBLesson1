package ru.gb.family_tree.model.family_tree.comparators;

import ru.gb.family_tree.model.family_tree.FamilyTreeItems;

import java.util.Comparator;

public class FamilyTreeComparatorByDate<T extends FamilyTreeItems<T>> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        return Integer.compare(o1.getBirthDate().getYear(), o2.getBirthDate().getYear());
    }
}

