package ru.gb.family_tree.saving;

import java.io.Serializable;

public interface Writable {
    void writeObject(Serializable serializable, String fileLink);
    Object readObject(String fileLink);
}
