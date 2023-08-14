package com.example.concurrencypractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ConcurrencyPracticeApplication {

    public static void main(String[] args) {
        List<Thread> allThreads = new ArrayList<>();
        Runnable reporter = ()->{
                try {
                    while (true) {
                        Thread.sleep(5000);
                        printThreadStates(allThreads);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                    e.printStackTrace();
                }
        };
//        Thread monitorThread = new Thread(reporter);
        //Deamon threads get terminated on completn of the program unlike normal threads which keeps running on the background.
        //Deamon threads are useful when its monitoring or dependent on other threads running like here.
//        monitorThread.setDaemon(true);
//        monitorThread.start();
//        allThreads.add(monitorThread);
//        Runnable reporterReported = ()->{
//            try {
//                while (true) {
//                    Thread.sleep(2000);
//                    System.out.println(monitorThread.getState());
//                }
//            } catch (InterruptedException e) {
//                System.out.println("Interrupted");
//                e.printStackTrace();
//            }
//        };
//        Thread monitorMonitorThread = new Thread(reporterReported);
//        monitorMonitorThread.setDaemon(true);
//        monitorMonitorThread.start();
        Scanner sc = new Scanner(System.in);
//        SpringApplication.run(ConcurrencyPracticeApplication.class, args);
        while (true) {
            System.out.println("Enter n to find nth. prime number using super inefficient algorithm:");
            int n = sc.nextInt();
            if (n == 0) {
//                monitorThread.interrupt();//interrupt is used to kill a thread and bring a running thread to end.
                try {
                    System.out.println("Intentionally stopping the flow here after this line of code for all threads to finish using join");
                    waitForAllThreadsToComplete(allThreads);//join freezes the main thread until all threads are terminated.So the done for the day broo print will only be completed once all thread finish processing.
                    //using Thread.interrupt() a thread can be stopped as shown here.(Removed deamon behaviour on the thread to force interrupt explicitly and see the behaviour)
//                    monitorThread.interrupt();
                    System.out.println("DOne for the day bro!!!!!!!");
                }
                catch (Exception e) {
                    System.out.println("Exception occured");
                }
                break;
            }
//            Runnable r = new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(n + "th. prime number is: " + calculatePrime(n));
//                }
//            };
            //ALternative for above as below:-
            Runnable r = new MyRunnableClass(n);
            Thread t = new Thread(r);
            allThreads.add(t);
            t.start();
            //Thread lifecycle:-
            /*
            * New
            * Runnable
            * Blocked
            * Waiting
            * Timed Waiting
            * Terminated
             */
            System.out.println("last line of program loop");
        }
        System.out.println("last line of program");
    }

    public static void waitForAllThreadsToComplete(List<Thread> threads) throws InterruptedException {
        for(Thread t: threads) {
            t.join();
        }
    }

    public static int calculatePrime(int n) {
        int initialNumber = 1;
        int numberCount = 0;
        while(numberCount < n) {
            int i = 2;
            initialNumber++;
            for(i = 2;i<=initialNumber && initialNumber % i!=0;i++) {

            }
            if(i == initialNumber)
                numberCount++;
        }
        return initialNumber;
    }

    public static void printThreadStates(List<Thread> threads) {
        for (Thread t : threads) {
            System.out.println(t.getState());
        }
    }

}
