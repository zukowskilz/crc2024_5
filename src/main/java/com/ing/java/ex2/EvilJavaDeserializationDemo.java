package com.ing.java.ex2;

import com.ing.java.ex2.data.Person;
import com.ing.java.ex2.evil.EvilPerson;

import java.io.*;

public class EvilJavaDeserializationDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // serialize evil class
        EvilPerson evilPerson = new EvilPerson();
        doSerialize(evilPerson, "evilPerson.ser");

        //serialize data
        Person person = new Person("Foo", 30);
        doSerialize(person, "person.ser");

        noProtectionDeserialize("person.ser");
        protectedDeserialize("person.ser");
    }

    private static void noProtectionDeserialize(String fileName) throws IOException, ClassNotFoundException {
        //deserialize
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Person newPerson = (Person) ois.readObject(); // lookup for
        System.out.println(newPerson);
        ois.close();
    }

    private static void protectedDeserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);

        // filter only Person
        var filter = ObjectInputFilter.Config.createFilter("com.ing.java.ex2.data.*;!*");
//        ObjectInputFilter.Config.setSerialFilter(filter); // for entire application
        ois.setObjectInputFilter(filter);

        Person newPerson = (Person) ois.readObject();
        System.out.println(newPerson);
        ois.close();
    }

    private static void doSerialize(Object person, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(person);
        oos.close();
    }
}
