package com.jonathanpaul.javacodesnippets.java;

import java.util.HashMap;
import java.util.Map;

public class MapProductLookupTable implements ProductLookupTable {

    /**
     * Map features:
     *
     * - map is the only collection that doesn't extend or implement the Collections interface. (doesn't abide by contract)
     * - null keys and values are implementation specific. Some allow, some don't.
     *
     * - V put(K key, V value) - puts a single value in the map
     * - void putAll(Map<? extends K, ? extends V> values) - puts another map in the map
     * - V get(Object key) - takes a key and returns the value associate with that key, or null if no key
     * - boolean containsKey(Object key) - returns true if specified key is in the map
     * - boolean containsValue(Object value) - returns true if specified value is in the map
     * - V remove(Object key) - removes value of key and returns that value
     * - void clear() - removes all elements in a map
     * - int size() - returns the size of the map
     * - boolean isEmpty() - returns true if the map is size 0, false otherwise
     */

    private Map<Integer, Product> idProductPair = new HashMap<>();

    @Override
    public Product lookupById(int id) {
        return idProductPair.get(id);
    }

    @Override
    public void addProduct(final Product productToAdd) {
        // If we already have the id in our map, we don't want to add the product
        final int id = productToAdd.getId();
        if (idProductPair.containsKey(id)) {
            throw new IllegalArgumentException("Unable to add product, duplicate id for " + productToAdd);
        }
        idProductPair.put(productToAdd.getId(), productToAdd);
    }

    @Override
    public void clear() {
        idProductPair.clear();
    }
}
