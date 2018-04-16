package com.jonathanpaul.persistablekeyvaluepairsexample;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {

    /*
     * Apps often need persistable key/value pairs to
     *      1. Store app configuration information
     *      2. Track simple aspects of app's state
     *      3. Track user preferences
     *
     * We use the java.util.Properties class to make key/value pairs persistable
     *      1. Inherits from HashTable class
     *      2. Keys and values are Strings
     *      3. Uses setProperty and getProperty methods
     */
    public static void main(String[] args) throws  IOException {
        //simpleSetGetPropertyValues();
        //storingAndLoadingSimpleText();
        //storingPropertiesToXml();
        //loadingPropertiesFromXml();
        usingDefaultProperties();
    }

    /*
     * Setting and Getting property values is a lot like using a HashTable or HashMap
     */
    public static void simpleSetGetPropertyValues() {
        Properties properties = new Properties();
        properties.setProperty("userName", "JP Deguzman");

        // Should return the value associated with this key, which is my name
        String userName = properties.getProperty("userName");

        // Should return null because that key does not exist
        String birthdayDate = properties.getProperty("birthdayDate");

        // Should return the default value, 0,  since they key does not exist
        String numberOfCats = properties.getProperty("numberOfCats", "0");

        System.out.println(userName);
        System.out.println(birthdayDate);
        System.out.println(numberOfCats);
    }

    /*
     * Properties can be persisted by writing to and reading from a Stream (supports simple text and XML)
     *
     * Persist as simple text (supports Writer and Reader)
     *      1. Use store and load methods
     *      2. Name the file with the .properties suffix
     *      3. One key/value pair per line separated by a = or :
     */
    public static void storingAndLoadingSimpleText() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("userName", "JP Deguzman");
        properties.setProperty("accountNumber", "123-45-6789");

        try (Writer writer = Files.newBufferedWriter(Paths.get("propertiesExample.properties"))) {
            // The comments argument is optional and can either include your comments or null
            properties.store(writer,  "My Comment");
        }
    }

    /*
     * Persist as XML (Supports OutputStream and InputString)
     *      1. Use storeToXml and loadFromXml methods
     *      2. Name the file with .xml suffix
     *      3. One key/value pair per XML element
     *      4. Stored as element named entry
     *          a. Key is stored as key attribute
     *          b. Value is stored as element value
     */
    public static void storingPropertiesToXml() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("userName", "JP Deguzman");
        properties.setProperty("accountNumber", "123-45-6789");

        try (OutputStream outputStream = Files.newOutputStream(Paths.get("loadXmlExample.xml"))) {
            properties.storeToXML(outputStream, "My Comment");
        }
    }

    public static void loadingPropertiesFromXml() throws IOException {
        Properties properties = new Properties();

        try (InputStream input = Files.newInputStream(Paths.get("loadXmlExample.xml"))) {
            properties.loadFromXML(input);
        }

        String userName = properties.getProperty("userName");
        String accountNumber = properties.getProperty("accountNumber");

        System.out.println(userName);
        System.out.println(accountNumber);
    }

    /*
     * It's often useful to provide default values because it provides initial values for user preferences
     * We can create properties with defaults by passing default properties to constructor of a new Properties object
     */
    public static void usingDefaultProperties() {
        Properties defaults = new Properties();
        defaults.setProperty("position", "1");

        Properties properties = new Properties(defaults);
        int position = Integer.parseInt(properties.getProperty("position"));

        System.out.println("Position from defaults: " + position);

        properties.setProperty("position", Integer.toString(position++));
        properties.getProperty("position");

        System.out.println("Position updated from properties: " + position);
    }
}
