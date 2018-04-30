package com.jonathanpaul.javacodesnippets.java;

import java.util.Comparator;

public class Product {

    // Comparator is an interface that defines a single method called compare(T o1, T o2).
    // Returns 0, a negative number, or a positive number if the first argument is equal to, less than
    // or greater than the second argument.
    // We use the Comparator.comparing method to create a Comparator for us to use that accomplishes the
    // above feature.
    public static final Comparator<Product> BY_WEIGHT = Comparator.comparing(Product::getWeight);

    private String name;

    private int weight;

    public Product(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "\'" + ", weight=" + weight + "}";
    }
}
