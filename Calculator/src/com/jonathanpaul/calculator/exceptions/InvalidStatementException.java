package com.jonathanpaul.calculator.exceptions;

/*
 * - Exceptions provide a non-intrusive way to signal errors and we can even create customized exceptions like this.
 * - Exceptions are caught by type; they are caught by most specific type to least specific.
 * - Two ways to handle exceptions:
 *      1. Use throws to make the calling method handle the exception
 *      2. Handle it ourselves by using a try-catch-finally block
 */
public class InvalidStatementException extends Exception {
    public InvalidStatementException(String reason, String statement) {
        super(reason + ": " + statement);
    }

    public InvalidStatementException(String reason, String statement, Throwable cause) {
        super(reason + ": " + statement, cause);
    }
}
