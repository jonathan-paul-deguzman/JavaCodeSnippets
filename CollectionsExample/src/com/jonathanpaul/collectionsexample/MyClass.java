package com.jonathanpaul.collectionsexample;

public class MyClass implements Comparable<MyClass> {

    String label, value;

    public MyClass(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String toString() {return label + " | " + value;}

    public boolean equals(Object o) {
        MyClass other = (MyClass) o;
        return value.equalsIgnoreCase(other.value);
    }

    public int compareTo(MyClass other) {
        return value.compareToIgnoreCase(other.value);
    }
}
