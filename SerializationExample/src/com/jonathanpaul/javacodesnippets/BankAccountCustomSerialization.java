package com.jonathanpaul.javacodesnippets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * To handle serializing: Add writeObject method to type
 * To handle deserializing: Add readObject method to type
 *
 * Both writeObject and readObject are called through reflection. This means that we will
 * mark these are private so nobody else can call them.
 */
public class BankAccountCustomSerialization implements Serializable {

    private  String id;
    private int balance = 0;
    private char lastTxType;
    private int lastTxAmount;

    public BankAccountCustomSerialization(String id) {
        this.id = id;
    }

    public BankAccountCustomSerialization(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }


    public int getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }

    /**
     * writeObject
     *
     * - return type of void
     * - include throws clause (IOException)
     * - Accepts ObjectOutputStream (writes values (writeInt, etc.), defaultWriteObject for default behavior)
     */
    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject();
    }

    /**
     * readObject
     *
     * - return type of void
     * - include throws clause (IOException, ClassNotFoundException)
     * - Accepts ObjectInputStream (read values (readInt, etc.), readFields to get field name info, defaultReadObject)
     */
    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        // Get the fields we want from the serialized object (make sure to cast objects back into their
        // normal forms. Primitive types are fine.
        ObjectInputStream.GetField fields = inputStream.readFields();
        id = (String) fields.get("id", null);
        balance = fields.get("balance", 0);
        lastTxType = fields.get("lastTxType", 'u');
        lastTxAmount = fields.get("lastTxAmount", -1);
    }
}
