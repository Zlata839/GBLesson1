package ru.gb.family_tree.view.commands;

import ru.gb.family_tree.view.ConsoleUI;

public class GetMarried extends Command{
    public GetMarried(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Поженить двух людей по id";
    }

    public void execute(){
        consoleUI.getMarried();
    }
}
