package com.jonathanpaul.multithreadingandconcurrencyexample;

import com.jonathanpaul.multithreadingandconcurrencyexample.synchronizationexample.BankAccount;
import com.jonathanpaul.multithreadingandconcurrencyexample.synchronizationexample.Worker;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/*
 * Multithreading and Concurrency basics:
 *
 * Process:
 *      - Instance of a program/application
 *      - Has resources such as memory, etc.
 *      - Has at least one thread
 *
 * Thread:
 *      - Sequence of programmed instructions
 *      - The thing that executes a programs code
 *      - Utilizes process resources
 */

/*
 * How Concurrency works:
 *
 * 1. We launch our application and a process is created
 * 2. The process then allocates resources to be used by the application
 * 3. The process runs over a period of time and over that period of time, the main thread does work
 * 4. The main thread interacts with some known set of resources
 * 5. At some point in time, the main thread may create another thread, and another, and so on (Multithreading)
 * 6. These threads are now running at the same time (Concurrency)
 * 7. We just need to make sure that these threads do not try to access the same resource at the same time!
 */

/*
 * Why is Multithreading important?
 *
 * - Can enable more complete/efficient CPU use (good for running things in parallel)
 * - Good for reducing execution time because we do not need to wait on a single thread to finish
 */

/*
 * Threading Foundation Types:
 *
 * 1. Runnable Interface
 *      - Represents a task to be run on a thread
 *      - Only member is the run method
 *
 * 2. Thread class
 *      - Represents a thread of execution
 *      - Can interact with and affect thread state
 *      - Begin execution with start method
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
	    //addValuesUsingSingleThreadedAdder();
        //addValuesUsingMultiThreadedAdder();
        //retrievingResultsFromThreadPools();
        usingSynchronizedToPreventConcurrencyProblems();
    }

    /*
     * This is a single threaded implementation.
     *
     * We'll first add the contents of file1.txt, then file2.txt, and so on. This all all done linearly so
     * each file will have to wait on the the previous file to finish before starting. This is an easy example
     * to see how multithreading would help speed up execution time.
     */
    private static void addValuesUsingSingleThreadedAdder() {
        String[] inFiles = new String[] {"file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt", "file6.txt"};
        String[] outFiles = new String[] {"file1.out.txt", "file2.out.txt", "file3.out.txt", "file4.out.txt", "file5.out.txt", "file6.out.txt"};

        try {
            for (int i = 0; i < inFiles.length; i++) {
                SingleThreadAdder singleThreadAdder = new SingleThreadAdder(inFiles[i], outFiles[i]);
                singleThreadAdder.doAdd();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Now that's more like it; now we have a multithreaded implementation.
     *
     * Our main thread launches a new thread for each of the Adder instances. It will then wait for all the new
     * threads to finish executing before it shuts down by using the thread.join method.
     *
     * The challenge with using threads is that we must use them EFFICIENTLY. It's really easy to misuse threads.
     *
     * Challenge: What if we had, instead of 6 files, 10000 huge files to process? Yeah, that would choke the system.
     * To solve this issue, we should use Thread Pools.
     */
    private static void addValuesUsingMultiThreadedAdder() throws InterruptedException {
        String[] inFiles = new String[] {"file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt", "file6.txt"};
        String[] outFiles = new String[] {"file1.out.txt", "file2.out.txt", "file3.out.txt", "file4.out.txt", "file5.out.txt", "file6.out.txt"};

        Thread[] threads = new Thread[inFiles.length];
        for (int i = 0; i < inFiles.length; i++) {
            MultiThreadAdder multiThreadAdder = new MultiThreadAdder(inFiles[i], outFiles[i]);

            // The Thread class constructor expects an implementation of Runnable and will use it to create
            // a new thread
            threads[i] = new Thread(multiThreadAdder);

            // well use the thread.start method to run each instance of the Adder class on a separate thread
            // Each of our 6 processes are processed on 6 different threads
            threads[i].start();
        }

        for (Thread thread : threads) {
            // This calling thread blocks the main thread until all the background threads are finished
            // executing
            thread.join();
        }
    }

    /*
     * Thread Pools:
     *
     * Let's us use threads, but takes care of the details for us.
     * Creates a queue for tasks and assigns tasks into a pool of threads.
     *
     * Thread Pool Types:
     *
     * 1. ExecutorService Interface
     *      - Models thread pool behavior
     *      - Can submit tasks
     *      - Request and wait for pool shutdown
     *
     * 2. Executors Class
     *      - Methods for creating thread pools
     */
    private static void addValuesUsingThreadPool() {
        String[] inFiles = new String[] {"file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt", "file6.txt"};
        String[] outFiles = new String[] {"file1.out.txt", "file2.out.txt", "file3.out.txt", "file4.out.txt", "file5.out.txt", "file6.out.txt"};

        // Creates a thread pool that never allows more than 3 threads to exist at one time
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < inFiles.length; i++) {
            MultiThreadAdder multiThreadAdder = new MultiThreadAdder(inFiles[i], outFiles[i]);
            // Submit instances of our Adder class. The thread pool takes care of assigning these instances off to threads to
            // do the work, but never more than 3 of them at a time.
            executorService.submit(multiThreadAdder);
        }

        try {
            // When we're done with the thread pool, we can ask for it to shut down
            executorService.shutdown();
            // We can also wait a certain amount of time before it shuts down
            executorService.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /*
     * Sometimes the main thread requires the results of the background threads being run (like if
     * the background task succeeded or not). We use the Callable and Future interfaces to keep track
     * of the results and send them to the main thread upon completion of the task.
     *
     * Callable Interface
     *      - Represents a task that can be run on a thread
     *      - The thread can return results and throw exceptions on the background thread
     *      - Only member is the call method
     *
     * Future Interface
     *      - Represents the results of a thread task that are returned by the ExecutorService.submit method
     *      - Uses the get method to block until background task is complete
     *              - Also returns Callable interface result and throws Callable interface exception
     */
    private static void retrievingResultsFromThreadPools() {
        String[] inFiles = new String[] {"file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt", "file6.txt"};
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Integer>[] results = new Future[inFiles.length];

        for (int i = 0; i < inFiles.length; i++) {
            CallableAdder callableAdder = new CallableAdder(inFiles[i]);
            // As each task if launched in the background, a reference to the future implementation
            // representing that task is put into our results array.
            results[i] = executorService.submit(callableAdder);
        }

        // When it becomes time to get our results back, we'll loop through our results array and
        // call results.get method, which blocks the main thread until our return value is available.
        for (Future<Integer> result : results) {
            try {
                int value = result.get();
                System.out.println("Total: " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * All Java objects have a lock
     *      - We can use synchronized methods to access those locks
     *              - Acquires lock of target instance of call
     *              - Only one active at a time on an object
     *      - We can also manually acquire the block by using a synchronized statement block
     *              - Available to any code that has a reference to the object
     *              - Might be useful when we can't easily manage synchronization with synchronization blocks
     */
    private static void usingSynchronizedToPreventConcurrencyProblems() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        BankAccount account = new BankAccount(100);
        // Taking advantage of the 5 threads available in the thread pool
        for (int i = 0; i < 5; i++) {
            Worker worker = new Worker(account);
            executorService.submit(worker);
        }
    }
}
