package com.jonathanpaul.javacodesnippets.java;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityHelpDesk {

    /**
     * Priority Queues:
     *
     * - priority queues are queues where the order in which the elements come out is based on priority
     */

    // Compares the enquiries by category
    private static final Comparator<Enquiry> BY_CATEGORY = new Comparator<Enquiry>() {
        @Override
        public int compare(Enquiry o1, Enquiry o2) {
            return o1.getCategory().compareTo(o2.getCategory());
        }
    };

    private Queue<Enquiry> enquiries = new PriorityQueue<>(BY_CATEGORY);

    public void enquire(Customer customer, Category category) {
        enquiries.offer(new Enquiry(customer, category));
    }

    public void processAllEnquiries() {
        Enquiry enquiry;
        while ((enquiry = enquiries.poll()) != null) {
            enquiry.getCustomer().reply("Have you tried turning it off and on again?");
        }
    }
}
