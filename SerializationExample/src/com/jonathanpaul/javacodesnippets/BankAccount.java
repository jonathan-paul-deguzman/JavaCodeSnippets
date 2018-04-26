package com.jonathanpaul.javacodesnippets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BankAccount implements Serializable {

    // The serialver utility performs the same java calculation, but instead looks at the class file instead of
    // the source file
    // Getting serialver: serialver com.jonathanpaul.whatever.MyClass
    // serialver -show
    // Now whenver a class
    // We use the same serialVersionUID whenever we make changes to the class so that the compiler knows
    // the 2 classes are compatible
    private static final long serialVersionUID = -123832678412647819L;

    // Since the String class and any primitive type is serializable, we know that BankAccount is also
    // serializable because all of its members are. Also, since id is just a reference to a different location
    // where the real id is stored in memory, we can say that BankAccount is a object-graph where the root of the
    // graph is the BankAccount object and the node is where the real value of id is stored.
    private final String id;
    private int balance = 0;

    public BankAccount(String id) {
        this.id = id;
    }

    public BankAccount(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    // When we call this method, we will serialize the BankAccount object into the specified file
    void saveAccount(BankAccount bankAccount, String fileName) {
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            outputStream.writeObject(bankAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // When loading serialized data, if the class you're trying to load doesn't match up with the class
    // that was loaded, you'll get an InvalidClassException. This way makes it hard to serialize data if
    // we expect to change a class over time
    //
    // Serial version unique identifier indicates version compatibility. That means two versions are only
    // considered compatible if they have the same serial version. Java calculates this number and its value
    // is affected by the type name, implemented interfaces, and members.
    BankAccount loadAccount(String fileName) {
        BankAccount bankAccount = null;
        try (ObjectInputStream inputStream =
                    new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
            bankAccount = (BankAccount) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bankAccount;
    }
}
