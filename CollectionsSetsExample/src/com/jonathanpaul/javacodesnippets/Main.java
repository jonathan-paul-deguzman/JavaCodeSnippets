package com.jonathanpaul.javacodesnippets;

public class Main {

    /**
     * Key features of a Set:
     *
     * - Sets are collections of distinct elements. There are no duplicates.
     *
     * Set implementations:
     *
     * - HashSet - O(n) add, O(n) search
     *      - Based on HashMap, calls hashCode() on element and looks up location
     *      - Good general purpose implementation, doubles size when it runs out of space
     *      - The Equals/Hashcode contract:
     *              - object.equals(other) -> object.hashCode() == other.hashCode()
     *              - aka the hashCodes must be equal if the object is equal
     *
     * - TreeSet - O(logn) add, O(logn) search
     *      - Based on TreeMap, uses a binary tree with a required sort order
     *      - Need to either put elements into the TreeSet that implement the Comparable interface or
     *        to provide a Comparator to the constructor of the TreeSet
     *
     * - EnumSet O(1) add, O(1) search
     *      - Only allows us to store enum objects, uses a bitset under the hood
     */

    /**
     * Key features of a SortedSet and NavigableSet:
     *
     * - A collection with distinct elements that also have an order
     * - Extends the Set interface
     * - Note that these don't have indices, they're just sorted in some defined order
     * - Both are implemented by TreeSet, but not HashSet
     *
     * SortedSet implementation:
     *
     * - No indices, but works with views (views directly affect the collection)
     * - E first() - returns the first element in the set
     * - E last() - returns the last element in the set
     * - SortedSet<E> tailSet(E fromElement) - returns the elements from fromElement to very end of SortedSet
     * - SortedSet<E> headSet(E toElement) - returns the elements from beginning to end of SortedSet (exclusive)
     * - SortedSet<E> subSet(E fromElement, E toElement) - fromElement (inclusive) to toElement (exclusive)
     *
     * NavigableSet implementation:
     *
     * - Extends SortedSet
     * - Provides ways to move through the order
     * - E lower(E e) - Given the specified element, return the element that preceded it
     * - E higher(E e) - Given the specified element, return the element that comes next
     * - E floor(E e)
     * - E ceiling(E e)
     * - E pollFirst(E e)
     * - E pollLast(E e)
     */

    /**
     * Correct hashcodes
     *
     * - Combine hashcode information from each field to make our hashcode similar in behavior to equals method.
     * - Important for understanding people's hashcode information and information they're combining
     *      - We want both methods to check to see if two objects have the same fields and methods, they're equal
     *      - result = 31 * result + obj.hashCode();
     *      - Array.hashCode(); // call this for each array based field
     *      - (int) (l ^ (l >>> 32)); // call this for each long field (we want the first 32 bits of information then convert to int)
     *      - (l ^ (l >>> 32)); // We can just use this for ints
     *      - Float.floatToIntBits(f);
     * - We can actually let our IDE build our hashcode instead of doing the above
     *      - Right click -> Generate -> equals and hashcode -> verify fields to use
     * - Always use the same fields as equals
     *
     */

    public static void main(String[] args) {
	// write your code here
    }
}
