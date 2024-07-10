package ru.gb.family_tree.model.service;

import ru.gb.family_tree.model.human.Gender;
import ru.gb.family_tree.model.human.Human;

import java.time.LocalDate;

public interface Service {

    String getFamilyInfo();
    String getHumanInfo(long id);
    void createNewFamilyTree(String familyName);
    void addInFamily(String name, LocalDate birthDate, Gender gender);
    void addDeathDate(int id, LocalDate deathDate);
    void getMarried(int husbandID, int wifeID);
    void addParents(int childID, int fatherID, int motherID);
    void saveFamilyTree();
    void downloadFamilyTree(String name);
    void sortByName();
    void sortByBirthDate();
    Human findByID(long id);
    int getTreeSize();
}

