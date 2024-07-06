package ru.gb.family_tree.model.family_tree;

import ru.gb.family_tree.model.family_tree.comparators.FamilyTreeComparatorByDate;
import ru.gb.family_tree.model.family_tree.comparators.FamilyTreeComparatorByName;
import ru.gb.family_tree.model.saving.FileHandler;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class FamilyTree<E extends FamilyTreeItems<E>> extends FileHandler implements Serializable, Iterable<E> {
    private int count = 1;
    private List<E> humanList;
    private String familyName;


    public FamilyTree(String familyName){
        this.familyName = familyName;
        humanList = new ArrayList<>();
    }

    public String getFamilyName() {
        return familyName;
    }


    public int addInFamily(E human) {
        int newId = count++;
        human.setId(newId);
        humanList.add(human);
        return newId;
    }

    public int getCount() {
        return count;
    }

    public String getFamilyInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(familyName);
        stringBuilder.append(":\n");
        for (E human: humanList) {
            stringBuilder.append(human.getId());
            stringBuilder.append(". ");
            stringBuilder.append(human);
        }
        return stringBuilder.toString();
    }

    public int size() {
        return humanList.size();
    }

    public void isSpouse (E human1, E human2) {

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
    public String toString() {
        return getFamilyInfo();
    }

    @Override
    public Iterator<E> iterator() {
        return new FamilyTreeIterator<>(humanList);
    }

//    public void sortByName() { humanList.sort(new FamilyTreeComparatorByName<>()); }
//
//    public void sortByBirthDate() { humanList.sort(new FamilyTreeComparatorByDate<>()); }

    public void sort(Comparator<E> comparator) {
        humanList.sort(comparator);
    }
}

