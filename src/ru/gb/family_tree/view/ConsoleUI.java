package ru.gb.family_tree.view;

import ru.gb.family_tree.model.human.Gender;
import ru.gb.family_tree.model.saving.ConsoleSaving;
import ru.gb.family_tree.model.saving.Saving;
import ru.gb.family_tree.presenter.Presenter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleUI implements View, ConsoleCommands {
    private static final String INPUT_ERROR = "Что то пошло не так...";
    private Scanner scanner;
    private Presenter presenter;
    private boolean work;
    private MainMenu menu;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        Saving saving = new ConsoleSaving();
        presenter = new Presenter(this, saving);
        work = true;
        menu = new MainMenu(this);
    }

    @Override
    public void printAnswer(String text) {
        System.out.println(text);
    }

    @Override
    public void start() {
        System.out.println("Здравствуйте! Ваш список команд:");
        while (work) {
            printMenu();
            execute();
        }
    }

    @Override
    public void createFamilyTree() {
        System.out.println("Введите навзание вашего древа:");
        String family = scanner.nextLine();
        presenter.createFamilyTree(family);
        System.out.println("Создано новое пустое семейное дерево: " + family);
    }

    @Override
    public void addInFamily() {
        System.out.println("Введите имя:");
        String name = scanner.nextLine();

        System.out.println("Введите дату рождения (формат дд мм гггг):");
        String strBirthDate = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(strBirthDate, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты. Пожалуйста, используйте дд мм гггг.");
            return;
        }

        System.out.println("Введите пол (м/ж):");
        String strGender = scanner.nextLine().toLowerCase();

        Gender gender;
        if (strGender.equals("м")) {
            gender = Gender.Male;
        } else if (strGender.equals("ж")) {
            gender = Gender.Female;
        } else {
            System.out.println("Неверный пол. Доступные варианты: м, ж.");
            return;
        }

        presenter.addInFamily(name, birthDate, gender);
    }

    @Override
    public void getFamilyList() {
        presenter.getFamilyInfo();
    }

    @Override
    public void addDeathDate() {
        presenter.getFamilyInfo();
        System.out.println("Введите ID человека:");
        String deathPerson = scanner.nextLine();
        if (checkTextForInt(deathPerson)) {
            int deathPersonID = Integer.parseInt(deathPerson);
            if (checkID(deathPersonID)) {
                System.out.println("Введите дату смерти (формат дд мм гггг):");
                String strDeathDate = scanner.nextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
                LocalDate deathDate;
                try {
                    deathDate = LocalDate.parse(strDeathDate, formatter);
                    presenter.addDeathDate(deathPersonID, deathDate);
                } catch (DateTimeParseException e) {
                    System.out.println("Неверный формат даты. Пожалуйста, используйте дд мм гггг.");
                }
            }
        }
    }

    @Override
    public void getMarried() {
        presenter.getFamilyInfo();
        System.out.println("Введите ID мужа:");
        String strHusband = scanner.nextLine();
        System.out.println("Введите ID жены:");
        String strWife = scanner.nextLine();
        if (checkTextForInt(strHusband) && checkTextForInt(strWife)) {
            int husbandID = Integer.parseInt(strHusband);
            int wifeID = Integer.parseInt(strWife);
            if (checkID(husbandID) && checkID(wifeID)) {
                presenter.getMarried(husbandID, wifeID);
            }
        }
    }

    @Override
    public void addParents() {
        presenter.getFamilyInfo();
        System.out.println("Введите ID ребенка:");
        String strChild = scanner.nextLine();
        System.out.println("Введите ID отца:");
        String strFather = scanner.nextLine();
        System.out.println("Введите ID матери:");
        String strMother = scanner.nextLine();
        if (checkTextForInt(strChild) && checkTextForInt(strFather) && checkTextForInt(strMother)) {
            int childID = Integer.parseInt(strChild);
            int fatherID = Integer.parseInt(strFather);
            int motherID = Integer.parseInt(strMother);
            if (checkID(childID) && checkID(fatherID) && checkID(motherID)) {
                presenter.addParents(childID, fatherID, motherID);
            }
        }
    }
    @Override
    public void getHumanInfo() {
        presenter.getFamilyInfo();
        System.out.println("Введите ID человека:");
        String strPerson = scanner.nextLine();
        if (checkTextForInt(strPerson)) {
            int personID = Integer.parseInt(strPerson);
            if (checkID(personID)) {
                presenter.getHumanInfo(personID);
            }
        }
    }
    @Override
    public void saveFamilyTree() {
        presenter.saveFamilyTree();
        System.out.println("Семейное дерево сохранено...");
    }
    @Override
    public void downloadFamilyTree() {
        System.out.println("Введите название древа:");
        String name = scanner.nextLine();
        presenter.downloadFamilyTree(name);
        presenter.getFamilyInfo();
    }

    @Override
    public void sortByName() {
        presenter.sortByName();
    }

    @Override
    public void sortByBirthDate() {
        presenter.sortByBirthDate();
    }

    @Override
    public void finish() {
        System.out.println("До новых встреч");
        work = false;
    }

    private void printMenu() {
        System.out.println(menu.menu());
    }
    private void execute() {
        String line = scanner.nextLine();
        if (checkTextForInt(line)) {
            int numCommand = Integer.parseInt(line);
            if (checkCommand(numCommand)) {
                menu.execute(numCommand);
            }
        }
    }
    private boolean checkTextForInt(String text){
        if (text.matches("[0-9]+")){
            return true;
        } else {
            inputError();
            return false;
        }
    }
    private boolean checkCommand(int numCommand) {
        if (numCommand <= menu.getSize()){
            return true;
        } else {
            inputError();
            return false;
        }
    }

    private boolean checkID(int id) {
        if (id >= 1 && id <= presenter.getTreeSize()) {
            return true;
        } else {
            System.out.println("Человека с таким ID не найдено!!");
            return false;
        }
    }

    private void inputError(){
        System.out.println(INPUT_ERROR);
    }
}
