package com.jonathanpaul.javacodesnippets.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Shipment implements Iterable<Product> {

    private static final int LIGHT_VAN_MAX_WEIGHT = 20;

    private static final int PRODUCT_NOT_PRESENT = -1;

    private final List<Product> products = new ArrayList<>();

    private List<Product> lightVanProducts = new ArrayList<>();

    private List<Product> heavyVanProducts = new ArrayList<>();

    public void add(Product product) {
        products.add(product);
    }

    public void replace(Product oldProduct, Product newProduct) {
        final int oldProductIndex = products.indexOf(oldProduct);
        if (oldProductIndex != PRODUCT_NOT_PRESENT) {
            products.set(oldProductIndex, newProduct);
        }
    }

    public void prepare() {
        // Here's out algorithm divided into three steps

        // 1. Sort our list of products by weight
        products.sort(Product.BY_WEIGHT);

        // 2. Find the product index that needs the heavy van
        int splitPoint = findSplitPoint();

        // 3. Assign the views of the product list for light and heavy vans
        lightVanProducts = products.subList(0, splitPoint);
        // Note that we did not have to make the sublist end at products.size() - 1 because sublist
        // is exclusive of that second argument.
        heavyVanProducts = products.subList(splitPoint, products.size());
    }

    private int findSplitPoint() {
        for (int i = 0; i < products.size(); i++) {
            final Product currentProduct = products.get(i);
            if (currentProduct.getWeight() > LIGHT_VAN_MAX_WEIGHT) {
                return i;
            }
        }
        return 0;
    }

    public List<Product> getLightVanProducts() {
        return lightVanProducts;
    }

    public List<Product> getHeavyVanProducts() {
        return heavyVanProducts;
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
