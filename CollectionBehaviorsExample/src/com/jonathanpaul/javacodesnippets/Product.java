package com.jonathanpaul.javacodesnippets;

public class Product {
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
