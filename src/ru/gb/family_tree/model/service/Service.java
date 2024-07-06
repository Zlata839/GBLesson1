package ru.gb.family_tree.model.service;

import ru.gb.family_tree.model.human.Gender;
import ru.gb.family_tree.model.human.Human;

import java.time.LocalDate;

public interface Service {

    String getFamilyInfo();
    String getHumanInfo(long id);
    void createNewFamilyTree(String familyName);
    int addInFamily(String name, LocalDate birthDate, Gender gender);
    void getMarried(int husbandID, int wifeID);
    void getChild(int childID, int fatherID, int motherID);
    void saveFamilyTree();
    void downloadFamilyTree(String name);
    void sortByName();
    void sortByBirthDate();
    Human findByID(long id);
    int getTreeSize();
}

