package ru.gb.family_tree.model.service;

import ru.gb.family_tree.model.family_tree.FamilyTree;
import ru.gb.family_tree.model.family_tree.comparators.FamilyTreeComparatorByDate;
import ru.gb.family_tree.model.family_tree.comparators.FamilyTreeComparatorByName;
import ru.gb.family_tree.model.human.Gender;
import ru.gb.family_tree.model.human.Human;
import ru.gb.family_tree.model.saving.ConsoleSaving;
import ru.gb.family_tree.model.saving.Saving;

import java.time.LocalDate;

public class FamilyTreeService implements Service{

    private FamilyTree<Human> familyTree;
    private Saving saving;

    public FamilyTreeService(Saving saving) {
        this.saving = saving;
        familyTree = new FamilyTree<>("");
    }

    @Override
    public String getFamilyInfo() {
        return familyTree.getFamilyInfo();
    }

    @Override
    public String getHumanInfo(long id) {
        return familyTree.getHumanInfo(findByID(id));
    }

    @Override
    public void createNewFamilyTree(String familyName) {
        familyTree = new FamilyTree<>(familyName);
    }

    public void addInFamily(String name, LocalDate birthDate, Gender gender) {
        Human newHuman = new Human(name, gender, birthDate);
        familyTree.addInFamily(newHuman);
    }

    @Override
    public void addDeathDate(int id, LocalDate deathDate) {
        Human person = findByID(id);
        person.setDeathDate(deathDate);
    }

    @Override
    public void getMarried(int husbandID, int wifeID) {
        Human husband = findByID(husbandID);
        Human wife = findByID(wifeID);
        familyTree.isSpouse(husband, wife);
    }

    @Override
    public void addParents(int childID, int fatherID, int motherID) {
        Human child = findByID(childID);
        Human father = findByID(fatherID);
        Human mother = findByID(motherID);
        familyTree.isChildren(child, father, mother);
    }

    @Override
    public void saveFamilyTree() {
        saving.save(familyTree);
    }

    @Override
    public void downloadFamilyTree(String name) {
        try {
            familyTree = saving.download(name);
            getFamilyInfo();
        } catch (Exception e) {
            System.out.println("Увы, что-то пошло не так...");
        }
    }

    @Override
    public void sortByName() {
        familyTree.sort(new FamilyTreeComparatorByName<>());
    }

    @Override
    public void sortByBirthDate() {
        familyTree.sort(new FamilyTreeComparatorByDate<>());
    }

    @Override
    public Human findByID(long id) {
        Human findHuman = null;
        for (Human human : familyTree) {
            if (id == human.getId()) {
                findHuman = human;
            }
        }
        return findHuman;
    }

    @Override
    public int getTreeSize() {
        return familyTree.size();
    }
}
