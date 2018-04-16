package com.jonathanpaul.logsystemexample;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    // This is how we get a reference to our logger so that we can use it throughout the project
    static Logger logger = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);

    // Uses our log configuration file, log.properties
    static Logger myLogger = Logger.getLogger("com.jonathanpaul.logsystemexample");

    /*
     * For making the most out of a logger, really look into the naming hierarchies of logging
     * Loggers can pass entries to parent and can inherit parent log level
     *
     * We would manage setup primarily on parent loggers and make log calls primarily on child loggers
     */
    public static void main(String[] args) {
        //usingLogLevels();
        //usingLogMethods();
        myLogger.log(Level.INFO, "We're logging!");
    }

    /*
     * Logging Levels
     *
     * Once we set a level, the logger ignores any entry that falls below that logging level
     * For example, logger.setLevel(Level.INFO) will only give us information for logger level
     * INFO and above (INFO, WARNING, and SEVERE).
     *
     * Level        Numeric Value       Description
     * OFF          Integer.MAX_VALUE   Logger captures nothing
     * SEVERE       1000                Serious failure
     * WARNING      900                 Potential problem
     * INFO         800                 General info
     * CONFIG       700                 Configuration info
     * FINE         500                 General developer info
     * FINER        400                 Detailed developer info
     * FINEST       300                 Specialized developer info
     * ALL          Integer.MIN_VALUE   Logger captures everything
     */
    private static void usingLogLevels() {
        // Set the logger level using the setLevel method
        logger.setLevel(Level.INFO);

        // Since INFO is 800, we should be able to see these two levels
        logger.log(Level.SEVERE, "Uh, oh!");
        logger.log(Level.INFO, "Just so you know");

        // However, we can't see these levels because they're < 800
        logger.log(Level.FINE, "Hey developer dude");
        logger.log(Level.FINEST, "You're really special");
    }

    private static void usingLogMethods() {
        // This is a simple log method
        logger.log(Level.SEVERE, "Uh, oh!");

        // This is a level convenience method where the level is determined by the method name
        logger.severe("Uh, oh again!");

        // This is a precise log method where we also pass in the class and method names
        logger.logp(Level.SEVERE, "com.jonathanpaul.logsystemexample.Main",
                "usingLogMethods", "Uh, oh a third time!");

        // This is a precise convenience method where we use entering and exiting methods
        // Always logged at level FINER
        logger.setLevel(Level.ALL);
        logger.entering("com.jonathanpaul.logsystemexample.Main",
                "usingLogMethods");
        logger.logp(Level.WARNING, "com.jonathanpaul.logsystemexample.Main",
                "usingLogMethods", "Empty function");
        logger.exiting("com.jonathanpaul.logsystemexample.Main",
                "usingLogMethods");

    }

    /*
     * There are 3 core log components
     *
     * 1. Logger - accepts app calls
     * 2. Handler - publishes logging information (logger can have multiple handlers)
     *      a. ConsoleHandler - writes to System.err
     *      b. StreamHandler - writes to OutputStream
     *      c. SocketHandler - writes to network socket
     *      d. FileHandler - writes to one or more files
     * 3. Formatter - formats log info for publication (each handler has 1 formatter)
     *      a. XMLFormatter - formats content as XML
     *      b. SimpleFormatter - formats content as simple text
     */
    private static void creatingLogComponents() {
        Handler handler = new ConsoleHandler();
        Formatter formatter = new SimpleFormatter();

        handler.setFormatter(formatter);
        logger.addHandler(handler);
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "We're logging!");
    }

    private static void loggingWithFileHandler() throws IOException {
        // This handler has a limit of 1000 bytes per file and will rotate between 4 files to display log
        // %h is the user's home directory and %g is the rotating log generation (0 -> 3 here)
        FileHandler handler = new FileHandler("%h/myapp_%g.log", 1000, 4);
        handler.setFormatter(new SimpleFormatter());
        logger.addHandler(handler);
    }
}
