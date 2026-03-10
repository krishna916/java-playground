package com.krishnamurti.playground.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Producer-Consumer Design Pattern Implementation
 *
 * Concepts for LLD (Low-Level Design) prep:
 * 1. Shared Resource: A bounded buffer (Queue) shared between Producer and Consumer threads.
 * 2. Mutual Exclusion: Using a {@link ReentrantLock} to ensure only one thread accesses the queue at a time.
 * 3. Thread Synchronization: Using {@link Condition} variables (notEmpty, notFull) to coordinate threads.
 *    - Producer waits if the queue is full (using notFull.await()).
 *    - Consumer waits if the queue is empty (using notEmpty.await()).
 * 4. Signalling: 
 *    - Producer signals waiting Consumers (notEmpty.signalAll()) after adding an item.
 *    - Consumer signals waiting Producers (notFull.signalAll()) after removing an item.
 */
public class ProducerConsumer {

    // Lock used to achieve mutual exclusion for the shared queue
    private static final ReentrantLock lock = new ReentrantLock();

    // Condition variable to make Consumers wait when the queue is empty, and to wake them when an item is produced
    private static Condition notEmpty = lock.newCondition();
    // Condition variable to make Producers wait when the queue is full, and to wake them when an item is consumed
    private static Condition notFull = lock.newCondition();

    // The shared resource (bounded buffer)
    private static Queue<Integer> queue = new LinkedList<>();
    // Maximum capacity of the queue to simulate a bounded buffer scenario
    private static final int size = 5;


    

    /**
     * Produces an item and adds it to the queue.
     * @param produce the item to add
     */
    public void produce(int produce) {
        lock.lock(); // Acquire lock before accessing the shared queue
        try {
            // LLD Note: Always use a 'while' loop to check the condition, NOT an 'if' statement.
            // This prevents "spurious wakeups" where a thread might wake up without being explicitly signaled,
            // or another thread might have changed the queue state between the signal and this thread waking up.
            while (queue.size() == size) { // use 'size' variable instead of hardcoded 5
                notFull.await(); // Queue is full. Release lock and wait until a consumer signals notFull.
            }
            queue.offer(produce); // Add item to the queue
            notEmpty.signalAll(); // Signal all waiting consumers that the queue is no longer empty
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // Always unlock in a finally block to ensure the lock is released even if an exception occurs
        }
    }
    
    /**
     * Consumes an item from the queue and returns it.
     * @return the consumed item
     */
    public int consume() {
        lock.lock(); // Acquire lock before accessing the shared queue

        try {
            // LLD Note: 'while' loop guards against spurious wakeups and state changes by other threads.
            while (queue.isEmpty()) {
                notEmpty.await(); // Queue is empty. Release lock and wait until a producer signals notEmpty.
            }
            int data = queue.poll(); // Remove item from the queue
            notFull.signalAll(); // Signal all waiting producers that the queue is no longer full
            return data;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // Always unlock in a finally block
        }
        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();

        System.out.println("=== Producer-Consumer Scenarios ===\n");

        // Scenario 1: 1 Producer, 2 Consumers
        System.out.println("--- Scenario 1: 1 Producer (20 items), 2 Consumers (10 items each) ---");
        Runnable producerTask1 = () -> {
            for (int i = 1; i <= 20; i++) {
                pc.produce(i);
                System.out.println(Thread.currentThread().getName() + " produced: " + i);
                try { Thread.sleep(50); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        };

        Runnable consumerTask1 = () -> {
            for (int i = 1; i <= 10; i++) {
                int val = pc.consume();
                System.out.println(Thread.currentThread().getName() + " consumed: " + val);
                try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        };

        Thread p1 = new Thread(producerTask1, "Producer-1");
        Thread c1 = new Thread(consumerTask1, "Consumer-1");
        Thread c2 = new Thread(consumerTask1, "Consumer-2");

        p1.start(); c1.start(); c2.start();
        p1.join(); c1.join(); c2.join();

        // Scenario 2: 3 Producers, 1 Consumer
        System.out.println("\n--- Scenario 2: 3 Producers (5 items each), 1 Consumer (15 items) ---");
        Runnable producerTask2 = () -> {
            String name = Thread.currentThread().getName();
            int id = Integer.parseInt(name.split("-")[1]);
            for (int i = 1; i <= 5; i++) {
                int item = id * 100 + i;
                pc.produce(item);
                System.out.println(name + " produced: " + item);
                try { Thread.sleep(10); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        };

        Runnable consumerTask2 = () -> {
            for (int i = 1; i <= 15; i++) {
                int val = pc.consume();
                System.out.println(Thread.currentThread().getName() + " consumed: " + val);
                try { Thread.sleep(20); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        };

        Thread p2 = new Thread(producerTask2, "Producer-2");
        Thread p3 = new Thread(producerTask2, "Producer-3");
        Thread p4 = new Thread(producerTask2, "Producer-4");
        Thread c3 = new Thread(consumerTask2, "Consumer-3");

        p2.start(); p3.start(); p4.start(); c3.start();
        p2.join(); p3.join(); p4.join(); c3.join();

        System.out.println("\nAll scenarios completed successfully!");
    }

}
