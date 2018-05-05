package com.jonathanpaul.javacodesnippets;

/**
 * Some review:
 *
 * - reflection is a Java technique that allows you to inspect your application (especially class types) at run time
 * - Lets you ask important questions like:
 *      - What fields are in this class?
 *      - What methods are in this class?
 *      - What's the return type of a given method?
 *      - How many constructors are in this class?
 *      - Who does this class extend?
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Reifiable vs non-reifiable types:
 *
 * - Note: reifiable just means that a type can be reflected (or inspected using reflection)
 *
 * Reifiable types:
 *
 *      - primitives
 *      - Non parameterized class or interface (no generics in class/interface name)
 *      - all type arguments are unbounded wildcards
 *      - raw types (List, Set, Queue, Map)
 *      - arrays of reifiable components
 *
 * Non-reifiable types (Generics are usually non-reifiable):
 *
 *      - Note: we can make them reigiable with Java 8's toGenericString method
 *      - Type variables (T)
 *      - Parameterized Type with Parameters (ArrayList<String>)
 *      - Parameterized Type with bounds (List<? extends Class>)
 */

public class ReflectionExample {

    public void reifiableExamples() {
        // primitives can be examined with reflection
        System.out.println(int.class); // should print out int

        // non parameterized classes can be examined with reflection
        System.out.println(String.class); // should print out class java.lang.String

        // Raw types with unbounded wildcards can be examined with reflection
        List<?> wildcardList = new ArrayList<>();
        System.out.println(wildcardList.getClass()); // should print out class java.util.ArrayList

        List rawList = new ArrayList();
        System.out.println(rawList.getClass()); // should print out class java.util.ArrayList
        System.out.println(rawList.getClass() == wildcardList.getClass()); // note these both have the same underlying type

        System.out.println(int[].class); // should print out class [I
    }

    public void nonReifiableExamples() {
        List<String> stringList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        System.out.println(stringList.getClass()); // should print out class java.util.ArrayList
        System.out.println(integerList.getClass()); // should print out class java.util.ArrayList
        System.out.println(stringList.getClass() == integerList.getClass()); // Note that reflection will consider these two the same when they're obviously not

        List<? extends Number> numberList = new ArrayList<>();
        System.out.println(stringList.getClass() == numberList.getClass()); // here too

        List<String> stringListReified = new ArrayList<>();
        Class<?> arrayList = stringListReified.getClass();
        System.out.println(arrayList.toGenericString()); // should print class java.util.ArrayList<E>
    }
}
