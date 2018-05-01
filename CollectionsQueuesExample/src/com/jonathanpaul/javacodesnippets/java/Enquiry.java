package com.jonathanpaul.javacodesnippets.java;

public class Enquiry {

    private final Customer customer;
    private  final Category category;

    public Enquiry(Customer customer, Category category) {
        this.customer = customer;
        this.category = category;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Category getCategory() {
        return category;
    }

    public String toString() {
        return "Enquiry{Customer=" + customer + ", Category=" + category + "}";
    }
}
