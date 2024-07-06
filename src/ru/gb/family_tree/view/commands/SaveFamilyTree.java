package ru.gb.family_tree.view.commands;

import ru.gb.family_tree.view.ConsoleUI;

public class SaveFamilyTree extends Command{
    public SaveFamilyTree(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Сохранить текущее семейное дерево";
    }

    public void execute(){
        consoleUI.saveFamilyTree();
    }
}
