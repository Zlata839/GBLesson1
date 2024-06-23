package ru.gb.family_tree;

import java.util.ArrayList;
import java.util.List;

public class FamilyTree {
    long id;
    private int humanId;
    private List<Human> humanList;

    public FamilyTree(long id) {
        this.id = id;
        humanList = new ArrayList<>();
    }

    public void addInFamily(Human human){
        human.setId(humanId++);
        humanList.add(human);
    }

    public String getHumanInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Список членов семьи:\n");
        for (Human human : humanList) {
            stringBuilder.append(human);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void isSpose (Human human1, Human human2) {

        List<Human> human1Spouse = human1.getSpouse();
        human1Spouse.add(human2);
        human1.setSpouse(human1Spouse);

        List<Human> human2Spouse = human2.getSpouse();
        human2Spouse.add(human1);
        human2.setSpouse(human2Spouse);
    }

    public void isChildren (Human children, Human father, Human mother) {

        children.setMother(mother);
        children.setFather(father);

        List<Human> fatherChild = father.getChildren();
        fatherChild.add(children);
        father.setChildren(fatherChild);

        List<Human> motherChild = mother.getChildren();
        motherChild.add(children);
        mother.setChildren(motherChild);
    }

    @Override
    public String toString() {
        return getHumanInfo();
    }

    public String getHumanInfo(Human human) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append(human.getName());
        stringBuilder.append(":\n\n");

        if (!human.getChildren().isEmpty()) {
            stringBuilder.append("Дети:\n");
            for (Human child: human.getChildren()) {
                stringBuilder.append(child);
            }
            stringBuilder.append("\n");
        }

        if (!human.getSpouse().isEmpty()) {
            stringBuilder.append("Супруги:\n");
            for (Human spouse: human.getSpouse()) {
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
}
