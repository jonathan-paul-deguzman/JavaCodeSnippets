package com.jonathanpaul.javacodesnippets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Main {

    /**
     * Collections interfaces vs. Collections implementations
     *
     * Collections interfaces:
     *      - Multiple data structures
     *      - Functional characteristics (Iteration, Sizes, and Adding/Removing elements)
     *      - This is usually the variable type we use when using Collections
     *      - Often has a popular implementations
     *
     * Collections implementations:
     *      - This is the specific data structure
     *      - Performance characteristics
     *      - Concrete and instantiable
     *
     * Interfaces with their popular implementations:
     *      - List: ArrayList, LinkedList
     *              - Use List if:
     *                      1. Elements do not need keys
     *                      2. Elements are not unique
     *                      3. Elements do not need to be inserted in a specific way (FIFO or LIFO)
     *      - Set: HashSet
     *              - Use Set if:
     *                      1. Elements do not need keys
     *                      2. Elements are unique
     *                      3. Order is not important
     *      - SortedSet: TreeSet
     *              - Use SortedSet if:
     *                      1. Elements do not need keys
     *                      2. Elements are unique
     *                      3. Order is important
     *      - Queue: PriorityQueue
     *              - Use Queue if:
     *                      1. Elements do not need keys
     *                      2. Elements are not unique
     *                      3. Elements should be first in, first out
     *      - Dequeue: LinkedList, ArrayDeque
     *              - Use Dequeue if:
     *                      1. Elements do not need keys
     *                      2. Elements are not unique
     *                      3. Elements should be either FIFO or LIFO
     *      - Map: HashMap
     *              - Use Map if:
     *                      1. Elements need keys
     *                      2. Order is not important
     *      - SortedMap: TreeMap
     *              - Use SortedMap if:
     *                      1. Elements need keys
     *                      2. Order is important
     */

    /**
     * Collection Behaviors:
     *
     * The Collection class extends the Iterable class. The Iterable class defines an Iterator, which is
     * a cursor that can loop through our collection elements.
     *
     * Outline of the Collection Interface:
     *              - size(): Get the number of elements in a collection
     *              - isEmpty(): True if size==0, false otherwise
     *              - add(element): Adds the element at the beginning of this collection
     *              - addAll(collection): Adds all the elements of argument collection to this collection
     *              - remove(element): Remove the element from this collection
     *              - removeAll(collection): Remove all the elements of argument collection from this collection
     *              - retainAll(collection): Remove all elements of this collection not in argument collection
     *              - contains(elements): Returns true if the element is in the collection, false otherwise
     *              - containsAll(collection): Returns true if all the elements of the arg collection is in this collection
     *              - clear(): Remove all elements from this collection
     */

    public static void main(String[] args) {
	    Product door = new Product("Wooden Door", 35);
	    Product floorPanel = new Product("Floor Panel", 25);
	    Product window = new Product("Glass Window", 10);

	    // This is our Collection of Products
        Collection<Product> products = new ArrayList<>();
        products.add(door);
        products.add(floorPanel);
        products.add(window);

        // Note that the preferred way to loop through a Collection is a for-each loop
        // BUT we should use an iterator if we need to REMOVE an element from a Collection or MODIFY it, since
        // a for-each loop does not do this.

        // Creates an iterator to use to loop through our products
        final Iterator<Product> productIterator = products.iterator();
        // If there is another element in the list, hasNext will return true
        while (productIterator.hasNext()) {
            // The next method will grab the next element in the list
            Product product = productIterator.next();
            if (product.getWeight() > 20) {
                System.out.println(product);
            } else {
                productIterator.remove(); // Glass Window should be removed
            }
        }

        // This is how we should loop through a Collection if we do not need to remove elements
        for (Product product : products) {
            System.out.println(product);
//            if (product.getWeight() > 20) {
//                System.out.println(product);
//            } else {
//                // GOTCHA: this will throw a ConcurrentModificationException
//                // We cannot traverse and modify at the same time with a for-each loop
//                products.remove(product); // Can't do this here
//                products.clear(); // Or this
//                products.add(window); // We can't even add to our collection
//            }
        }

        // Now let's look at some of the methods for the Collection interface
        System.out.println(products.size()); // Should return 2
        System.out.println(products.isEmpty()); // Should return false
        System.out.println(products.contains(window)); // Should return false
        System.out.println(products.contains(door)); // Should return true

        Collection<Product> otherProducts = new ArrayList<>();
        otherProducts.add(window);
        otherProducts.add(door);

        products.removeAll(otherProducts); // Should remove window and door from products
        System.out.println(products); // Should only return floor
    }
}
