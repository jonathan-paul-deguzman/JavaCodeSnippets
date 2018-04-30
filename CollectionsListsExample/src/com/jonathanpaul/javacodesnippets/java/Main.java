package com.jonathanpaul.javacodesnippets.java;

public class Main {

    /**
     * Key features of a list:
     *
     * - Lists are collections with a clearly defined iteration order (each element has an index)
     *
     * - We can modify the list using its indices using these methods:
     *      - void add(int index, E e) - add e at the index position and shift up other elements by 1
     *      - E get(int index) - move to the specified index and extract the element at that index
     *      - E remove(int index) - delete the element at a given index and return the its value. shift down by 1
     *      - E set(int index, E e) - replace the element at the specified index with e and shift down by 1
     *      - boolean addAll(int index, Collection<? extends E> c) .. note: look into that generic syntax
     *
     * - We can also look up values by its index using these methods:
     *      - int indexOf(Object o) - return the first index of the specified object, -1 if it's not there
     *      - int lastIndexOf(Object o) - return the last index of the specified object (given there's multiple), -1 if it's not there
     *      - Note: fromIndex is inclusive and toIndex is exclusive (like a for loop)
     *
     * - Modifying the view modifies the list
     *      - List<E> subList(int fromIndex, int toIndex) - return sublist within the specified indices
     *              - A sublist is a view. If you add an element to your view, that'll be added in to the list
     *              that it is a view of.
     */

    /**
     * List implementations:
     *
     * - ArrayLists - O(1) access, O(n) search, O(n) add, amortized O(1) add b/c (a), O(n) remove
     *      - When we hit the end of our list, our ArrayList doubles the size of our backing array
     *              - (a) This means that resizing is less frequent as our list grows
     *      - It's less efficient to add nodes to the middle/start of the list as we need to shift following elements up
     *      - Good general purpose implementation when we don't know what to use
     *      - More CPU cache sympathetic (it's an array backed by a list, so it's linearly laid out in memory)
     *
     * - LinkedLists - O(n) access, O(n) search, O(1) add, O(1) remove
     *      - Java uses a doubly LinkedList -> Each node in a LL has a pointer to its next element. previous element,
     *        and the value that's stored there and the LL itself keeps a reference to the head and tail of the LL.
     *      - Traversing a LL is slow because you have to travel the pointers and go from node to node
     *      - However, adding a node is fast because we just need to re-arrange the pointers (especially nodes in the middle)
     *      - It's really only useful when adding elements at start because we don't have to do any shifting
     *      - It's also useful when we are doing many adds/removes
     *      - Don't use when we have to do any type of iteration through our collection
     *
     *
     */

    public static void main(String[] args) {
	// write your code here
    }
}
