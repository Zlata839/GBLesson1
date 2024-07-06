package ru.gb.family_tree.model.human;

import ru.gb.family_tree.model.family_tree.FamilyTreeItems;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Human implements Serializable, Comparable<Human>, FamilyTreeItems<Human> {

    private long id;
    private String name;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private Gender gender;
    private Human father;
    private Human mother;
    private List<Human> children;
    private List<Human> spouse;

    public Human(String name, Gender gender, LocalDate birthDate, LocalDate deathDate) {

        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        children = new ArrayList<>();
        spouse = new ArrayList<>();
    }

    public Human(String name, Gender gender, LocalDate birthDate) {
        this(name, gender, birthDate, null);
    }

    public int getAge() {
        if (deathDate == null) {
            return getPeriod(birthDate, LocalDate.now());
        } else {
            return getPeriod(birthDate, deathDate);
        }
    }

    private int getPeriod(LocalDate birthDate, LocalDate deathDate) {
        Period diff = Period.between(birthDate, deathDate);
        return diff.getYears();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    @Override
    public void setFather(Human father) {
        this.father = father;
    }

    @Override
    public Human getFather() {
        return father;
    }

    @Override
    public void setMother(Human mother) {
        this.mother = mother;
    }

    @Override
    public Human getMother() {
        return mother;
    }

    @Override
    public void setSpouse(List<Human> spouse) {
        this.spouse = spouse;
    }

    @Override
    public List<Human> getSpouse() {
        return spouse;
    }

    @Override
    public void setChildren(List<Human> children) {
        this.children = children;
    }

    @Override
    public List<Human> getChildren() {
        return children;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return getHumanInfo();
    }

    @Override
    public int compareTo(Human o) {
        return name.compareTo(o.getName());
    }
    private String getHumanInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t");
        stringBuilder.append(name);
        stringBuilder.append(" (");
        stringBuilder.append(gender);
        stringBuilder.append(", возраст:");
        stringBuilder.append(getAge());
        stringBuilder.append(")\n\t(");
        stringBuilder.append(birthDate.getYear());
        stringBuilder.append(" - ");
        if (deathDate == null) {
            stringBuilder.append("по н.в.");
        } else {
            stringBuilder.append(deathDate.getYear());
        }
        stringBuilder.append(", ");
        stringBuilder.append(getSpouseInfo());
        stringBuilder.append(", ");
        stringBuilder.append(getMotherInfo());
        stringBuilder.append(", ");
        stringBuilder.append(getFatherInfo());
        stringBuilder.append(", ");
        stringBuilder.append(getChildreInfo());
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public String getSpouseInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!getSpouse().isEmpty()) {
            stringBuilder.append("Супруг(а):");
            for (Human spouse : getSpouse()) {
                if (spouse.getGender() == Gender.Male) {
                    stringBuilder.append(" ");
                } else {
                    if (spouse.getGender() == Gender.Female)
                        stringBuilder.append(" ");
                }
                stringBuilder.append(spouse.getName());
            }
        }
        return stringBuilder.toString();
    }

    public String getMotherInfo() {
        String res = "мать: ";
        Human mother = getMother();
        if (mother != null) {
            res += mother.getName();
        } else {
            res += "неизвестна";
        }
        return res;
    }

    public String getFatherInfo() {
        String res = "отец: ";
        Human father = getFather();
        if (father != null) {
            res += father.getName();
        } else {
            res += "неизвестен";
        }
        return res;
    }

    public String getChildreInfo() {
        StringBuilder res = new StringBuilder();
        res.append("дети: ");
        if (children.size() != 0) {
            res.append(children.get(0).getName());
            for (int i = 1; i < children.size(); i++) {
                res.append(", ");
                res.append(children.get(i).getName());
            }
        } else {
            res.append("отсутствуют");
        }
        return res.toString();
    }
}


