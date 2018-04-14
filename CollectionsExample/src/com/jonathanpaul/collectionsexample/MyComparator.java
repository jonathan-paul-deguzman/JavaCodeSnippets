package com.jonathanpaul.collectionsexample;

import java.util.Comparator;

public class MyComparator implements Comparator<MyClass> {

    /*
     * negative value: o1 < o2
     * zero: o1 == o2
     * positive value: o1 > o2
     */
    @Override
    public int compare(MyClass o1, MyClass o2) {
        // For this compare, we want to compare the labels instead of the values
        return o1.label.compareToIgnoreCase(o2.label);
    }
}
