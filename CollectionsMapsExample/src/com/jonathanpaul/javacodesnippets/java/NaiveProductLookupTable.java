package com.jonathanpaul.javacodesnippets.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to show that using an ArrayList for items with keys is very difficult
 */
public class NaiveProductLookupTable implements ProductLookupTable {

    private List<Product> products = new ArrayList<>();

    /**
     * Note that with ArrayLists, we have to loop through it to look at things like the id.
     * This takes O(n) time search. Maps will let us do this same exact things faster.
     */

    @Override
    public Product lookupById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void addProduct(Product productToAdd) {
        // We must first very that the product's id is unique.. this takes extra work with ArrayLists
        for (Product product : products) {
            if (product.getId() == productToAdd.getId()) {
                // If a match is found, then this id is not unique and we should throw an exception
                throw new IllegalArgumentException("Unable to add product, duplicate id for " + productToAdd);
            }
        }

        products.add(productToAdd);
    }

    @Override
    public void clear() {
        products.clear();
    }
}
