package com.jonathanpaul.javacodesnippets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AccountGroup implements Serializable {

    private Map<String, BankAccountCustomSerialization> accountMap = new HashMap<>();

    /**
     * Transient Fields
     *
     * - We use the transient keyword to exclude a field from being serialized
     */
    private transient int totalBalance;

    public int getTotalBalance() {
        return totalBalance;
    }

    public void addAccount(BankAccountCustomSerialization account) {
        totalBalance += account.getBalance();
        accountMap.put(account.getId(), account);
    }

    // Remember to make this private
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // we want to set up totalBalance ourselves
        for (BankAccountCustomSerialization account : accountMap.values())  {
            totalBalance += account.getBalance();
        }
    }
}
