package com.jonathanpaul.javacodesnippets.java;

import java.util.HashSet;
import java.util.Set;

public class Supplier {

    private String name;

    Set<Product> productSet = new HashSet<>();

    public Supplier(String name) {
        this.name = name;
    }

    public Set<Product> products() {
        return productSet;
    }
}
