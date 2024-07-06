package ru.gb.family_tree.view.commands;

import ru.gb.family_tree.view.ConsoleUI;

public class GetFamilyList extends Command{
    public GetFamilyList(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Показать инфо о членах семьи";
    }

    public void execute(){
        consoleUI.getFamilyList();
    }
}