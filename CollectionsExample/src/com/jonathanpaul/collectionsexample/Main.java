package com.jonathanpaul.collectionsexample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

    /*
     * Collections hold and organize values. There is a wide variety of collections available.
     *
     * Common Collection Interfaces
     * Collection - Basic collection operations
     * List - Collection that maintains a particular order
     * Queue - Collection with the concept of order and specific "head" element
     * Set - Collection that contains no duplicate values
     * SortedSet - A set whose members are sorted
     *
     * Common Collection Classes
     * ArrayList - A List backed by a resizeable array
     *      Efficient random access but inefficient random inserts
     * LinkedList - A List and Queue backed by a doubly-linked list
     *      Efficient random insert but inefficient random access
     * HashSet - A set implemented as a hash table
     *      Efficient general purpose usage at any size
     * TreeSet - A SortedSet implemented as a balanced binary tree (maintains sorting)
     *      Members accessible in order but less efficient to modify and search than a HashSet
     */
    public static void main(String[] args) {
        //collectionsAndTypeSafety();
        //commonCollectionMethods();
        //java8CollectionMethods();
        //convertingBetweenCollectionsAndArrays();
        //sortingCollections();
        //mapCollections();
        sortedMapCollections();
    }

    /*
     * Collections use generics for type safety. Compiler ensures the correct type is used.
     */
    public static void collectionsAndTypeSafety() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Foo");
        list.add("Bar");

        for (String item : list) {
            System.out.println(item);
        }

        String firstItem = list.get(0);
        System.out.println("The first item in the ArrayList is: " + firstItem);
    }

    /*
     * Common Collection Methods:
     * size - Returns number of elements
     * clear - Removes all elements
     * isEmpty - Returns true if no elements
     * add - Add a single element
     * addAll - Add all members of another collection
     * contains - Returns true if contains element
     * containsAll - Returns true if contains all members of another collection
     * remove - Remove element
     * removeAll - Remove all elements contained in another collection
     * retainAll - Remove all elements not contained in another collection
     */
    public static void commonCollectionMethods() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Foo");
        list.add("Bar");

        System.out.println("size(): " + list.size());
        list.clear();
        System.out.println("size(): " + list.size());
        System.out.println("isEmpty(): " + list.isEmpty());

        list.add("Coo");
        list.add("Faz");

        LinkedList<String> list2 = new LinkedList<>();
        list2.add("Baz");
        list2.add("Boo");

        // Add all members from the LinkedList we just created
        list.addAll(list2);

        for (String item : list) {
            System.out.println(item);
        }

        System.out.println("contains : " + list.contains("Baz"));
        System.out.println("containsAll: " + list.containsAll(list2));
    }

    /*
     * Java 8 introduces lambda expressions (lambdas basically pass code as arguments)
     * Lambdas make it easy for us to run blocks of code across members of our collections.
     *
     * Collection methods that leverage lambdas:
     * forEach - perform code for each member
     * removeIf - remove element if test is true
     */
    public static void java8CollectionMethods() {
        ArrayList<String> list = new ArrayList<>();
        list.add("abc");
        list.add("xyz");
        list.add("abc");

        // Passing in m (represents the current member in collection) into this code
        // Prints true if the current member contains a
        list.forEach(m -> System.out.println(m.contains("a")));

        // Removes the member from the collection if it equals abc
        list.removeIf(m -> m.equals("abc"));
        for (String item : list) {
            System.out.println(item);
        }
    }

    /*
     * Collection interface can return an array using the toArray method
     * toArray() - Returns Object array
     * toArray(T[] array) - Returns an array of type T
     *
     * Array content can also be retrieved as a collection using the asList method
     */
    private static void convertingBetweenCollectionsAndArrays() {
        ArrayList<String> list = new ArrayList<>();
        list.add("abc");
        list.add("xyz");
        list.add("abc");

        Object[] objArray = list.toArray();
        for(Object item : objArray) {
            System.out.println(item);
        }

        // The argument indicates the type of array we want back
        String[] stringArray = list.toArray(new String[0]);
        for(String item : stringArray) {
            System.out.println(item);
        }

        // Since there's space to hold the members here, toArray will use the passed in array
        String[] stringArray2 = new String[3];
        String[] stringArray3 = list.toArray(stringArray2);

        if (stringArray == stringArray2) {
            System.out.println("string1 and string2 do not reference the same array");
        }

        if (stringArray2 == stringArray3) {
            System.out.println("string2 and string3 reference the same array");
        }

        String[] myArray = {"dog", "cat", "cow"};
        Collection<String> collectionList = Arrays.asList(myArray);
        collectionList.forEach(m -> System.out.println(m));
    }

    /*
     * Two ways to specify sort behavior
     * Comparable interface - implemented by the type to be sorted
     * Comparator interface - implemented by the type to perform sort
     */
    private static void sortingCollections() {
        TreeSet<MyClass> tree = new TreeSet<>();
        tree.add(new MyClass("2222", "ghi"));
        tree.add(new MyClass("3333", "abc"));
        tree.add(new MyClass("1111", "def"));

        // The tree should have already be sorted by value
        tree.forEach(m -> System.out.println(m));

        System.out.println();

        TreeSet<MyClass> treeComparator = new TreeSet<>(new MyComparator());
        treeComparator.add(new MyClass("2222", "ghi"));
        treeComparator.add(new MyClass("3333", "abc"));
        treeComparator.add(new MyClass("1111", "def"));

        // The tree should have already be sorted by label
        treeComparator.forEach(m -> System.out.println(m));
    }

    /*
     * Maps store key-value pairs where each key is used to identify values.
     * Keys must be unique, but values can be duplicated.
     *
     * Common Map Interfaces
     * Map - Basic map operations
     * SortedMap - Maps whose keys are sorted
     *
     * Common Map Classes
     * HashMap - Efficient general purpose Map implementation
     * TreeMap - SortedMap implemented as a self-balancing tree. Support Comparable and
     *      Comparator sorting.
     *
     * Common Map Methods:
     * put - Add key and value
     * putIfAbsent - Add key and value if not contained or if value null
     * get - Return value for key, if key is not found return null
     * getOrDefault - Return value for key, if key is not found return provided default value
     * values - Return a Collection of the contained values
     * keySet - Return a Set of the contained keys
     * forEach - (for lambda) Perform action for each entry
     * replaceAll - (for lambda) Perform action for each entry replacing each key's value with the
     *      action's result.
     */
    public static void mapCollections() {
        Map<String, String> map = new HashMap<>();
        map.put("2222", "ghi");
        map.put("3333", "abc");
        map.put("1111", "def");

        // Should return abc
        System.out.println(map.get("3333"));

        // Should return null
        System.out.println(map.get("9999"));

        // Should return the default value, which is xyz
        System.out.println(map.getOrDefault("9999", "xyz"));

        // Should print out each element of Map. This looks a lot cleaner than a for loop
        map.forEach((k, v) -> System.out.println(k + " | " + v) );

        // This lambda expression uses a bi-function which takes 2 parameters and returns a value
        // The code will run, receive the key and value, and then return what we want the new value to be
        // Now the map will apply the new value to the entry key
        // Here, we will upper case all values and assign it back to its key
        map.replaceAll((k, v) -> v.toUpperCase());
        map.forEach((k, v) -> System.out.println(k + " | " + v) );
    }

    /*
     * Common SortedMap Methods
     * firstKey - Returns first key
     * lastKey - Returns last key
     * headMap - Returns an exclusive map for all keys that are less than the specified key
     * tailMap - Returns a inclusive map for all the keys that are greater than the specified key
     * subMap - Returns a map for all the keys that are greater than or equal to the starting key,
     *      but less than the ending key
     */
    public static void sortedMapCollections() {
        SortedMap<String, String> map = new TreeMap<>();
        map.put("2222", "ghi");
        map.put("3333", "abc");
        map.put("1111", "def");
        map.put("6666", "xyz");
        map.put("4444", "mno");
        map.put("5555", "pqr");

        // Since this is a TreeMap, it is a self-balancing tree
        // Since this is also a SortedMap, its keys are sorted
        map.forEach((k, v) -> System.out.println(k + " | " + v));

        System.out.println();

        // Should return a map with keys that are exclusively less than 3333
        //      Should return 1111 and 2222
        SortedMap<String, String> hMap = map.headMap("3333");
        hMap.forEach((k, v) -> System.out.println(k + " | " + v));

        System.out.println();

        // Should return a map with keys that are inclusively greater than 3333
        //      Should return 3333, 4444, 5555 and 6666
        SortedMap<String, String> tMap = map.tailMap("3333");
        tMap.forEach((k, v) -> System.out.println(k + " | " + v));

        System.out.println();

        // I guess it just returns all keys greater than the specified value even if the key doesn't exist
        // This could be helpful for comparing key values
        SortedMap<String, String> tMap2 = map.tailMap("4000");
        tMap2.forEach((k, v) -> System.out.println(k + " | " + v));

        System.out.println();

        // Should return 2222, 3333, 4444, 5555
        SortedMap<String, String> sMap = map.subMap("2222", "6666");
        sMap.forEach((k, v) -> System.out.println(k + " | " + v));
    }
}
