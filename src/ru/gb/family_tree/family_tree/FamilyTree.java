package ru.gb.family_tree.family_tree;

import ru.gb.family_tree.family_tree.comparators.FamilyTreeComparatorByDate;
import ru.gb.family_tree.family_tree.comparators.FamilyTreeComparatorByName;
import ru.gb.family_tree.saving.FileHandler;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FamilyTree<E extends FamilyTreeItems<E>> extends FileHandler implements Serializable, Iterable<E> {
    long id;
    private int humanId;
    private List<E> humanList;


    public FamilyTree(long id) {
        this.id = id;
        humanList = new ArrayList<>();
    }

    public void addInFamily(E human){
        human.setId(humanId++);
        humanList.add(human);
    }

    public String getHumanInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Список членов семьи:\n");
        for (E human : humanList) {
            stringBuilder.append(human);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void isSpose (E human1, E human2) {

        List<E> human1Spouse = human1.getSpouse();
        human1Spouse.add(human2);
        human1.setSpouse(human1Spouse);

        List<E> human2Spouse = human2.getSpouse();
        human2Spouse.add(human1);
        human2.setSpouse(human2Spouse);
    }

    public void isChildren (E children, E father, E mother) {

        children.setMother(mother);
        children.setFather(father);

        List<E> fatherChild = father.getChildren();
        fatherChild.add(children);
        father.setChildren(fatherChild);

        List<E> motherChild = mother.getChildren();
        motherChild.add(children);
        mother.setChildren(motherChild);
    }

    @Override
    public String toString() {
        return getHumanInfo();
    }

    public String getHumanInfo(E human) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append(human.getName());
        stringBuilder.append(":\n\n");

        if (!human.getChildren().isEmpty()) {
            stringBuilder.append("Дети:\n");
            for (E child: human.getChildren()) {
                stringBuilder.append(child);
            }
            stringBuilder.append("\n");
        }

        if (!human.getSpouse().isEmpty()) {
            stringBuilder.append("Супруги:\n");
            for (E spouse: human.getSpouse()) {
                stringBuilder.append(spouse);
            }
            stringBuilder.append("\n");
        }

        if (human.getMother() != null) {
            stringBuilder.append("Мать:\n");
            stringBuilder.append(human.getMother());
            stringBuilder.append("\n");
        }

        if (human.getFather() != null) {
            stringBuilder.append("Отец:\n");
            stringBuilder.append(human.getFather());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }


    @Override
    public Iterator<E> iterator() {
        return new FamilyTreeIterator<>(humanList);
    }

    public void sortByName() { humanList.sort(new FamilyTreeComparatorByName<>()); }

    public void sortByBirthDate() { humanList.sort(new FamilyTreeComparatorByDate<>()); }

}

