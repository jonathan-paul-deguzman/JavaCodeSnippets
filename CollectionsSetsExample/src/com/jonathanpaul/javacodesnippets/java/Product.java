package com.jonathanpaul.javacodesnippets.java;

import java.util.Comparator;
import java.util.Objects;

public class Product {

    // We can use this Comparator in tree sets since they require passing in a sort order
    public static final Comparator<Product> BY_NAME = Comparator.comparing(Product::getName);

    // and this one too since we could want to sort items by weight
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return weight == product.weight &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }
}
