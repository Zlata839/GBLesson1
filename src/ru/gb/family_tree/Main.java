package ru.gb.family_tree;

import ru.gb.family_tree.family_tree.FamilyTree;
import ru.gb.family_tree.human.Gender;
import ru.gb.family_tree.human.Human;
import ru.gb.family_tree.saving.FileHandler;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        String fileLink = "src/ru/gb/family_tree/saving/files/tree.txt";

//        FamilyTree tree = read(fileLink);

        FamilyTree tree = testTree();
        System.out.println(tree);

        save(tree, fileLink);
    }

        static FamilyTree read(String fileLink){
            FileHandler fileHandler = new FileHandler();
            return(FamilyTree) fileHandler.readObject(fileLink);
        }

        static void save(FamilyTree tree, String fileLink){
            FileHandler fileHandler = new FileHandler();
            fileHandler.writeObject(tree, fileLink);
        }

        static FamilyTree testTree(){
        FamilyTree tree = new FamilyTree(1);

        Human igor = new Human ("Игорь Федорович", Gender.Male,
                LocalDate.of(1933, 6, 22),
                LocalDate.of(2008, 7, 22));

        Human anna = new Human("Анна Андреевна", Gender.Female,
                LocalDate.of(1940, 1, 1),
                LocalDate.of(2019, 8, 18));

        tree.addInFamily(igor);
        tree.addInFamily(anna);

        tree.isSpose(igor, anna);


        Human kirill = new Human("Кирилл Игорьевич", Gender.Male,
                LocalDate.of(1959, 3, 19),
                null);

        tree.addInFamily(kirill);

        tree.isChildren(kirill, igor, anna);

        Human maria = new Human("Мария Ильинична", Gender.Female,
                LocalDate.of(1961, 4, 11),
                null);

        tree.addInFamily(maria);

        tree.isSpose(kirill, maria);

        Human petr = new Human("Петр Кириллович", Gender.Male,
                LocalDate.of(1990, 7, 6),
                null);

        tree.addInFamily(petr);
        tree.isChildren(petr, kirill, maria);

        System.out.println(tree.getHumanInfo());
        tree.sortByName();
        System.out.println(tree.getHumanInfo());
        tree.sortByBirthDate();

        return tree;
    }
}
