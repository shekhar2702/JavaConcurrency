package com.example.concurrencypractice;

public class MyRunnableClass implements Runnable{
    private int n;

    public MyRunnableClass(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        System.out.println(this.n + "th. prime number is: " + ConcurrencyPracticeApplication.calculatePrime(this.n));
    }
}
