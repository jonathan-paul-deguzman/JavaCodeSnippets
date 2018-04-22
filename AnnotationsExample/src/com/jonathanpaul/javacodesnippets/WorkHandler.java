package com.jonathanpaul.javacodesnippets;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***********************************************************************************************************************
 * Annotation retention
 *
 * 3 types of retention:
 *      1. SOURCE - Exists only in source file. Discarded by compiler.
 *      2. CLASS - Compiled into class file. Discarded by runtime.
 *      3. RUNTIME - Loaded into Runtime. Accessible with reflection.
 ***********************************************************************************************************************/

/***********************************************************************************************************************
 * Annotations can narrow allowable targets
 *
 *      - Part of annotation declaration
 *      - Indicated with types annotation
 *              - Accepts ElementType value
 ***********************************************************************************************************************/

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD}) // will allow annotations only for class types or methods
public @interface WorkHandler {
    boolean isSpecialItem();
}
