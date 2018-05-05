package com.jonathanpaul.javacodesnippets;

import java.util.Comparator;

// This is a GENERIC CLASS that implements a GENERIC INTERFACE called Comparator
public class ReverseAgeComparator<T> implements Comparator<T> {

    // Wraps another comparator, a.k.a. our delegateComparator
    private final Comparator<T> delegateComparator;

    public ReverseAgeComparator(final Comparator<T> delegateComparator) {
        this.delegateComparator = delegateComparator;
    }

    @Override
    public int compare(T person1, T person2) {
        return -1 * delegateComparator.compare(person1, person2);
    }
}
