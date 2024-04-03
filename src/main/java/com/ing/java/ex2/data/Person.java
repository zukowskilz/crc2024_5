package com.ing.java.ex2.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {
    private final String name;
    private final int age;
}
