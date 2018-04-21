package com.jonathanpaul.javacodesnippets;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {

    /*
     * Reflection lets us do 2 things:
     *      1. Examine types at runtime
     *      2. Dynamically executes and access members of types
     *
     * Using reflection, we can fully examine objects at runtime:
     *      1. We can determine an object's type and the types it's extending
     *      2. We determine the interfaces implemented
     *      3. We can determine what its members are (fields and method names)
     *
     * Using reflection, we can access the full capabilities of a type:
     *      1. We can construct instances of a type that we didn't know about when we run our program
     *      2. We can access fields of that type
     *      3. We can call methods on that type
     */

    public static void main(String[] args) {
        Item item1 = new Item();
        Sword sword1 = new Sword();

        // All these different methods of getting a class instance will actually point
        // to the same exact class instance of the Class class. This means we get the exact same
        // type information back from all these methods.
        //getClassInstanceFromTypeReference(item1);
        //getClassInstanceFromStringName();
        //getClassInstanceFromTypeLiteral();
        //typeModifiers(item1);
        //getFieldInfo(sword1);
        //getMethodInfo(sword1);
        callGetId(sword1);
    }

    /******************************************************************************************
     * Getting class instances
     ******************************************************************************************/
    private static void getClassInstanceFromTypeReference(Object object) {
        // using getClass will give us the class descriptor for that type
        // remember that the class description contains class info like class name and members
        Class<?>  classDescriptor = object.getClass();
        showName(classDescriptor);
    }

    private static void getClassInstanceFromStringName() {
        try {
            Class<?> classDescriptor = Class.forName("com.jonathanpaul.javacodesnippets.Item");
            showName(classDescriptor);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void getClassInstanceFromTypeLiteral() {
        Class<?> classDescriptor = Item.class;
        showName(classDescriptor);
    }

    private static void showName(Class<?> theClass) {
        System.out.println(theClass.getSimpleName());
    }

    /******************************************************************************************
     * Retrieving types
     ******************************************************************************************/
    private static void typeModifiers(Object object) {
        Class<?> theClass = object.getClass();
        // returned as a single int value, each modifier is a separate bit
        int modifiers = theClass.getModifiers();

        if ((modifiers & Modifier.FINAL) > 0) {
            System.out.println("is final");
        } else {
            System.out.println("is not final");
        }

        // Modifier class interprets modifiers by providing static fields and methods
        if (Modifier.isPrivate(modifiers)) {
            System.out.println("is private");
        } else if (Modifier.isProtected(modifiers)) {
            System.out.println("is protected");
        } else if (Modifier.isPublic(modifiers)) {
            System.out.println("is public");
        }
    }

    /******************************************************************************************
     * Accessing member information
     *
     * For accessing the type's declared members only:
     *      - We can get public, protected, and private members using these methods
     *              1. getDeclaredFields - returns any declared fields
     *              2. getDeclaredMethods - returns any declared methods
     *              3. getDeclaredConstructors - returns any declared constructors
     *
     * For accessing type's declared and inherited members:
     *      - We can only get public members using these methods
     *              1. getFields
     *              2. getMethods
     *              3. getConstructors
     *      - To get specific members, we have to request them by signature
     *              1. getField - pass name
     *              2. getMethod - pass name plus parameter types
     *              3. getConstructors - pass parameter types
     ******************************************************************************************/
    private static void getFieldInfo(Object object) {
        Class<?> theClass = object.getClass();

        // We can return any public members from both Sword and Item
        // Since we didn't define any public fields in the Sword or Item class, nothing is returned
        Field[] fields = theClass.getFields();
        System.out.println("Displaying Fields");
        displayFields(fields);

        // We can return everything that was declared in Sword, but not in Item
        Field[] declaredFields = theClass.getDeclaredFields();
        System.out.println("Displaying Declared Fields");
        displayFields(declaredFields);
    }

    private static void displayFields(Field[] fieldArr) {
        for (Field field : fieldArr) {
            System.out.println(field.getName() + " : " + field.getType());
        }
    }

    private static void getMethodInfo(Object object) {
        Class<?> theClass = object.getClass();

        // Note that since this getMethods gets the methods of all the classes the object has inherited from,
        // this can stem all the way up to even the Object class, which has many public methods
        //Method[] methods = theClass.getMethods();

        // However, we can easily exclude a particular class
        Method[] methods = theClass.getMethods();
        System.out.println("Displaying Methods (Inside sword class and parent classes, excluding Object)");
        for (Method method : methods) {
            if (method.getDeclaringClass() != Object.class) {
                System.out.println(method.getName() + " : " + method.getReturnType());
            }
        }

        System.out.println("\n\n");

        Method[] declaredMethods = theClass.getDeclaredMethods();
        System.out.println("Displaying Declared Methods (Inside sword class)");
        displayMethodInfo(declaredMethods);
    }

    private static void displayMethodInfo(Method[] methodArr) {
        for (Method method : methodArr) {
            System.out.println(method.getName() + " : " + method.getReturnType());
        }
    }

    // We're going to use Reflection here to access and interact with the members of a type (the sword class)
    private static void callGetId(Object object) {
        Class<?> theClass = object.getClass();

        try {
            // Gets the slash method
            Method method = theClass.getMethod("slash");
            // and returns the result of that method
            Object result = method.invoke(object);
            System.out.println(result.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /******************************************************************************************
     * Instance creation with reflection
     *
     * Objects can be created with reflection
     *      - Constructors can be executed
     *              - Use Constructor newInstance method
     *              - Creates a new instance of the object and returns a reference to it
     *      - Simplified handling for no-arg constructor
     *              - No need to access constructor directly
     *              - Use Class newInstance method
     *
     * Flexible work dispatch system
     *      - Executes worker classes against target
     *      - Build this in a way where we don't need to know what all the workers classes are
     *      - Build this in a way where we only need to class path (workerTypeName) and the target
     *      - Method to start work accepts 2 arguments
     *              1. Name of worker type - received as a String reference
     *              2. Target of work - received as Object reference
     ******************************************************************************************/
    private static void startWork(String workerTypeName, Object workerTarget) {
        try {
            // Get type information for our worker
            Class<?> workerType = Class.forName(workerTypeName);
            // Construct an instance of that worker
            Class<?> targetType = workerTarget.getClass();
            Constructor constructor = workerType.getConstructor(targetType);
            Object worker = constructor.newInstance(workerTarget);
            // Get the information on the method
            Method doWork = workerType.getMethod("doWork");
            // Call the method
            doWork.invoke(worker);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
