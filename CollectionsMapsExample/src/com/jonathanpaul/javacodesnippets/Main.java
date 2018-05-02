package com.jonathanpaul.javacodesnippets;

import com.jonathanpaul.javacodesnippets.java.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    /**
     * Maps:
     *
     * - Collections of pairs of values (like a dictionary where key=word, value=description)
     * - Maps have unique keys (uniqueness determined by hashCode and equals or comparison and equals)
	 * - Note that keys cannot be mutable or else the map will break
	 *
	 * SortedMaps and Navigable Maps:
	 *
	 * - Sorts by keys in ascending order
	 *
	 * SortedMap: defines an interface for map with ordering
	 *
	 * - Subviews are based upon key
	 * - K firstKey() - returns the first key
	 * - K lastKey() - returns the last key
	 * - SortedMap<K, V> tailMap(E fromKey) - returns a SortedMap view from the fromKey to the last key of map
	 * - SortedMap<K, V> headMap(E toKey) - returns a SortedMap view from the start key to the key before toKey
	 * - SortedMap<K, V> subMap(E fromKey, E toKey) - returns a SortedMap view from the fromKey (inclusive) to the toKey(exclusive)
	 *
	 * NavigableMap: provides additional features to SortedMap
	 *
	 * - Map.Entry<K, V> firstEntry() - returns the first entry (not just the key)
	 * - Map.Entry<K, V> lastEntry() - returns the last entry
	 * - Map.Entry<K, V> pollFirstEntry() - removes and returns the first entry
	 * - Map.Entry<K, V> pollLastEntry() - removes and returns the last entry
	 * - Map.Entry<K, V> lowerEntry(K key) - given a key, return the max entry less than the key (skips keys of equal value)
	 * - Map.Entry<K, V> higherEntry(K key) - given a key, return the min entry greater than the key (skips keys of equal value)
	 * - K lowerKey(K key) - given a key, return the min key greater than the argument key (skips keys of equal value)
	 * - K higherKey(K key) - given a key, return the min key greater than the argument key (skips keys of equal value)
	 * - Map.Entry<K, V> floorEntry(K key) - given a key, return the entry that comes before the key
	 * - Map.Entry<K, V> ceilingEntry(K key) - given a key, return the entry that comes after the key
	 * - K floorKey(K key) - given a key, return the key that comes before the argument key
	 * - K ceilingKey(K key) - given a key, return the key that comes after the argument key
	 * - NavigableMap<K, V> descendingMap() - returns a view of the NavigableMap with descending sort order
	 * - NavigableSet<K> descendingKeySet() - returns a view of a NavigableSet with descending sort order
	 * - NavigableSet<K> navigableKeySet() - returns a NavigableSet that represents the key set
	 * - NavigableMap<K, V> tailMap(E fromKey, boolean inclusive) - returns a NavigableMap view from the fromKey, determines if inclusive or not
	 * - NavigableMap<K, V> headMap(E toKey, boolean inclusive) - returns a NavigableMap view from the start to the toKey, determines if inclusive or not
	 * - NavigableMap<K, V> subMap(E fromKey, boolean fromInclusive, E toKey, boolean toInclusive) - determines if inclusive or not
	 *
	 * Features from Java 8:
	 *
	 * - replace(key, value) - if key is in the map, it's value is replaced with the argument value. returns the old value
	 * - replaceAll(BiFunction<K, V, V>) - updates map based on BiFunction - note BiFunctions are functions that take a key and value, and returns a new value (lambda?)
	 * - remove(key, value) - removes an element if it matches the argument key and argument value
	 * - getOrDefault - like get method, but we can now provide a default value if the element is absent
	 * - putIfAbsent - like the put method, but only puts the element in the map if it was absent beforehand
	 * - compute - takes a key and a function, and lets you update the value for the key based on the result of the update
	 * 		- computeIfAbsent
	 * 		- computeIfPresent
	 * - we can now iterate through a map using foreach, no need to use an entryset!
     */

	/**
	 * Map implementations:
	 *
	 * HashMap
	 *
	 * - O(1) insert, O(1) remove, O(1) search -> all are for average case
	 * - good general purpose map
	 * - uses the .hashcode()
	 * - maintains an array of buckets - hash % bucket_count
	 * - buckets are linked lists to accomodate collisions
	 * - buckets can be trees
	 * - the number of buckets increases with more elements
	 *
	 * LinkedHashMap
	 *
	 * - O(1) insert, O(1) remove, O(1) search
	 * - based on hashmap
	 * - maintains an order (order is based on the order of how to insert or access the keys)
	 * - Useful for implemented caches
	 * - We can even implement a LRU (least recently used) cache using the boolean removeEldestEntry(Map.Entry<K, V> eldest)
	 * - Another good Map to use for caches is WeakHashMap (weak referenced keys are removed by garbage collection when they're not being used by anything)
	 *
	 * TreeMap
	 *
	 * - O(lgN) insert, O(lgN) remove, O(lgN) search
	 * - implemented using a red-black tree (a balanced binary tree)
	 * - navigable and sorted (uses comparable/comparator to define order)
	 * - order is based on the keys themselves
	 *
	 * EnumMap
	 *
	 * - O(1) insert, O(1) remove, O(1) search -> all are for worst case, this is really fast for enums!
	 * - use if your keys are enums
	 * - implementation based on bitsets
	 */

	public static void main(String[] args) {

    	final Product defaultProduct = new Product(-1, "Whatever the customer wants", 100);

    	final Map<Integer, Product> idProductPair = new HashMap<>();
	    idProductPair.put(1, new Product(1, "Wooden Door", 35));
	    idProductPair.put(2, new Product(2, "Floor Panel", 25));
	    idProductPair.put(3, new Product(3, "Glass Window", 10));

	  //  System.out.println(idProductPair);
	  //  System.out.println();

	    // We can get a set view of the keys of a map
		Set<Integer> ids = idProductPair.keySet();
		//System.out.println(ids);
		//System.out.println();

		// Just like the views from List and Set, any modifications to the view will affect the original collection
		// Here, we'll remove 1 from the view and it will then be removed from the map
		// Be careful, we can't use add(K key) because our map doesn't know the value of the specified key
		ids.remove(1);
		//System.out.println(ids);
		//System.out.println(idProductPair);

		// We can also get a view of the values of a map
		// values() returns the Collection interface instead of Set because the values aren't garaunteed
		// to be unique
		// We can also remove from the view like we did above for the keys
		final Collection<Product> products = idProductPair.values();
		//System.out.println(products);
		//System.out.println();

		/**
		 * Entry Set: (no need for this though, just use a foreach)
		 *
		 * - An entry set is the set of the key value entry elements
		 * - Use if you want to iterate through the map
		 * - Use also if you want to use the setValue method
 		 */
		final Set<Map.Entry<Integer, Product>> entries = idProductPair.entrySet();
		for (Map.Entry<Integer, Product> entry : entries) {
			//System.out.println(entry.getKey() + "->" + entry.getValue());
			entry.setValue(new Product(1, "Wooden Door", 35));
		}
		//System.out.println(idProductPair);

		/**
		 * Here's the new way to iterate through a map
		 */
		idProductPair.forEach((key, value) -> { System.out.println(key + "->" + value);} );
		System.out.println();

		// Now let's try out some of the new methods from Java 8
		final Map<Integer, Product> productMap = new HashMap<>();
		productMap.put(1, new Product(1, "Wooden Door", 35));
		productMap.put(2, new Product(2, "Floor Panel", 25));
		productMap.put(3, new Product(3, "Glass Window", 10));

		// Useful for providing default values if nothing is there
		Product result = productMap.getOrDefault(99, defaultProduct); // should return our default product
		System.out.println(result);
		System.out.println(productMap.get(99)); // should return null
		System.out.println();

		// Note that with replace, we can't add anything to the map. Only replace the values
		Product resultReplace = productMap.replace(1, new Product(1, "Bigger Door", 50));
		System.out.println(resultReplace); // should print the old value
		System.out.println(productMap.get(1)); // the map should have the new value and we can print it out with get
		System.out.println();

		// doesn't return anything, but will replace all the elements in a map with this new value (+10 its weight)
		productMap.replaceAll((id, oldProduct) -> new Product(id, oldProduct.getName(), oldProduct.getWeight() + 10));
		System.out.println(productMap);
		System.out.println();

		// this is pretty cool. don't forget the computeIfPresent method as well
		Product resultComputeAbsent = productMap.computeIfAbsent(10, (id) -> new Product(10, "Custom Product", 10));
		System.out.println(resultComputeAbsent); // since there was no key of 10, we created one and gave it a value
		System.out.println(productMap.get(10)); // it is now in the map
		Product resultComputeNotAbsent = productMap.computeIfAbsent(2, (id) -> new Product(2, "Customer Product", 10));
		System.out.println(resultComputeNotAbsent); // since there was already a key of 2, we do not compute anything and this returns the old product
		System.out.println(productMap.get(2)); // nothing was changed here
    }
}
