package ru.gb.family_tree.model.saving;

import ru.gb.family_tree.model.family_tree.FamilyTree;
import ru.gb.family_tree.model.human.Human;

public interface Saving {
    void save(FamilyTree<Human> familyTree);
    FamilyTree<Human> download(String FileName);
}
