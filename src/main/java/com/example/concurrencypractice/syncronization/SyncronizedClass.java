package com.example.concurrencypractice.syncronization;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ThreadSyncronized implements Runnable {
    private int counter = 0;
    private Lock l = new ReentrantLock();
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

//    @Override
//    public synchronized void run() {//a method can be syncronized and the below can be removed.Here by default the lock which is used is the current object reference.
//        //syncronization provides mutual exclusion(mutex or one at a time) of critical sections.
//        synchronized (this) { //critical section
//            this.increment();
//            System.out.println(Thread.currentThread().getName() + " increments counter to " + counter);
//            this.decrement();
//            System.out.println(Thread.currentThread().getName() + " decrements counter to " + counter);
//        }
//    }
@Override
public void run() {//using unstructured lock to gain more control.This works similar to above.
        try {
            l.lock();
            this.increment();
            System.out.println(Thread.currentThread().getName() + " increments counter to " + counter);
            this.decrement();
            System.out.println(Thread.currentThread().getName() + " decrements counter to " + counter);
        }
        finally {
            l.unlock();//since this is an unstructured lock we need to unlock it explicitly unlike in case of syncronous which did it for us automatically.Also its a good practice to write it in finally just in case an exception happens and this block is always ran.In syncronized its handled.
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
