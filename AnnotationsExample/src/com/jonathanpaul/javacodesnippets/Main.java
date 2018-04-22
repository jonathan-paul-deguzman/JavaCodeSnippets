package com.jonathanpaul.javacodesnippets;

public class Main {

    public static void main(String[] args) {
	    OrdinarySword sword = new OrdinarySword();
	    accessingAnnotationsUsingReflection(sword);
    }

    /********************************************************************************************
     * Using annotations
     *
     * Annotations are special types that act as metadata
     *      - Applied to a specific target (i.e. class, method, or field)
     *      - Have no direct impact on target (i.e. does not change target's behavior)
     *
     * Annotations must be interpreted
     *      - Tools/Execution Environment/Programs can look at the metadata and take specific actions towards it
     *
     * Annotations in code
     *      - Name always preceded by @ when used
     *      - Placed directly before target
     *
     * Common annotations
     *      - @Override - Compiler looks for methods marked with this annotation and verifies that there is a method
     *                      with a matching signature that can be overridden.
     *      - @Deprecated - Indicates that a certain method or class is no longer the preferred way to do something.
     *      - @SuppressWarnings - Indicates that you're doing something that could cause a compiler warning and you're
     *                      code already expects that. Use this as close to the place where you want warnings suppressed
     *                      as possible.
     *
     * We can even create our own annotations
     *      - We just do this: public @interface AnnotationName
     *      - Annotations can optionally have elements. These are declared like methods.
     *
     * We can call are custom annotations like we do normal annotations
     *      - If our annotations have elements, we set the values of those elements too when we use the annotation
     *      - Ex. @WorkHandler(useThreadPool = false)
     ********************************************************************************************/

    private static void accessingAnnotationsUsingReflection(Object object) {
        Class<?> theClass = object.getClass();
        WorkHandler handler = theClass.getAnnotation(WorkHandler.class);
        if (!handler.isSpecialItem()) {
            System.out.println("Congrats, you've accessed this annotation's element using reflection");
        }
    }
}
