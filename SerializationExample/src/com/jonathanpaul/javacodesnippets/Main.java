package com.jonathanpaul.javacodesnippets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    /*******************************************************************************************************************
     * Java Serialization Overview
     *
     * - Serialization is storing an object, and all other objects that point to it (a.k.a. object-graph), to a byte stream
     * - Deserialization is restoring an object-graph from a byte stream
     *******************************************************************************************************************/

    public static void main(String[] args) {
	    BankAccountCustomSerialization account1 = new BankAccountCustomSerialization("1234", 500);
	    BankAccountCustomSerialization account2 = new BankAccountCustomSerialization("5678", 750);
	    AccountGroup group = new AccountGroup();
	    group.addAccount(account1);
	    group.addAccount(account2);

	    System.out.println("Balance before serialization: " + group.getTotalBalance());
        saveGroup(group, "group.dat");
        AccountGroup savedGroup = loadGroup("group.dat"); // transient value not saved, so create our own deserialization for it
        System.out.println("Balance after serialization: " + savedGroup.getTotalBalance()); // AccountGroup's readObject will have been called above and balance is updated
    }

    private static void saveGroup(AccountGroup g, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
            out.writeObject(g);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static AccountGroup loadGroup(String filename) {
        AccountGroup group = null;
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
            group = (AccountGroup) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return group;
    }
}
