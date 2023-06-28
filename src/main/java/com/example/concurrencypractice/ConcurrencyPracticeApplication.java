package com.example.concurrencypractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ConcurrencyPracticeApplication {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        SpringApplication.run(ConcurrencyPracticeApplication.class, args);
        while (true) {
            System.out.println("Enter n to find nth. prime number using super inefficient algorithm:");
            int n = sc.nextInt();
            if (n == 0) {
                System.out.println("DOne for the day bro!!!!!!!");
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
            t.start();

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

}
