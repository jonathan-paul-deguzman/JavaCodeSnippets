package com.jonathanpaul.javacodesnippets;

import java.util.ArrayList;
import java.util.Collections; // Note that we're using the one with an 's' here because we're using its methods
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //collectionsAlgorithms();
        //collectionsFactories();
        collectionsUtilities();
    }

    private static void collectionsAlgorithms() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Wooden Door", 35));
        products.add(new Product("Floor Panel", 25));
        products.add(new Product("Glass Window", 10));

        System.out.println(products);
        System.out.println();

        // The rotate method takes a list and the distance you want to rotate the list
        // It will take the last element in the list and bring it around to be the first element in the list
        /*Collections.rotate(products, 1);
        System.out.println(products);
        System.out.println();

        Collections.rotate(products, 1);
        System.out.println(products);
        System.out.println();

        Collections.rotate(products, 1);
        System.out.println(products);
        System.out.println();*/

        // The shuffle method shuffles all the elements of a list into random positions
        // Kind of like a deck of cards
        /*Collections.shuffle(products);
        System.out.println(products);
        System.out.println();

        Collections.shuffle(products);
        System.out.println(products);
        System.out.println();

        Collections.shuffle(products);
        System.out.println(products);
        System.out.println();*/

        // Perhaps my favorite of them all is sorting, which takes a Comparator and sorts the list
        Collections.sort(products, Product.BY_NAME);
        System.out.println(products);
        System.out.println();

        Collections.sort(products, Product.BY_WEIGHT);
        System.out.println(products);
        System.out.println();

        // We can also simply sort a list of integers without needing to make a comparator
        List<Integer> listOfInts = new ArrayList<>();
        listOfInts.add(1);
        listOfInts.add(47);
        listOfInts.add(15);
        listOfInts.add(9);

        Collections.sort(listOfInts);
        System.out.println(listOfInts);
        System.out.println();

        // We can even sort a list of Strings!
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("car");
        listOfStrings.add("zebra");
        listOfStrings.add("cable");
        listOfStrings.add("mail");

        Collections.sort(listOfStrings);
        System.out.println(listOfStrings);
        System.out.println();

        // Note that in Java 8, the sort method has been added to the list interface
        // This way may be a little faster and readable so this is preferred if we have access to Java 8
        products.sort(Product.BY_WEIGHT);
        System.out.println(products);
    }

    /**
     * Factories are static methods on the Collections class that will allow us to create a new Collection with specific properties
     */
    private static void collectionsFactories() {
        /**
         * Singleton Factory: collections that only contain a single value
         *  - Immutable single value of collection
         *  - Use when you want to pass a single value into a method that requires a Collection
         *  - For this same purpose, Empty Collections are also valuable when you want to pass no values as opposed to one
         *
         * Unmodifiable Factor Collection: gives us a view around the collection that cannot be modified
         *  - note that it is still mutable, meaning we can still read from it but can't write to it
         *  - our underlying collection can change, but out unmodifiable view cannot
         */

        List<Product> products = new ArrayList<>();
        List<Product> productsCopy = Collections.unmodifiableList(products);
    }

    private static void collectionsUtilities() {
        Product door = new Product("Wooden Door", 35);
        Product floorPanel = new Product("Floor Panel", 25);
        Product window = new Product("Glass Window", 10);

        /**
         * WHAT?! I COULD"VE DONE THIS THE WHOLE TIME?
         */
        List<Product> products = new ArrayList<>();
        Collections.addAll(products, door, floorPanel, window);
        System.out.println(products);
        System.out.println();

        /**
         * Nice! Easy ways to grab the min and max of a Collection
         */
        Product productWithLowestWeight = Collections.min(products, Product.BY_WEIGHT);
        System.out.println(productWithLowestWeight);
        System.out.println();

        Product productWithHighestWeight = Collections.max(products, Product.BY_WEIGHT);
        System.out.println(productWithHighestWeight);
        System.out.println();
    }
}
