package com.jonathanpaul.javacodesnippets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Wizard harryPotter = new Wizard("Harry Potter", 9);
        Wizard ronaldWeasley = new Wizard("Ronald Weasley", 10);
        Wizard albusDumbledore = new Wizard("Albus Dumbledore", 143);

        List<Wizard> hogwarts = new ArrayList<>();
        Collections.addAll(hogwarts, harryPotter, ronaldWeasley, albusDumbledore);

        Collections.sort(hogwarts, new AgeComparator());
        System.out.println(hogwarts);

        Collections.sort(hogwarts, new ReverseAgeComparator<>(new AgeComparator()));
        System.out.println(hogwarts);

        final Wizard youngestWizard = findYoungestWizard(hogwarts, new AgeComparator());
        System.out.println(youngestWizard);

        // We can also do this for normal Integers
        List<Integer> values = new ArrayList<>();
        Collections.addAll(values, 1, 2, 3);
        // Note: Integer::compare comes in Java 8 and is a method reference to the compare method of the Integer class, which
        // has the same type signature as a Comparator
        System.out.println(findYoungestWizard(values, Integer::compare));

        Slytherin draco = new Slytherin("Cobra");
        Slytherin salazar = new Slytherin("Python");
        Slytherin voldemort = new Slytherin("Basilisk");


        List<Slytherin> slytherins = new ArrayList<>();
        Collections.addAll(slytherins, draco, salazar, voldemort);

        printAll(slytherins); // using our wildcard upperbound in printAll, we can pass in this subclass of Wizard
    }

    // <T> is the type parameter for our generic, which comes right before the return type and is used within the scope of our method
    // T is our return type
    // Note that this is a little messy and we can do the same thing using wildcards (found below)
    public static <T> T findYoungestWizard(List<T> wizards, Comparator<T> comparator) {
        if (wizards.isEmpty()) {
            throw new IllegalArgumentException("List is empty, cannot find wizards.");
        }

        T currentYoungestWizard = wizards.get(0);
        for (T wizard : wizards) {
            currentYoungestWizard = comparator.compare(currentYoungestWizard, wizard) < 0 ? currentYoungestWizard : wizard;
        }

        return currentYoungestWizard;
    }

    /**
     * The verdict:
     *
     * Use T extends Class when you're dealing with class-level generics
     * Use ? extends Class when you're dealing with parameter method-level generics
     */

    // Wildcards deal with bounds.
    // Upper Bounded (extends): List<? extends Wizard>
    // - accepts anything that is a Wizard and also any of its subclasses (a.k.a. Hpuff, Gryff, Rvnclw, Slyth)
    // - use when getting data out of the parameter
    // - covariance
    //
    // Lower Bounded (super): List<? super Wizard>
    // - accepts anything that's a Wizard or anything that's a parent class of Wizard (a.k.a. Person, Objects)
    // - use to put data in the parameter
    // - contravariance
    //
    // Unbounded: List<?>
    // - Use when your method does not care about the type for the generics it takes in
    // - note that this is different from not using generics at all. If you do not even include generics, the compiler
    //   ignore not just the type parameter of a class, but also all generic type parameters as well

    // Note that if we do not define an upper bound here, our method will only take Wizard types and not any of its subclasses
    public static void printAll(List<? extends Wizard> wizards) {
        for (Wizard wizard : wizards) {
            System.out.println("Hi " + wizard.getName());
        }
    }
}
