package com.jonathanpaul.javacodesnippets.java;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Predicate;

public class CategorizedHelpDesk {

    private Queue<Enquiry> enquiries = new ArrayDeque<>();

    public boolean enqiuire(Customer customer, Category category) {
        return enquiries.offer(new Enquiry(customer, category));
    }

    // This is the pre-Java8 way of doing this
//    public void processPrinterEnquiries() {
//        final Enquiry enquiry = enquiries.peek();
//        if (enquiry != null && enquiry.getCategory() == Category.PRINTER) {
//            enquiries.remove();
//            enquiry.getCustomer().reply("Is the printer out of paper?");
//        } else {
//            System.out.println("No work to do.");
//        }
//    }
//
//    public void processGeneralEnquiries() {
//        final Enquiry enquiry = enquiries.peek();
//        if (enquiry != null && enquiry.getCategory() != Category.PRINTER) {
//            enquiries.remove();
//            enquiry.getCustomer().reply("Have you tried turning it off and on again?");
//        } else {
//            System.out.println("No work to do.");
//        }
//    }

    public void processPrinterEnquiries() {
        processEnquiries(
                enquiry -> enquiry.getCategory() == Category.PRINTER,
                "Is the printer out of printer?"
        );
    }

    public void processGeneralEnquiries() {
        processEnquiries(
                enquiry -> enquiry.getCategory() != Category.PRINTER,
                "Have you tried turning it off and on again?"
        );
    }

    public void processEnquiries(final Predicate<Enquiry> predicate, final String message) {
        final Enquiry enquiry = enquiries.peek();
        if (enquiry != null && predicate.test(enquiry)) {
            enquiries.remove();
            enquiry.getCustomer().reply(message);
        } else {
            System.out.println("Nothing to do here, let's grab coffee!");
        }
    }
}
