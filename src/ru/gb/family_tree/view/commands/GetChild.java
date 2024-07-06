package ru.gb.family_tree.view.commands;

import ru.gb.family_tree.view.ConsoleUI;

public class GetChild extends Command{
    public GetChild(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Добавить ребенка родителям";
    }

    public void execute(){
        consoleUI.getChild();
    }
}
