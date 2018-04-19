package com.jonathanpaul.multithreadingandconcurrencyexample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MultiThreadAdder implements Runnable {

    private String inFile, outFile;

    public MultiThreadAdder(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public void doAdd() throws IOException {
        int total = 0;
        String line = null;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inFile))) {
            while ((line = reader.readLine()) != null) {
                total = Integer.parseInt(line);
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outFile))) {
            writer.write("Total: " + total);
        }
    }

    /*
     * By implementing the runnable interface and its run method, we will indicate that this class knows
     * how to be run on another thread.
     *
     * Note that since doAdd throws an IOException, it's our Runnable method's responsibility to handle
     * any exceptions that might occur.
     */
    @Override
    public void run(){
        try {
            doAdd();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
