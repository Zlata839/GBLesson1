package ru.gb.family_tree;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Human implements Serializable {

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

        id = -1;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        children = new ArrayList<>();
        spouse = new ArrayList<>();
    }

    public Human (String name, Gender gender, LocalDate birthDate) {
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

    public long getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setDeathDate(LocalDate deathDate) { this.deathDate = deathDate; }
    public LocalDate getDeathDate() { return deathDate; }
    public void setFather(Human father) { this.father = father; }
    public Human getFather() { return father; }
    public void setMother(Human mother) { this.mother = mother; }
    public Human getMother() { return mother; }
    public void setSpouse(List<Human> spouse) { this.spouse = spouse; }
    public List<Human> getSpouse() { return spouse; }
    public void setChildren(List<Human> children) { this.children = children; }
    public List<Human> getChildren() { return children; }
    public Gender getGender() {return gender; }

    @Override
    public String toString() {
        return getHumanInfo();
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
            for (Human spouse: getSpouse()) {
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


