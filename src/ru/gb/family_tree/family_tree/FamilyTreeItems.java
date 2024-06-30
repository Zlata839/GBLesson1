package ru.gb.family_tree.family_tree;

import java.time.LocalDate;
import java.util.List;

public interface FamilyTreeItems<T> {
    void setId(int i);
    long getId();
    String getName();
    LocalDate getBirthDate();
    void setSpouse(List<T> husbands);
    List<T> getSpouse();
    void setFather(T father);
    void setMother(T mother);
    List<T> getChildren();
    void setChildren(List<T> children);
    T getMother();
    T getFather();
}

