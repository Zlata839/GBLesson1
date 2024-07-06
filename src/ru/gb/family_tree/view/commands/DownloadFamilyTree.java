package ru.gb.family_tree.view.commands;

import ru.gb.family_tree.view.ConsoleUI;

public class DownloadFamilyTree extends Command{
    public DownloadFamilyTree(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Загрузить сохраненное семейное дерево";
    }

    public void execute(){
        consoleUI.downloadFamilyTree();
    }
}