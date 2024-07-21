package ru.gb.family_tree.presenter;

import ru.gb.family_tree.model.human.Gender;
import ru.gb.family_tree.model.saving.Saving;
import ru.gb.family_tree.model.service.FamilyTreeService;
import ru.gb.family_tree.view.View;

import java.time.LocalDate;

public class Presenter{
    private View view;
    private FamilyTreeService service;

    public Presenter(View view, Saving saving) {
        this.view = view;
        service = new FamilyTreeService(saving);
    }
    public void getFamilyInfo() {
        String info = service.getFamilyInfo();
        view.printAnswer(info);
    }
    public void createFamilyTree(String familyName) {
        service.createNewFamilyTree(familyName);
    }

    public void addInFamily(String name, LocalDate birthDate, Gender gender) {
        service.addInFamily(name, birthDate, gender);
        getFamilyInfo();
    }

    public void addDeathDate(int id, LocalDate deathDate) {
        service.addDeathDate(id, deathDate);
        getFamilyInfo();
    }

    public void getMarried(int husbandID, int wifeID) {
        service.getMarried(husbandID, wifeID);
        getFamilyInfo();
    }
    public void addParents(int childID, int fatherID, int motherID) {
        service.addParents(childID, fatherID, motherID);
        getFamilyInfo();
    }

    public void GetFamilyList() {
        String info = service.getFamilyInfo();
        view.printAnswer(info);
    }

    public void getHumanInfo(long id) {
        String info = service.getHumanInfo(id);
        view.printAnswer(info);
    }
    public void saveFamilyTree() {
        service.saveFamilyTree();
    }
    public void downloadFamilyTree(String name) {
        service.downloadFamilyTree(name);
    }
    public void sortByName() {
        service.sortByName();
        getFamilyInfo();
    }
    public void sortByBirthDate() {
        service.sortByBirthDate();
        getFamilyInfo();
    }
    public int getTreeSize() {
        return service.getTreeSize();
    }
}
