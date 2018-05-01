package com.jonathanpaul.javacodesnippets.java.calculatorexample;

import java.util.ArrayDeque;
import java.util.Deque;

public class Calculator {

    /**
     * Stacks and Deques:
     *
     * Stacks (avoid because deprecated):
     * - Stacks are a LIFO (last in, first out) data strcuture
     * - Java has deprecated the Stack and instead recommends the use of Deques
     *
     * Deques (preferred):
     * - Deques are double ended queues (add or remove from both head AND tail)
     * - boolean offerFirst(E e) - inserts e into front of queue and returns false if the queue is full
     * - boolean offerLast(E e) - inserts e into back of queue returns false if the queue is full
     * - void addFirst(E e) - inserts e into front of queue throws an exception if the queue is full
     * - void addLast(E e) - inserts e into back of queue and throws an exception if the queue is full
     * - E removeFirst() - removes and returns the element. Throws exception if queue is empty
     * - E removeLast() - removes and returns the element. Throws exception if queue is empty
     * - E pollFirst() - removes and returns the element. Returns null if queue is empty
     * - E pollLast() - removes and returns the element. Returns null if queue is empty
     * - E getFirst() - reads without removing and throws exception if queue is empty
     * - E getLast() - reads without removing and throws exception if queue is empty
     * - E peekFirst() - reads without removing and returns null if queue is empty
     * - E peekLast() - reads without removing and returns null if queue is empty
     * - void push(E e) - behaves like a stack. Pushes element on top of stack
     * - E pop() - behaves like a stack. Removes element from top of stack
     */

    public int evaluate(final String input) {
        final Deque<String> stack = new ArrayDeque<>();
        final String[] tokens = input.split(" ");

        for (String token : tokens) {
            stack.add(token);
        }

        /*
         * Using the add method of queue, we'll be adding to the end of the queue:
         *
         * for example, if we have 1 - 3 + 2 + 4:
         *
         * 1
         * -
         * 3
         * +
         * 2
         * +
         * 4
         *
         * and using stack's pop method, we'll be popping the expression in the correct order
         */

        while (stack.size() > 1) {
            final int leftOperand = Integer.parseInt(stack.pop());
            final String operator = stack.pop();
            final int rightOperand = Integer.parseInt(stack.pop());

//            final int leftOperand = Integer.parseInt(stack.pollLast());
//            final String operator = stack.pollLast();
//            final int rightOperand = Integer.parseInt(stack.pollLast());

            int result = 0;
            switch(operator) {
                case "+":
                    result = leftOperand + rightOperand;
                    break;
                case "-":
                    result = leftOperand - rightOperand;
                    break;
                default:
                    System.out.println("We only support addition and subtraction at this moment. Try again.");
                    break;
            }

            stack.push(String.valueOf(result));
        }

        // The final value in the stack should be our result
        return Integer.parseInt(stack.pop());
    }
}
