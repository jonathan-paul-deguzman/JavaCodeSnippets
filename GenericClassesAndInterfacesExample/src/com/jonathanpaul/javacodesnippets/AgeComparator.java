package com.jonathanpaul.javacodesnippets;

import java.util.Comparator;

public class AgeComparator implements Comparator<Wizard> {


    /**
     * Compares the age of two Wizard objects
     *
     * @param wizard1 the first Wizard object to be compared
     * @param wizard2 the second Wizard object to be compared
     * @return a negative integer, zero, or a positive integer as the
     *         first argument is less than, equal to, or greater than the
     *         second.
     */
    public int compare(final Wizard wizard1, final Wizard wizard2) {
        return Integer.compare(wizard1.getAge(), wizard2.getAge());
    }
}
