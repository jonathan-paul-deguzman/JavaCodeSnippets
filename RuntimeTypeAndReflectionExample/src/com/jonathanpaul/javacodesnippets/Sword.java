package com.jonathanpaul.javacodesnippets;

public class Sword extends Item implements Runnable {
    boolean isSharpened;
    int swordLength;
    String swordName;

    public String slash() {
        return "Ouch";
    }

    public void stab() {

    }

    @Override
    public void run() {
        System.out.println("We're running now");
    }
}
