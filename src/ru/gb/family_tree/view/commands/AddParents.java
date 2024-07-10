package ru.gb.family_tree.view.commands;

import ru.gb.family_tree.view.ConsoleUI;

public class AddParents extends Command{
    public AddParents(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Добавить родителей";
    }

    public void execute(){
        consoleUI.addParents();
    }
}
