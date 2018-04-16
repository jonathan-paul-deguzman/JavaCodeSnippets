package com.jonathanpaul.propertiesfromresourceexample;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException {
        Properties defaultProperties = new Properties();
        try (InputStream input = Main.class.getResourceAsStream("MyDefaultValues.xml")) {
            defaultProperties.loadFromXML(input);
        }

        Properties userProperties = new Properties(defaultProperties);
        loadUserProperties(userProperties);

        String name = userProperties.getProperty("userName");
        String accountNumber = userProperties.getProperty("accountNumber");

        System.out.println(name);
        System.out.println(accountNumber);

        String isFirstRun = userProperties.getProperty("isFirstRun");
        if (isFirstRun.equalsIgnoreCase("Y")) {
            userProperties.setProperty("isFirstRun", "N");
            // We'll make it so that the user will not see the account number after the first run
            userProperties.setProperty("accountNumber", "XXX-XX-XXXX");
            saveUserProperties(userProperties);
        }
    }

    private static Properties loadUserProperties(Properties userProperties) throws IOException {
        Path userFile = Paths.get("userValues.xml");

        // Because userValues.xml does not exist, nothing is loaded from the XML and we'll then
        // be forced to use our default properties.
        if (Files.exists(userFile)) {
            try (InputStream input = Files.newInputStream(userFile)) {
                userProperties.loadFromXML(input);
            }
        }

        return  userProperties;
    }

    private static void saveUserProperties(Properties userProperties) throws IOException {
        try (OutputStream output = Files.newOutputStream(Paths.get("userValues.xml"))) {
            userProperties.storeToXML(output, null);
        }
    }
}
