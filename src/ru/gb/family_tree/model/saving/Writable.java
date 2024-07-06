package ru.gb.family_tree.model.saving;

import java.io.Serializable;

public interface Writable {
    void writeObject(Serializable serializable, String fileLink);
    Object readObject(String fileLink);
}
