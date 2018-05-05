package com.jonathanpaul.javacodesnippets;

public class Wizard {

    private String name;
    private int age;

    public Wizard() { }

    public Wizard(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "{name=" + name + ", age=" + age + "}";
    }
}
