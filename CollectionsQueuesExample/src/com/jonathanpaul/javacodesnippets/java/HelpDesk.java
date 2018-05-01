package com.jonathanpaul.javacodesnippets.java;

import java.util.ArrayDeque;
import java.util.Queue;

public class HelpDesk {

    private final Queue<Enquiry> queue = new ArrayDeque<>();

    public void enquire(final Customer customer, final Category category) {
        queue.offer(new Enquiry(customer, category));
    }

    public void processAllEnquires() {
        while (!queue.isEmpty()) {
            final Enquiry enquiry = queue.remove();
            enquiry.getCustomer().reply("Have you tried turning it off and on again?");
        }

// This is another version that uses the poll method
//        Enquiry enquiry;
//        while ((enquiry = queue.poll()) != null) {
//            enquiry.getCustomer().reply("Have you tried turning it off and on again?");
//        }
    }
}
