package com.jonathanpaul.javacodesnippets;

// The following is type bounds. This essentially says that we don't want our SortedPair to take just any type T,
// instead we want it to be the same type as another T. When we extend Comparable, we want to compare it to itself.
// Therefore, we can only compare an object with a T, not just any other random object.
// Note we don't need to implement Comparable here, we're just providing the bounds on the class type itself.
public class SortedPair<T extends Comparable<T>> {

    private final T first;
    private final T second;

    public SortedPair(T left, T right) {
        if (left.compareTo(right) < 0) {
            first = left;
            second = right;
        } else {
            first = right;
            second = left;
        }
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }
}
