package ru.gb.family_tree;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public interface Writable {
    void writeObject(Serializable serializable, String fileLink);
    Object readObject(String fileLink);
}
