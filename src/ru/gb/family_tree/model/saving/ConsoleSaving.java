package ru.gb.family_tree.model.saving;

import ru.gb.family_tree.model.family_tree.FamilyTree;
import ru.gb.family_tree.model.human.Human;

public class ConsoleSaving implements Saving {
    private static final String LINK = "src/ru/gb/family_tree/model/saving/files/";
    private static final String SAVE_FILE_NAME = "_saveFile.txt";
    public void save(FamilyTree<Human> familyTree) {
        FileHandler fileHandler = new FileHandler();
        String familyName = familyTree.getFamilyName();
        String serializationFile = LINK + familyName + SAVE_FILE_NAME;
        fileHandler.writeObject(familyTree, serializationFile);
    }
    public FamilyTree download(String fileName) {
        FileHandler fileHandler = new FileHandler();
        String serializationFile = LINK + fileName + SAVE_FILE_NAME;
        return (FamilyTree) fileHandler.readObject(serializationFile);
    }
}