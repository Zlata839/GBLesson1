package ru.gb.family_tree.presenter;

import ru.gb.family_tree.model.human.Gender;
import ru.gb.family_tree.model.service.FamilyTreeService;
import ru.gb.family_tree.view.View;

import java.time.LocalDate;

public class Presenter{
    private View view;
    private FamilyTreeService service;

    public Presenter(View view) {
        this.view = view;
        service = new FamilyTreeService();
    }
    public void getFamilyInfo() {
        String info = service.getFamilyInfo();
        view.printAnswer(info);
    }
    public void createFamilyTree(String familyName) {
        service.createNewFamilyTree(familyName);
    }

    public int addInFamily(String name, LocalDate birthDate, Gender gender) {
        int newId = service.addInFamily(name, birthDate, gender);
        view.printAnswer("New member ID: " + newId);
        service.getFamilyInfo();
        return newId;
    }

    public void getMarried(int husbandID, int wifeID) {
        service.getMarried(husbandID, wifeID);
    }
    public void getChild(int childID, int fatherID, int motherID) {
        service.getChild(childID, fatherID, motherID);
    }

    public void GetFamilyList() {
        String info = service.getFamilyInfo();
        view.printAnswer(info);
    }

    public void getHumanInfo(int id) {
        String info = service.getHumanInfo(id);
        System.out.println(info);
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
