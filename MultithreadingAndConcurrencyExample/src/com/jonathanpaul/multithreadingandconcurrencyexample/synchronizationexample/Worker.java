package com.jonathanpaul.multithreadingandconcurrencyexample.synchronizationexample;

public class Worker implements Runnable {

    private BankAccount account;

    public Worker(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int startingBalance = account.getBalance();
            account.deposit(10);
            int endingBalance = account.getBalance();

            System.out.println("Start: " + startingBalance);
            System.out.println("End: " + endingBalance);
        }
    }
}
