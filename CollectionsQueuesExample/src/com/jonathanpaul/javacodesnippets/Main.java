package com.jonathanpaul.javacodesnippets;

import com.jonathanpaul.javacodesnippets.java.CategorizedHelpDesk;
import com.jonathanpaul.javacodesnippets.java.Category;
import com.jonathanpaul.javacodesnippets.java.Customer;
import com.jonathanpaul.javacodesnippets.java.HelpDesk;
import com.jonathanpaul.javacodesnippets.java.PriorityHelpDesk;
import com.jonathanpaul.javacodesnippets.java.calculatorexample.Calculator;

public class Main {

    /**
     * Collections with Modification order: Queues, Stacks, and Deques (Double-ended queue)
     *
     * Queues:
     *
     * - First in, First out data structure (like a line)
     * - boolean offer(E e) - (preferred) adds element to queue and returns false if the queue is full, true otherwise
     * - boolean add(E e) - adds element to queue and throws exception if queue is full
     * - E remove() - removes element from the front of the queue and throws Exception if empty (easy if size is known)
     * - E poll() - removes element from the front of the queue and returns null if empty (easy to check for nulls)
     * - E element() - reads top of queue and throws exception if empty
     * - E peek() - reads top of queue and returns null if empty
     */

    /**
     * 2 Key implementations for queues:
     *
     * ArrayDeque (preferred):
     * - RingBuffer based implementation
     * - Constant amortized (possible resizing) add/remove
     * - Less memory, faster
     * - No random access
     *
     * LinkedList (Used as a double ended queue because of doubly linked list):
     * - Very seldom used as a queue
     * - Has random access! but it's O(n)
     * - Slow and not a lot of memory
     * - Allows null elements
     */

    public static void main(String[] args) {
        //HelpDesk helpDesk = new HelpDesk();
        //helpDesk.enquire(Customer.JACK, Category.PHONE);
        //helpDesk.enquire(Customer.JILL, Category.PRINTER);
        //helpDesk.processAllEnquires();

//        CategorizedHelpDesk categorizedHelpDesk = new CategorizedHelpDesk();
//        categorizedHelpDesk.enqiuire(Customer.JACK, Category.PHONE);
//        categorizedHelpDesk.enqiuire(Customer.JILL, Category.PRINTER);
//        categorizedHelpDesk.processPrinterEnquiries();
//        categorizedHelpDesk.processGeneralEnquiries();
//        categorizedHelpDesk.processPrinterEnquiries();

//        PriorityHelpDesk helpDesk = new PriorityHelpDesk();
//        helpDesk.enquire(Customer.JACK, Category.PHONE);
//        helpDesk.enquire(Customer.JILL, Category.PRINTER);
//        helpDesk.enquire(Customer.MARY, Category.COMPUTER);
//        helpDesk.processAllEnquiries();
    }
}
