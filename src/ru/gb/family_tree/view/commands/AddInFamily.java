package ru.gb.family_tree.view.commands;

import ru.gb.family_tree.view.ConsoleUI;

public class AddInFamily extends Command{
    public AddInFamily(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Добавить члена семьи";
    }
    @Override
    public void execute() {
        consoleUI.addInFamily();
    }
}
