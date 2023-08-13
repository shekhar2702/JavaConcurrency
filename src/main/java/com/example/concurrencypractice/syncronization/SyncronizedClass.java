package com.example.concurrencypractice.syncronization;

import org.springframework.boot.autoconfigure.SpringBootApplication;

class ThreadSyncronized implements Runnable {
    private int counter = 0;
    public void increment() {
        try {
//            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        counter++;
    }
    public void decrement() {
        counter--;
    }
    public int getCounter() {
        return counter;
    }

    @Override
    public void run() {
        synchronized (this) { //critical section
            this.increment();
            System.out.println(Thread.currentThread().getName() + " increments counter to " + counter);
            this.decrement();
            System.out.println(Thread.currentThread().getName() + " decrements counter to " + counter);
        }
    }
}

@SpringBootApplication
public class SyncronizedClass {
    public static void main(String args[]) {
        ThreadSyncronized threadSyncronized = new ThreadSyncronized();
        new Thread(threadSyncronized,"One").start();
        new Thread(threadSyncronized,"Two").start();
        new Thread(threadSyncronized,"Three").start();
        new Thread(threadSyncronized,"Four").start();
        ThreadSyncronized threadSyncronized1 = new ThreadSyncronized();
        new Thread(threadSyncronized1,"Five").start();
    }
}
