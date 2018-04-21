package com.jonathanpaul.javacodesnippets;

public class Hero implements Runnable {
    Item item;
    Sword sword;

    public Hero(Item item) {

    }

    public Hero(Sword sword) {

    }

    public void doWork() {
        Thread thread = new Thread(sword != null ? sword : this);
        thread.start();
    }

    @Override
    public void run() {
        String swordName;
        int swordLength;
        boolean isSharpened;
    }
}
