package com.jonathanpaul.javacodesnippets;

import java.util.Comparator;

public class Product {

    public static final Comparator<Product> BY_NAME = Comparator.comparing(Product::getName);
    public static final Comparator<Product> BY_WEIGHT = Comparator.comparing(Product::getWeight);

    private final String name;
    private final int weight;

    public Product(final String name, final int weight) {
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
        return "Product{name=" + name + ", weight=" + weight + "}";
    }
}
