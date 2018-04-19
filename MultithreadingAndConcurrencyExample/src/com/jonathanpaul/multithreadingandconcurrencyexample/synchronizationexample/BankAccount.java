package com.jonathanpaul.multithreadingandconcurrencyexample.synchronizationexample;

/*
 * The challenge of concurrency:
 *      - Threads can sometimes share resources and this causes problems
 *      - Changes to resources must be coordinated or else it could crash the program or return wrong results
 *
 * A solution to the concurrency problem: Coordinate method access
 *      - Synchronized methods
 *              - Coordinate thread access to methods
 *              - Use synchronized method modifier (class can have as many as needed)
 *              - Synchronization is managed per class instance
 *                      - i.e. no more than one thread can be in any synchronized method at a time
 *              - When to use:
 *                      - Protecting modification by multiple threads
 *                      - Reading values that might be modified by another thread
 *              - Note that it has significant overhead and should only be used in multithreading
 */

public class BankAccount {

    private int balance;

    // Constructors should never be marked as synchronized
    public BankAccount(int startBalance) {
        balance = startBalance;
    }

    // Use synchronized here to make sure that only one thread accesses this method at a time
    public synchronized int getBalance() {
        return balance;
    }

    // Use synchronized here to make sure that only one thread accesses this method at a time
    public synchronized void deposit(int amount) {
        balance += amount;
    }
}
