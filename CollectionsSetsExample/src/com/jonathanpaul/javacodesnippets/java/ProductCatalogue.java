package com.jonathanpaul.javacodesnippets.java;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static com.jonathanpaul.javacodesnippets.java.Product.BY_WEIGHT;

public class ProductCatalogue implements Iterable<Product> {

    private static final int LIGHT_VAN_MAX_WEIGHT = 20;

    //private Set<Product> products = new HashSet<>();
    private SortedSet<Product> products = new TreeSet<>(BY_WEIGHT);

    public void isSuppliedBy(Supplier supplier) {
        products.addAll(supplier.products());
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }

    public Set<Product> lightVanProducts() {
        Product heaviestLightVanProduct = findHeaviestLightVanProduct();
        return products.headSet(heaviestLightVanProduct);
    }

    public Set<Product> heavyVanProducts() {
        Product heaviestLightVanProduct = findHeaviestLightVanProduct();
        return products.tailSet(heaviestLightVanProduct);
    }

    private Product findHeaviestLightVanProduct() {
        for (Product product : products) {
            if (product.getWeight() > LIGHT_VAN_MAX_WEIGHT) {
                return product;
            }
        }
        // If no products meet our criteria, just return the last element in the set
        return products.last();
    }
}
