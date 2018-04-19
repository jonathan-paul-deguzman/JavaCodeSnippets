package com.jonathanpaul.multithreadingandconcurrencyexample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SingleThreadAdder {

    private String inFile, outFile;

    public SingleThreadAdder(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    /*
     * Note that here we are not taking advantage of CPU resources and instead relying on other resources
     */
    public void doAdd() throws IOException {
        int total = 0;
        String line = null;

        // Read in values from inFile
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inFile))) {
            while ((line = reader.readLine()) != null) {
                total += Integer.parseInt(line);
            }
        }

        // Write total to outFile
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outFile))) {
            writer.write("Total: " + total);
        }
    }

}
