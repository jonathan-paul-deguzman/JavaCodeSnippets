package com.jonathanpaul.stringformattingexample;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.StringJoiner;

public class Main {

    public static void main(String[] args) throws IOException{
        joiningSeuquenceOfValuesWithStringJoiner();
        //concatenationVsFormatting();
        //commonFormatSpecifiers();
        //writingFormatContentToAStream(22, 14, 12);
        //stringClassSupportForRegularExpressions();
    }

    private static void joiningSeuquenceOfValuesWithStringJoiner() {
        StringJoiner joiner = new StringJoiner(", ");
        joiner.add("alpha");
        joiner.add("beta");
        joiner.add("gamma");
        String resultingString = joiner.toString();
        System.out.println(resultingString);
    }

    /*
     * Format specifiers make it easier to focus on describing the desired result.
     *
     * The supporting methods are String.format, System.out.printf, and Formatter.format
     */
    private static void concatenationVsFormatting() {
        int chris = 22, veronica = 14, abby = 12;

        // Creating a string using concatenation
        String s1 = "My siblings are " + chris + ", " + veronica + ", " + abby + " years old";

        // Creating a string using formatting
        String s2 = String.format("My siblings are %d, %d, %d years old" , chris, veronica, abby);

        System.out.println("String using concatenation: " + s1);
        System.out.println("String using formatting: " + s2);
    }

    /*
     * Parts of a format specifier: % [argument index] [flags] [width] [precision] conversion
     * where [width] = minimum char length and [precision] = decimal places to display.
     *
     * Some common conversions include d (Numerical), f (Floating point), and s (String or object)
     *
     * Some common flags include # (radix), 0 (zero-padding), - (left justify) and , (groups)
     */
    private static void commonFormatSpecifiers() {
        // Formatting a numerical number. Note that %n creates a newline
        System.out.printf("50 / 2 = %d%n", 50 / 2);

        // Formatting a floating point number. Here, we use the .1 for precision.
        System.out.printf("51.7 / 2 = %.1f%n", 51.7 / 2);

        // Using %4 for padding spaces until the value takes up a minimum length of 4 chars
        System.out.printf("W: %4d, X: %4d%n", 5, 325);

        // Using %0 flag to pad zeroes to the beginning out the value
        System.out.printf("W: %04d, X: %04d%n", 5, 325);

        // Using %, flag to group the values
        System.out.printf("%,d", 1234567);
    }

    /*
     * We can also use the Formatter class to write to streams. The formatter inside the tryWithResources
     * block will handle closing the stream since the stream already implements the Closeable interface.
     */
    private static void writingFormatContentToAStream(int chris, int veronica, int abby) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get("file1.txt"));
        try (Formatter formatter = new Formatter(writer)) {
            formatter.format("My siblings are %d, %d, %d years old" , chris, veronica, abby);
        }
    }

    private static void stringClassSupportForRegularExpressions() {
        String s1 = "apple, apple and orange please";

        // Replace all occurrences of ple with ricot
        String s2 = s1.replaceAll("ple", "ricot");
        System.out.println(s2);

        // The \\b makes it so that we will only change the ple that occurs before the word breakb
        String s3 = s1.replaceAll("ple\\b", "ricot");
        System.out.println(s3);

        // Split the entire string by word breaks
        String[] parts = s1.split("\\b");
        for (String thePart : parts) {
            // w is a word character and + has one or more occurrences
            // Thus, if the String is composed of one or more occurrences of word characters, we have a match
            if (thePart.matches("\\w+")) {
                System.out.println(thePart);
            }
        }
    }
}
